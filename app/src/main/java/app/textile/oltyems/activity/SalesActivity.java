package app.textile.oltyems.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import app.textile.oltyems.R;
import app.textile.oltyems.adapter.AdapterNewProduct;
import app.textile.oltyems.adapter.AdapterSales;
import app.textile.oltyems.databinding.ActivityNewProductBinding;
import app.textile.oltyems.model.ItemNewProduct;

public class SalesActivity extends AppCompatActivity {

    ActivityNewProductBinding binding;
    private AdapterSales itemAdapter;
    private List<ItemNewProduct> itemList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.txtTitle.setText("Sales");
        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.llSearchBar.setVisibility(View.GONE);
        itemList = new ArrayList<>();
        itemList.add(new ItemNewProduct("Item 1", R.drawable.camera));
        itemList.add(new ItemNewProduct("Item 2", R.drawable.camera));
        itemList.add(new ItemNewProduct("Item 3", R.drawable.camera));

        itemAdapter = new AdapterSales(this, itemList);
        binding.recyclerView.setAdapter(itemAdapter);
    }
}
