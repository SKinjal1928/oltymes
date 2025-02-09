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
import app.textile.oltyems.databinding.ActivityNewProductBinding;
import app.textile.oltyems.model.ItemNewProduct;

public class NewProductActivity extends AppCompatActivity {

    ActivityNewProductBinding binding;
    private AdapterNewProduct itemAdapter;
    private List<ItemNewProduct> itemList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityNewProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
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
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        itemList = new ArrayList<>();
        itemList.add(new ItemNewProduct("Item 1", R.drawable.camera));
        itemList.add(new ItemNewProduct("Item 2", R.drawable.camera));
        itemList.add(new ItemNewProduct("Item 3", R.drawable.camera));

        itemAdapter = new AdapterNewProduct(itemList);
        binding.recyclerView.setAdapter(itemAdapter);
    }
}
