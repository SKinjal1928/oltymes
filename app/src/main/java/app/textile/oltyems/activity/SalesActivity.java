package app.textile.oltyems.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import app.textile.oltyems.R;
import app.textile.oltyems.adapter.AdapterSales;
import app.textile.oltyems.common.SharedPref;
import app.textile.oltyems.databinding.ActivityPurchaseBinding;
import app.textile.oltyems.model.FetchProductList;
import app.textile.oltyems.model.FetchShipmentList;
import app.textile.oltyems.model.FetchShipmentResponse;
import app.textile.oltyems.model.ItemNewProduct;
import app.textile.oltyems.retrofit.RetroInterface;
import app.textile.oltyems.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SalesActivity extends AppCompatActivity {

    ActivityPurchaseBinding binding;
    private AdapterSales itemAdapter;
    private List<ItemNewProduct> itemList;
    RetroInterface apiInterface;
    String ship_id = "", activity = "";
    List<FetchProductList.PendingProduct> shipmentProductList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPurchaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        shipmentProductList = new ArrayList<>();
        if(getIntent().getStringExtra("from") != null){
            activity = getIntent().getStringExtra("from");
        }
        if (activity.equalsIgnoreCase("pending")) {
            binding.txtTitle.setText("Pending");
            ship_id = getIntent().getStringExtra("ship_id");
        } else if (activity.equalsIgnoreCase("process")) {
            binding.txtTitle.setText("Processing");
            ship_id = getIntent().getStringExtra("ship_id");
        } else if (activity.equalsIgnoreCase("complete")) {
            binding.txtTitle.setText("Complete");
            ship_id = getIntent().getStringExtra("ship_id");
        } else {
            binding.txtTitle.setText("Sales");
        }
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.llSearchBar.setVisibility(View.GONE);
        itemList = new ArrayList<>();
        fetchProductList();
       /* itemList.add(new ItemNewProduct("Item 1", R.drawable.camera));
        itemList.add(new ItemNewProduct("Item 2", R.drawable.camera));
        itemList.add(new ItemNewProduct("Item 3", R.drawable.camera));

        itemAdapter = new AdapterSales(this, itemList);
        binding.recyclerView.setAdapter(itemAdapter);*/
    }
    private void fetchProductList() {
//        binding.progressBar.setVisibility(View.VISIBLE);
        apiInterface = RetrofitClient.getRetrofitInstance().create(RetroInterface.class);
        Call<FetchProductList> call1 = apiInterface.fetchShipmentProduct("Bearer " + SharedPref.getString("token", "") + "",
                ship_id);
        call1.enqueue(new Callback<FetchProductList>() {
            @Override
            public void onResponse(Call<FetchProductList> call, Response<FetchProductList> response) {
//                binding.progressBar.setVisibility(View.GONE);
                Log.d("TAG", response.code() + "");

                if (response.code() == 200) {
                    Log.d("Purchase Success",  "");

                    if (activity.equalsIgnoreCase("pending")) {
                        if (response.body().getPendingProduct().size() > 0) {
                            shipmentProductList.addAll(response.body().getPendingProduct());
                        }
                    } else if (activity.equalsIgnoreCase("process")) {
                        if (response.body().getPendingProceed().size() > 0) {
                            shipmentProductList.addAll(response.body().getPendingProceed());
                        }
                    } else if (activity.equalsIgnoreCase("complete")) {
                        if (response.body().getPendingCompleted().size() > 0) {
                            shipmentProductList.addAll(response.body().getPendingCompleted());
                        }
                    } else {

                    }
                    itemAdapter = new AdapterSales(SalesActivity.this, shipmentProductList, activity);
                    binding.recyclerView.setAdapter(itemAdapter);

                } else {
                    Toast.makeText(SalesActivity.this, "Data error..", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<FetchProductList> call, Throwable t) {
//                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(SalesActivity.this, "Something went wrong..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
