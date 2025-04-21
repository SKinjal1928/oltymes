package app.textile.oltyems.fragment;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.MenuRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import app.textile.oltyems.R;
import app.textile.oltyems.ScannedBarcodeActivity;
import app.textile.oltyems.activity.CustomerActivity;
import app.textile.oltyems.activity.NewProductActivity;
import app.textile.oltyems.activity.SalesActivity;
import app.textile.oltyems.activity.ViewAllActivity;
import app.textile.oltyems.adapter.AdapterPurchase;
import app.textile.oltyems.adapter.AdapterSales;
import app.textile.oltyems.adapter.AdapterShipment;
import app.textile.oltyems.common.SharedPref;
import app.textile.oltyems.databinding.FragAllProductBinding;

import app.textile.oltyems.model.FetchProductList;
import app.textile.oltyems.model.FetchShipmentList;
import app.textile.oltyems.model.FetchShipmentResponse;
import app.textile.oltyems.retrofit.RetroInterface;
import app.textile.oltyems.retrofit.RetrofitClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllProductFragment extends Fragment implements View.OnClickListener {

    FragAllProductBinding binding;
    RetroInterface apiInterface;
    List<FetchShipmentList.Datum> shipmentList;
    String Ship_id = "", activity = "";
    ColorStateList def;
    private AdapterSales itemAdapter;
    private AdapterShipment shipmentAdapter;
    List<FetchProductList.PendingProduct> shipmentProductList;
    public AllProductFragment() {
        // require a empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
//        View rootView = inflater.inflate(R.layout.frag_dashboard, container, false);
        binding = FragAllProductBinding.inflate(inflater, container, false);
        SharedPref.init(getActivity());
        shipmentList = new ArrayList<>();
        shipmentProductList = new ArrayList<>();
        getShipmentListing();
        binding.item1.setOnClickListener(this);
        binding.item2.setOnClickListener(this);
        binding.item3.setOnClickListener(this);

        def = binding.item2.getTextColors();

        binding.txtAllPurchaseShipment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMenu(v, R.menu.order_category);
            }
        });
     /*   binding.viewAllPurchaseOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ViewAllActivity.class);
                i.putExtra("activity", "purchase");
                startActivity(i);
            }
        });*/

        // Set scroll listener
      /*  binding.scrollPurchaseOrder.setOnScrollChangeListener(new View.OnScrollChangeListener() {
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

        binding.llPurchasePending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), SalesActivity.class);
                i.putExtra("from","pending");

                i.putExtra("ship_id",Ship_id);
                startActivity(i);
//                startActivity(new Intent(getActivity(), SalesActivity.class));
            }
        });*/
        binding.txtMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to DetailsFragment
                Fragment detailsFragment = new FragDashboard();
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, detailsFragment)
                        .addToBackStack(null) // allows back navigation
                        .commit();

            }
        });
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        itemAdapter = new AdapterSales(getActivity(), shipmentProductList, activity);
        binding.recyclerView.setAdapter(itemAdapter);
