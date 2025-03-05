package app.textile.oltyems.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.textile.oltyems.R;
import app.textile.oltyems.adapter.AdapterNewProduct;
import app.textile.oltyems.common.FetchProductList;
import app.textile.oltyems.common.SharedPref;
import app.textile.oltyems.databinding.ActivityNewProductBinding;
import app.textile.oltyems.model.CreateOrder;
import app.textile.oltyems.model.ItemNewProduct;
import app.textile.oltyems.pojo.ProductReq;
import app.textile.oltyems.retrofit.RetroInterface;
import app.textile.oltyems.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewProductActivity extends AppCompatActivity {

    ActivityNewProductBinding binding;
    private AdapterNewProduct itemAdapter;
    private List<ItemNewProduct> itemList;
    HashMap<String, List<String>> expandableListDetail;
    List<FetchProductList.Datum> expandableListTitle;
    private List<FetchProductList.Datum> filteredProductList; // The list of filtered products based on the search

    RetroInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitClient.getRetrofitInstance().create(RetroInterface.class);
        expandableListTitle = new ArrayList<>();
        filteredProductList = new ArrayList<>();
        SharedPref.init(NewProductActivity.this);
        if(getIntent().getStringExtra("activity")!= null){
            if(getIntent().getStringExtra("activity").equals("sales")){
                binding.txtTitle.setText("Sales");
            }
        }
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
//        expandableListDetail = ExpandableListDataPump.getData();
//        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();
        itemList.add(new ItemNewProduct("Item 1", R.drawable.camera));
        itemList.add(new ItemNewProduct("Item 2", R.drawable.camera));
        itemList.add(new ItemNewProduct("Item 3", R.drawable.camera));

//        itemAdapter = new AdapterNewProduct(this, expandableListTitle, expandableListDetail);
//        binding.recyclerView.setAdapter(itemAdapter);

        binding.recyclerView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                         " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        binding.recyclerView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                         " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });
        getProductListing();

        // Set up the TextWatcher to listen for changes in the EditText
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {
                // You can leave this empty if not needed
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                String query = charSequence.toString().trim();
                if (!query.isEmpty()) {
                    searchProduct(query); // Call search function
                } else {
                    // If search is empty, show all products
                    filteredProductList.clear();
                    filteredProductList.addAll(expandableListTitle);
                    itemAdapter = new AdapterNewProduct(NewProductActivity.this, expandableListTitle, expandableListDetail,
                            item -> saveProductItem(item));
                    binding.recyclerView.setAdapter(itemAdapter);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // You can leave this empty if not needed
            }
        });
    }

    private void saveProductItem(FetchProductList.Datum item) {
        apiInterface = RetrofitClient.getRetrofitInstance().create(RetroInterface.class);
        ProductReq order = new ProductReq(item.getProductId()+"", item.getEanCode());
        Call<CreateOrder> call1 = apiInterface.createOrder(order, "Bearer " + SharedPref.getString("token", "") + "");
        call1.enqueue(new Callback<CreateOrder>() {
            @Override
            public void onResponse(Call<CreateOrder> call, Response<CreateOrder> response) {
//                binding.progressBar.setVisibility(View.GONE);
                Log.d("TAG", response.code() + "");

                if (response.code() == 200) {
                    Log.d("Order data added",  "");
                } else {
                    Toast.makeText(NewProductActivity.this, "Order data error..", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<CreateOrder> call, Throwable t) {
//                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(NewProductActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getProductListing() {
//        binding.progressBar.setVisibility(View.VISIBLE);
        apiInterface = RetrofitClient.getRetrofitInstance().create(RetroInterface.class);
        Call<FetchProductList> call1 = apiInterface.fetchProductList("Bearer " + SharedPref.getString("token", "") + "");
        call1.enqueue(new Callback<FetchProductList>() {
            @Override
            public void onResponse(Call<FetchProductList> call, Response<FetchProductList> response) {
//                binding.progressBar.setVisibility(View.GONE);
                Log.d("TAG", response.code() + "");

                if (response.code() == 200) {
                    Log.d("Product data Success", response.body().getSuccess() + "");
                    expandableListTitle.addAll(response.body().getData());
                    filteredProductList.addAll(response.body().getData());
                    itemAdapter = new AdapterNewProduct(NewProductActivity.this, expandableListTitle, expandableListDetail,
                            item -> saveProductItem(item)
                            );
                    binding.recyclerView.setAdapter(itemAdapter);
                } else {
                    Toast.makeText(NewProductActivity.this, "Data error..", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<FetchProductList> call, Throwable t) {
//                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(NewProductActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
            }
        });
    }
    // Search method that filters the list based on product name or EAN code
    private void searchProduct(String query) {
        filteredProductList.clear(); // Clear the previous search results

        for (FetchProductList.Datum product : expandableListTitle) {
            if (product.getProductName().toLowerCase().contains(query.toLowerCase()) ||
                    product.getEanCode().contains(query)) {
                filteredProductList.add(product); // Add matching products to the filtered list
            }
        }

        // Update the RecyclerView with the filtered list
        if (filteredProductList.isEmpty()) {
            Toast.makeText(NewProductActivity.this, "No products found", Toast.LENGTH_SHORT).show();
        } else {
//            productAdapter.setProductList(filteredProductList);
            itemAdapter = new AdapterNewProduct(NewProductActivity.this, filteredProductList, expandableListDetail,
                    item -> saveProductItem(item));
            binding.recyclerView.setAdapter(itemAdapter);
        }
    }
}
