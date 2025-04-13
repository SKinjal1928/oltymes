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
import app.textile.oltyems.model.ItemNewProduct;

public class AdapterSales extends RecyclerView.Adapter<AdapterSales.ItemViewHolder> {
    private List<FetchProductList.PendingProduct> itemList;
    Context mCtx;
    String activity = "";

    public AdapterSales(Context mContext, List<FetchProductList.PendingProduct> itemList,
                        String activity) {
        this.itemList = itemList;
        this.mCtx = mContext;
        this.activity = activity;

    }

    @Override
    public AdapterSales.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sales, parent, false);
        return new AdapterSales.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterSales.ItemViewHolder holder, int position) {
        FetchProductList.PendingProduct item = itemList.get(position);

        holder.titleTextView.setText(item.getProductName()+" * "+item.getNoOfCtn());
        holder.txt_no_ctn.setText(item.getOrderOfCtn()+"");
        holder.txt_weight.setText(item.getTotalGrossWeight()+"");
        holder.txt_cbm.setText(item.getTotalCbm()+"");
        holder.txt_pcs.setText(item.getTotalPcs()+"");
        holder.txt_invoice.setText(item.getInvoiceValue()+"");

        if (activity.equalsIgnoreCase("pending")) {
            holder.linearLayout.setBackground(ContextCompat.getDrawable(mCtx, R.drawable.side_line_pending));
        } else if (activity.equalsIgnoreCase("process")) {
            holder.linearLayout.setBackground(ContextCompat.getDrawable(mCtx, R.drawable.side_line_proceed));
        } else if (activity.equalsIgnoreCase("complete")) {
            holder.linearLayout.setBackground(ContextCompat.getDrawable(mCtx, R.drawable.side_line_complete

            ));
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mCtx, NewProductActivity.class);
                intent.putExtra("activity", "sales");
                mCtx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemList.size();
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