package app.textile.oltyems.activity;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import app.textile.oltyems.R;
import app.textile.oltyems.adapter.AdapterNewProduct;
import app.textile.oltyems.adapter.CustomProductAdapter;
import app.textile.oltyems.common.FetchProductList;
import app.textile.oltyems.common.SharedPref;
import app.textile.oltyems.databinding.ActivityNewProductBinding;
import app.textile.oltyems.model.FetchOrderList;
import app.textile.oltyems.model.ItemNewProduct;
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
    List<FetchOrderList.Datum> expandableListTitle;
    private ArrayList<FetchProductList.Datum> productList; // The list of filtered products based on the search
    private ArrayList<FetchProductList.Datum> filteredProductList; // The list of filtered products based on the search

    RetroInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        apiInterface = RetrofitClient.getRetrofitInstance().create(RetroInterface.class);
        expandableListTitle = new ArrayList<>();
        expandableListDetail = new HashMap<>();
        filteredProductList = new ArrayList<>();
        productList = new ArrayList<>();
        SharedPref.init(NewProductActivity.this);
        if (getIntent().getStringExtra("activity") != null) {
            if (getIntent().getStringExtra("activity").equals("sales")) {
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

       /* binding.recyclerView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

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
        });*/
        getProductListing();
        searchProduct();
        binding.etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomDropdownDialog(productList);
            }
        });
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
                    searchProduct(); // Call search function
                } else {

                    // If search is empty, show all products
                    /*filteredProductList.clear();
                    filteredProductList.addAll(expandableListTitle);
                    itemAdapter = new AdapterNewProduct(NewProductActivity.this, expandableListTitle, expandableListDetail);
                    binding.recyclerView.setAdapter(itemAdapter);*/

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // You can leave this empty if not needed
            }
        });
    }
    private ListView listViewItems;
    private EditText editTextSearch;
    private PopupWindow popupWindow;
    private CustomProductAdapter adapter;

    private void showCustomDropdownDialog(ArrayList<FetchProductList.Datum> listParties) {
        // Inflate the dropdown layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View dialog = inflater.inflate(R.layout.custom_dropdown_layout, null);

        // Initialize views in the dialog
        editTextSearch = dialog.findViewById(R.id.editTextSearch);
        listViewItems = dialog.findViewById(R.id.listViewItems);

        // Set up the adapter
        // Set up the custom adapter
        filteredProductList = new ArrayList<>(listParties);

        adapter = new CustomProductAdapter(this, filteredProductList);
        listViewItems.setAdapter(adapter);
        // Create PopupWindow
        popupWindow = new PopupWindow(dialog,
                ViewGroup.LayoutParams.MATCH_PARENT, // width
                ViewGroup.LayoutParams.WRAP_CONTENT, // height adjusts dynamically
                true); // focusable

        // Show the popup window below the TextView
        popupWindow.showAsDropDown(binding.etSearch, 0, 10, Gravity.START);
        // Handle filtering based on EditText input
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String query = s.toString().trim();
                if (!query.isEmpty()) {
                    filterList(s.toString()); // Call search function
                } else {
                    filteredProductList.clear();
                    filteredProductList.addAll(productList);
                    adapter = new CustomProductAdapter(NewProductActivity.this, filteredProductList);
                    listViewItems.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        // Handle item click
        listViewItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                binding.etSearch.setText(filteredProductList.get(position).getProductName());

//                selectedPartyID = listPartiesFilter.get(position).getPartyId().toString();

                popupWindow.dismiss(); // Close the dialog
                openDialogForCtn(filteredProductList.get(position));
            }
        });

    }

    private void openDialogForCtn(FetchProductList.Datum selectedItem) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_product);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//        TextView txtCancel = dialog.findViewById(R.id.txtCancel);


        TextView txt_product_name = dialog.findViewById(R.id.txt_product_name);
        txt_product_name.setText(selectedItem.getProductName());
        EditText et_order_ctn = dialog.findViewById(R.id.et_order_ctn);
        LinearLayout llSave = dialog.findViewById(R.id.txt_save);

        llSave.setOnClickListener(v -> {
            if (et_order_ctn.getText().toString().isEmpty() || et_order_ctn.getText().toString().equals("0")) {
                Toast.makeText(this, "The No of Ctn field is required.", Toast.LENGTH_SHORT).show();
            }  else {

            }
        });
        dialog.show();
    }

    // Filter list based on search input
    private void filterList(String query) {
        filteredProductList.clear();

        for (FetchProductList.Datum item : productList) {
            if(item.getProductName() != null ||
                    item.getEanCode() != null){
                if (item.getProductName().toLowerCase().contains(query.toLowerCase()) ||
                        item.getEanCode().contains(query)) {
                    filteredProductList.add(item); // Add matching products to the filtered list
                }
            }
           /* if (item.getPartyName().toLowerCase().contains(query.toLowerCase())) {
                listPartiesFilter.add(item);
            }*/
        }
        adapter.notifyDataSetChanged();
    }
    /*private void saveProductItem(FetchOrderList.Datum item) {
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
    }*/

    private void getProductListing() {
//        binding.progressBar.setVisibility(View.VISIBLE);

        Call<FetchOrderList> call1 = apiInterface.fetchOrderList("Bearer " + SharedPref.getString("token", "") + "");
        call1.enqueue(new Callback<FetchOrderList>() {
            @Override
            public void onResponse(Call<FetchOrderList> call, Response<FetchOrderList> response) {
//                binding.progressBar.setVisibility(View.GONE);
                Log.d("TAG", response.code() + "");

                if (response.code() == 200) {
                    List<String> list = new ArrayList<>();
                    Log.d("Order data Res is", response.body().getStatus() + "");
                    expandableListTitle.addAll(response.body().getData());
                    itemAdapter = new AdapterNewProduct(NewProductActivity.this, expandableListTitle, expandableListDetail
                    );
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(NewProductActivity.this));
                    binding.recyclerView.setAdapter(itemAdapter);
                } else {
                    Toast.makeText(NewProductActivity.this, "Data error..", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<FetchOrderList> call, Throwable t) {
//                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(NewProductActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Search method that filters the list based on product name or EAN code
    private void searchProduct() {
        productList.clear(); // Clear the previous search results
        Call<FetchProductList> call1 = apiInterface.fetchProductList("Bearer " + SharedPref.getString("token", "") + "");
        call1.enqueue(new Callback<FetchProductList>() {
            @Override
            public void onResponse(Call<FetchProductList> call, Response<FetchProductList> response) {
//                binding.progressBar.setVisibility(View.GONE);
                Log.d("TAG", response.code() + "");

                if (response.code() == 200) {
                    productList.addAll(response.body().getData());
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

    private void createNewProduct() {

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.dialog_create_product);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().getAttributes().windowAnimations = R.style.animation;
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        TextView txtCancel = dialog.findViewById(R.id.txtCancel);


        EditText et_product_name = dialog.findViewById(R.id.et_product_name);
        EditText et_no_ctn = dialog.findViewById(R.id.et_no_ctn);
        EditText et_order_ctn = dialog.findViewById(R.id.et_order_ctn);

        // Find the Spinner by ID
        Spinner statusSpinner = dialog.findViewById(R.id.unitSpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.unit_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        // Set a listener for the spinner to handle item selection
        statusSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Get the selected item
//                    selectedStatus  = parentView.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle case when no item is selected (optional)
            }
        });

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        TextView txtSaveOrder = dialog.findViewById(R.id.txt_save_product);
        txtSaveOrder.setOnClickListener(v -> {
            if (et_product_name.getText().toString().isEmpty()) {
                Toast.makeText(this, "The Product name field is required.", Toast.LENGTH_SHORT).show();
            } else if (et_no_ctn.getText().toString().isEmpty() || et_no_ctn.getText().toString().equals("0")) {
                Toast.makeText(this, "The No of Ctn field is required.", Toast.LENGTH_SHORT).show();
            } else if (et_order_ctn.getText().toString().isEmpty()) {
                Toast.makeText(this, "The Order Ctn field is required.", Toast.LENGTH_SHORT).show();
            } else {

            }
        });
        dialog.show();
    }

}
