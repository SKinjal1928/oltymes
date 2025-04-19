package app.textile.oltyems.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dash, parent, false);
        return new AdapterSales.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterSales.ItemViewHolder holder, int position) {
        FetchProductList.PendingProduct item = itemList.get(position);


        holder.txt_no_ctn.setText(item.getNoOfCtn()+" ct");
        holder.txt_weight.setText(item.getTotalGrossWeight()+" G.Wt");
        holder.txt_cbm.setText(item.getTotalCbm()+" CBM");
//        holder.clientName.setText(item.getProductName()+"");
        holder.txt_invoice.setText(item.getInvoiceValue()+" Invoice");


       /* SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append(item.getProductName()).append("  ").append(item.getWeight());

// Optional: Style only the Qty part differently
        int qtyStart = builder.length() - item.getWeight().length();
        builder.setSpan(new ForegroundColorSpan(Color.GRAY), qtyStart, builder.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
*/
//        tvNameWithQty.setText(builder);
        holder.titleTextView.setText(item.getProductName());
        holder.txt_qty.setText(item.getWeight());
        if(item.getWeight().length() > 0){
            holder.txt_seperator.setVisibility(View.VISIBLE);
        }else {
            holder.txt_seperator.setVisibility(View.GONE);
        }
        if(item.getProductName().toString().length()>20){
            holder.txt_elipse.setVisibility(View.VISIBLE);
//            holder.txt_qty.setText(" - "+item.getWeight());
//            holder.titleTextView.setText(item.getProductName());
        }else {
            holder.txt_elipse.setVisibility(View.GONE);
//            holder.titleTextView.setText(item.getProductName() +" - ");
//            holder.txt_qty.setText(item.getWeight());
        }
//        holder.amountDue.setText(item.getWeight());
       /* if (activity.equalsIgnoreCase("pending")) {
            holder.linearLayout.setBackground(ContextCompat.getDrawable(mCtx, R.drawable.side_line_pending));
        } else if (activity.equalsIgnoreCase("process")) {
            holder.linearLayout.setBackground(ContextCompat.getDrawable(mCtx, R.drawable.side_line_proceed));
        } else if (activity.equalsIgnoreCase("complete")) {
            holder.linearLayout.setBackground(ContextCompat.getDrawable(mCtx, R.drawable.side_line_complete

            ));
        }*/
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
        TextView titleTextView, txt_no_ctn, txt_qty, txt_weight, txt_cbm, txt_invoice, txt_elipse,
                txt_seperator;
//                clientName, amountDue;
        LinearLayout linearLayout;

            public ItemViewHolder(View itemView) {
                super(itemView);
                titleTextView = itemView.findViewById(R.id.itemTitle);  // Using a simple TextView
                txt_no_ctn = itemView.findViewById(R.id.txt_no_of_ctn);  // Using a simple TextView
                txt_cbm = itemView.findViewById(R.id.txt_cbm);  // Using a simple TextView
                txt_qty = itemView.findViewById(R.id.txt_qty);  // Using a simple TextView
                txt_weight = itemView.findViewById(R.id.txt_weight);  // Using a simple TextView
                txt_invoice = itemView.findViewById(R.id.txt_invoice);  // Using a simple TextView
                txt_elipse = itemView.findViewById(R.id.txt_elipse);  // Using a simple TextView
                txt_seperator = itemView.findViewById(R.id.txt_seperator);  // Using a simple TextView
//                amountDue = itemView.findViewById(R.id.amountDue);  // Using a simple TextView
//                linearLayout = itemView.findViewById(R.id.ll_main);  // Using a simple TextView
            }
    }
}