//        itemList = new ArrayList<>();
//        fetchProductList();
        return binding.getRoot();
    }

    private static final DecimalFormat df = new DecimalFormat("0.00");

    private void getPurchaseListing() {
        binding.progressBar.setVisibility(View.VISIBLE);
        apiInterface = RetrofitClient.getRetrofitInstance().create(RetroInterface.class);
        Call<FetchShipmentResponse> call1 = apiInterface.pendingDataList("Bearer " + SharedPref.getString("token", "") + "",
                Ship_id);
        call1.enqueue(new Callback<FetchShipmentResponse>() {
            @Override
            public void onResponse(Call<FetchShipmentResponse> call, Response<FetchShipmentResponse> response) {

                Log.d("TAG", response.code() + "");

                if (response.code() == 200) {
                    Log.d("Purchase Success",  "");
                    if (response.body().getData().getOrderCounts().getPending() != null) {
//                        binding.txtPendingCount.setText("Pending (" + response.body().getData().getOrderCounts().getPending() + ")");
                    }
                    if (response.body().getData().getOrderCounts().getProceed() != null) {
//                        binding.txtProcessCount.setText("Process (" + response.body().getData().getOrderCounts().getProceed() + ")");
                    }
                    if (response.body().getData().getOrderCounts().getCompleted() != null) {
//                        binding.txtCompleteCount.setText("Complete (" + response.body().getData().getOrderCounts().getCompleted() + ")");
                    }

                    if (response.body().getData().getPendingData() != null) {
                        if (response.body().getData().getPendingData().getTotalCtnShip() != null) {
                            binding.txtCtnPending.setText(response.body().getData().getPendingData().getTotalCtnShip() + "");
                        }
                        if (response.body().getData().getPendingData().getTotalCbmShip() != null) {
                            binding.txtCbmPending.setText(df.format(Float.parseFloat(response.body().getData().getPendingData().getTotalCbmShip())) + "");
                        }
                        if (response.body().getData().getPendingData().getTotalWeightShip() != null) {
//                            binding.txtWeightPending.setText(df.format(Float.parseFloat(response.body().getData().getPendingData().getTotalWeightShip())) + "");
                            binding.txtWeightPending.setText(response.body().getData().getPendingData().getTotalWeightShip() + "");
                        }
                        if (response.body().getData().getPendingData().getTotalInvoiceShip() != null) {
//                            binding.txtInvoicePending.setText(df.format(Float.parseFloat(response.body().getData().getPendingData().getTotalInvoiceShip())) + "");
                            binding.txtInvoicePending.setText(response.body().getData().getPendingData().getTotalInvoiceShip() + "");
                        }

                    }
                    if (response.body().getData().getProceedData() != null) {
                        if (response.body().getData().getProceedData().getTotalCtnShip() != null) {
//                            binding.txtCtnProcess.setText(response.body().getData().getProceedData().getTotalCtnShip() + "");
                        }
                        if (response.body().getData().getProceedData().getTotalCbmShip() != null) {
//                            binding.txtCbmProcess.setText(df.format(Float.parseFloat(response.body().getData().getProceedData().getTotalCbmShip())) + "");
                        }
                        if (response.body().getData().getProceedData().getTotalWeightShip()!= null) {
//                            binding.txtWeightProcess.setText(df.format(Float.parseFloat(response.body().getData().getProceedData().getTotalWeightShip())) + "");
//                            binding.txtWeightProcess.setText(response.body().getData().getProceedData().getTotalWeightShip() + "");
                        }
                        if (response.body().getData().getProceedData().getTotalInvoiceShip() != null) {
//                            binding.txtInvoiceProcess.setText(df.format(Float.parseFloat(response.body().getData().getProceedData().getTotalInvoiceShip())) + "");
//                            binding.txtInvoiceProcess.setText(response.body().getData().getProceedData().getTotalInvoiceShip() + "");
                        }
                    }

                    if (response.body().getData().getCompletedData() != null) {
                        if (response.body().getData().getCompletedData().getTotalCtnShip() != null) {
//                            binding.txtCtnComplete.setText(response.body().getData().getCompletedData().getTotalCtnShip()+ "");
                        }
                        if (response.body().getData().getCompletedData().getTotalCbmShip() != null) {
//                            binding.txtCbmComplete.setText(df.format(Float.parseFloat(response.body().getData().getCompletedData().getTotalCbmShip())) + "");
                        }
                        if (response.body().getData().getCompletedData().getTotalWeightShip() != null) {
//                            binding.txtWeightComplete.setText(df.format(Float.parseFloat(response.body().getData().getCompletedData().getTotalWeightShip())) + "");
//                            binding.txtWeightComplete.setText(response.body().getData().getCompletedData().getTotalWeightShip() + "");
                        }
                        if (response.body().getData().getCompletedData().getTotalInvoiceShip() != null) {
//                            binding.txtInvoiceComplete.setText(df.format(Float.parseFloat(response.body().getData().getCompletedData().getTotalInvoiceShip())) + "");
//                            binding.txtInvoiceComplete.setText(response.body().getData().getCompletedData().getTotalInvoiceShip() + "");
                        }
                    }
                    binding.progressBar.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getActivity(), "Data error..", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<FetchShipmentResponse> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Something went wrong..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showMenu(View v, @MenuRes int menuRes) {
        // Get the context from the view
        final Context context = v.getContext();

        // Create a PopupMenu
        PopupMenu popup = new PopupMenu(context, v);
        for (int i = 0; i < shipmentList.size(); i++) {
            popup.getMenu().add(i, shipmentList.get(i).getShipId(), 0, shipmentList.get(i).getShipmentName());

//            popup.getMenu().add(shipmentList.get(i).getShipmentName());
        }
        // Inflate the menu from the resource
        popup.getMenuInflater().inflate(menuRes, popup.getMenu());

        // Set the menu item click listener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                binding.progressBar.setVisibility(View.VISIBLE);
//                int i = menuItem.getItemId();
//                Toast.makeText(getActivity(), "Selected Item ID: " + menuItem.getItemId()
//                        + ", Name: " + menuItem.getTitle(), Toast.LENGTH_SHORT).show();

                Ship_id = menuItem.getItemId() + "";
                getPurchaseListing();
                fetchProductList();
                /*if (i == 1) {
                    Ship_id = shipmentList.get(0).getShipId() + "";
                    getPurchaseListing();
                    return true;
                } else if (i == 2) {
                    Ship_id = shipmentList.get(1).getShipId() + "";
                    getPurchaseListing();
                    return true;
                } else if (i == 3) {
                    Ship_id = shipmentList.get(2).getShipId() + "";
                    getPurchaseListing();
                    return true;
                }else if (i == 4) {
                    Ship_id = shipmentList.get(3).getShipId() + "";
                    getPurchaseListing();
                    return true;
                } else {
                    return false;
                }*/
                return false;
            }
        });

        // Set the dismiss listener
        popup.setOnDismissListener(new PopupMenu.OnDismissListener() {
            @Override
            public void onDismiss(PopupMenu menu) {
                binding.progressBar.setVisibility(View.GONE);

            }
        });

        // Show the popup menu
        popup.show();
    }

    private void showCustomDialog(View anchorView) {
        View view = getActivity().findViewById(R.id.ll_dashboard);
        // Create a view for the popup window (this will be our dialog-like view)
        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(LAYOUT_INFLATER_SERVICE);
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

    private void getShipmentListing() {
//        binding.progressBar.setVisibility(View.VISIBLE);
        apiInterface = RetrofitClient.getRetrofitInstance().create(RetroInterface.class);
        Call<FetchShipmentList> call1 = apiInterface.fetchShipmentList("Bearer " + SharedPref.getString("token", "") + "");
        call1.enqueue(new Callback<FetchShipmentList>() {
            @Override
            public void onResponse(Call<FetchShipmentList> call, Response<FetchShipmentList> response) {
//                binding.progressBar.setVisibility(View.GONE);
                Log.d("TAG", response.code() + "");

                shipmentList.clear();
                if (response.code() == 200) {
                    Log.d("Shipment data Success", response.body().getSuccess() + "");
                    shipmentList.addAll(response.body().getData());

                    binding.rvShipment.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                    shipmentAdapter = new AdapterShipment(getActivity(), shipmentList);
                    binding.rvShipment.setAdapter(shipmentAdapter);

                    if (shipmentList.size() > 0) {
                        Ship_id = shipmentList.get(0).getShipId() + "";
                        getPurchaseListing();
                        fetchProductList();
                    }

                } else {
                    Toast.makeText(getActivity(), "Data error..", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<FetchShipmentList> call, Throwable t) {
//                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Something went wrong..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.item1){
            activity = "pending";
            binding.select.animate().x(0).setDuration(100);
            binding.item1.setTextColor(Color.WHITE);
            binding.item2.setTextColor(def);
            binding.item3.setTextColor(def);
            fetchProductList();
        } else if (view.getId() == R.id.item2){
            activity = "process";
            binding.item1.setTextColor(def);
            binding.item2.setTextColor(Color.WHITE);
            binding.item3.setTextColor(def);
            int size = binding.item2.getWidth();
            binding.select.animate().x(size).setDuration(100);
            fetchProductList();
        } else if (view.getId() == R.id.item3){
            activity = "complete";
            binding.item1.setTextColor(def);
            binding.item3.setTextColor(Color.WHITE);
            binding.item2.setTextColor(def);
            int size = binding.item2.getWidth() * 2;
            binding.select.animate().x(size).setDuration(100);
            fetchProductList();
        }

    }

    private void fetchProductList() {
//        binding.progressBar.setVisibility(View.VISIBLE);
        apiInterface = RetrofitClient.getRetrofitInstance().create(RetroInterface.class);
        Call<FetchProductList> call1 = apiInterface.fetchShipmentProduct("Bearer " + SharedPref.getString("token", "") + "",
                Ship_id);
        call1.enqueue(new Callback<FetchProductList>() {
            @Override
            public void onResponse(Call<FetchProductList> call, Response<FetchProductList> response) {
//                binding.progressBar.setVisibility(View.GONE);
                Log.d("TAG", response.code() + "");
                shipmentProductList.clear();
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
                        shipmentProductList.addAll(response.body().getPendingProduct());
                    }
                    itemAdapter = new AdapterSales(getActivity(), shipmentProductList, activity);
                    binding.recyclerView.setAdapter(itemAdapter);

                } else {
                    Toast.makeText(getActivity(), "Data error..", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<FetchProductList> call, Throwable t) {
//                binding.progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Something went wrong..", Toast.LENGTH_SHORT).show();
            }
        });
    }
}