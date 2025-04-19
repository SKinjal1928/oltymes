package app.textile.oltyems.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationBarView;

import app.textile.oltyems.R;
import app.textile.oltyems.databinding.ActivityDashboardBinding;
import app.textile.oltyems.databinding.ActivityLoginBinding;
import app.textile.oltyems.fragment.AccountFragment;
import app.textile.oltyems.fragment.DashboardFragment;
import app.textile.oltyems.fragment.FragDashboard;
import app.textile.oltyems.fragment.PaymentFragment;
import app.textile.oltyems.fragment.SalesFragment;

public class DashboardActivity extends AppCompatActivity {

    ActivityDashboardBinding binding;
    String token = "";
    private BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadFragment(R.id.menu_home);

        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.menu_home) {
//                    showCustomDialog(binding.bottomNavigation);
                    loadFragment(R.id.menu_home);
                    return true;
                } else if (menuItem.getItemId() == R.id.menu_sales) {
                    showOrder(binding.bottomNavigation);
                    loadFragment(R.id.menu_sales);
                    return true;
                } else if (menuItem.getItemId() == R.id.menu_acc) {
                    showCustomDialog(binding.bottomNavigation);
                    loadFragment(R.id.menu_acc);
                    return true;
                } else if (menuItem.getItemId() == R.id.menu_payment) {
                    showMaster(binding.bottomNavigation);
                    loadFragment(R.id.menu_payment);
                    return true;
                }
                return false;
            }
        });

    }

    public void loadFragment(int id) {
        Fragment fragment = null;

        if (id == R.id.menu_home) {
//            fragment = new FragDashboard();
            fragment = new DashboardFragment();
        } else if (id == R.id.menu_sales) {
            fragment = new SalesFragment();
        } else if (id == R.id.menu_acc) {
            fragment = new AccountFragment();
        } else if (id == R.id.menu_payment) {
            fragment = new PaymentFragment();
        }

        if (fragment == null)
            return;

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, fragment)
                .commit();
    }

    private void showCustomDialog(View anchorView) {
        // Create a view for the popup window (this will be our dialog-like view)
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.bottom_sheet_layout, null);

        // Set up the PopupWindow
        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        // Make the popup window focusable and dismissable when touched outside
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.Animation);

        // Get the button's location on the screen to position the popup above it
        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);

        // Show the PopupWindow above the button (adjust for the size of the button)
        popupWindow.showAtLocation(anchorView, Gravity.TOP,
                location[0], location[1] - popupView.getHeight() - 160);


    }
    private void showMaster(View anchorView) {
        // Create a view for the popup window (this will be our dialog-like view)
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.bottom_sheet_master, null);

        // Set up the PopupWindow
        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        // Make the popup window focusable and dismissable when touched outside
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.Animation);

        // Get the button's location on the screen to position the popup above it
        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);

        // Show the PopupWindow above the button (adjust for the size of the button)
        popupWindow.showAtLocation(anchorView, Gravity.TOP,
                location[0], location[1] - popupView.getHeight() - 160);


    }
    private void showOrder(View anchorView) {
        // Create a view for the popup window (this will be our dialog-like view)
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.bottom_sheet_order, null);

        // Set up the PopupWindow
        PopupWindow popupWindow = new PopupWindow(popupView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        // Make the popup window focusable and dismissable when touched outside
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.Animation);

        // Get the button's location on the screen to position the popup above it
        int[] location = new int[2];
        anchorView.getLocationOnScreen(location);

        // Show the PopupWindow above the button (adjust for the size of the button)
        popupWindow.showAtLocation(anchorView, Gravity.TOP,
                location[0], location[1] - popupView.getHeight() - 160);


    }
}