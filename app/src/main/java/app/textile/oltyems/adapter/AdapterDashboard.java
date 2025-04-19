package app.textile.oltyems.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.textile.oltyems.R;
import app.textile.oltyems.activity.NewProductActivity;
import app.textile.oltyems.model.FetchProductList;

public class AdapterDashboard extends RecyclerView.Adapter<AdapterDashboard.ItemViewHolder> {
    private List<FetchProductList.PendingProduct> itemList;
    Context mCtx;
    String activity = "";

    public AdapterDashboard(Context mContext, List<FetchProductList.PendingProduct> itemList,
                        String activity) {
        this.itemList = itemList;
        this.mCtx = mContext;
        this.activity = activity;

    }

    @Override
    public AdapterDashboard.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dash, parent, false);
        return new AdapterDashboard.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterDashboard.ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, txt_no_ctn, txt_pcs, txt_weight, txt_cbm, txt_invoice;
        LinearLayout linearLayout;

        public ItemViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.itemTitle);  // Using a simple TextView
            txt_no_ctn = itemView.findViewById(R.id.txt_no_of_ctn);  // Using a simple TextView
            txt_cbm = itemView.findViewById(R.id.txt_cbm);  // Using a simple TextView
            txt_pcs = itemView.findViewById(R.id.txt_pcs);  // Using a simple TextView
            txt_weight = itemView.findViewById(R.id.txt_weight);  // Using a simple TextView
            txt_invoice = itemView.findViewById(R.id.txt_invoice);  // Using a simple TextView
            linearLayout = itemView.findViewById(R.id.ll_main);  // Using a simple TextView
        }
    }
}
