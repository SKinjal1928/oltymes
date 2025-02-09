package app.textile.oltyems.fragment;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.MenuRes;
import androidx.fragment.app.Fragment;

import app.textile.oltyems.R;
import app.textile.oltyems.ScannedBarcodeActivity;
import app.textile.oltyems.activity.CustomerActivity;
import app.textile.oltyems.activity.NewProductActivity;
import app.textile.oltyems.activity.SalesActivity;
import app.textile.oltyems.activity.ViewAllActivity;
import app.textile.oltyems.databinding.ActivityDashboardBinding;
import app.textile.oltyems.databinding.FragDashboardBinding;

public class DashboardFragment extends Fragment {

    FragDashboardBinding binding;

    public DashboardFragment(){
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.frag_dashboard, container, false);
        binding = FragDashboardBinding.inflate(inflater, container, false);
        binding.llScanBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ScannedBarcodeActivity.class);
                i.putExtra("activity", "purchase");
                startActivity(i);
            }
        });
        binding.txtAllPurchaseShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v, R.menu.order_category);
            }
        });
        binding.txtAllSalesShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v, R.menu.order_category);
            }
        });
        binding.llCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomDialog(v);
            }
        });
        binding.viewAllPurchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ViewAllActivity.class);
                i.putExtra("activity", "purchase");
                startActivity(i);
            }
        });
        binding.viewAllSalesOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ViewAllActivity.class);
                i.putExtra("activity", "sales");
                startActivity(i);
            }
        });
        // Set scroll listener
        binding.scrollPurchaseOrder.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // Check if the scrollX has exceeded a certain threshold (e.g., 500px)
                if (scrollX > 500) {
                    // Show the view once the scroll position exceeds 500px
                    binding.viewAllPurchaseOrder.setVisibility(View.VISIBLE);
                } else {
                    // Hide the view if the scroll position is below 500px
                    binding.viewAllPurchaseOrder.setVisibility(View.GONE);
                }
            }
        });
        binding.scrollSalesOrder.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // Check if the scrollX has exceeded a certain threshold (e.g., 500px)
                if (scrollX > 500) {
                    // Show the view once the scroll position exceeds 500px
                    binding.viewAllSalesOrder.setVisibility(View.VISIBLE);
                } else {
                    // Hide the view if the scroll position is below 500px
                    binding.viewAllSalesOrder.setVisibility(View.GONE);
                }
            }
        });
        return binding.getRoot();
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
                if(menuItem.getItemId() == R.id.option_1){
                    return true;
                }else if(menuItem.getItemId() == R.id.option_2){
                    return true;
                }else if(menuItem.getItemId() == R.id.option_3){
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

    private void showCustomDialog(View anchorView) {
        View view = getActivity().findViewById(R.id.ll_dashboard);
        // Create a view for the popup window (this will be our dialog-like view)
        LayoutInflater inflater = (LayoutInflater)getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.bottom_sheet_create, null);

        TextView txtPurchase = popupView.findViewById(R.id.txt_purchase);
        TextView txtCustomer = popupView.findViewById(R.id.txt_customer);
        TextView txtSales = popupView.findViewById(R.id.txt_sales);
        txtPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), NewProductActivity.class);
                startActivity(i);
            }
        });
        txtSales.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SalesActivity.class);
                startActivity(i);
            }
        });
        txtCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), CustomerActivity.class);
                startActivity(i);
            }
        });
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
        view.getLocationOnScreen(location);

        // Show the PopupWindow above the button (adjust for the size of the button)
        popupWindow.showAtLocation(view, Gravity.BOTTOM,
                location[0], location[1]);


    }
}