package app.textile.oltyems.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import androidx.annotation.MenuRes;
import androidx.appcompat.app.AppCompatActivity;

import app.textile.oltyems.R;
import app.textile.oltyems.databinding.ActivityViewAllBinding;

public class ViewAllActivity extends AppCompatActivity {


    ActivityViewAllBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityViewAllBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        if(getIntent().getStringExtra("activity").equals("purchase")){
            binding.txtTitle.setText("Purchase Order");
        }else {
            binding.txtTitle.setText("Sales Order");
        }
        binding.txtAllShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v, R.menu.order_category);
            }
        });
    }
    public void showMenu(View v, @MenuRes int menuRes) {
        // Get the context from the view
        final Context context = v.getContext();

        // Create a PopupMenu
        PopupMenu popup = new PopupMenu(context, v);

        // Inflate the menu from the resource
        popup.getMenuInflater().inflate(menuRes, popup.getMenu());

        // Set the menu item click listener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                int i = menuItem.getItemId();
                if(i == 1){
                    return true;
                }else if(i == 2){
                    return true;
                }else if(i == 3){
                    return true;
                }else {
                    return false;
                }
            }
        });

        // Set the dismiss listener
        popup.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {

            }
        });

        // Show the popup menu
        popup.show();
    }
}
