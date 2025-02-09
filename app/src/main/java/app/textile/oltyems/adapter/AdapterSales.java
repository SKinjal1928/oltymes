package app.textile.oltyems.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.textile.oltyems.R;
import app.textile.oltyems.activity.NewProductActivity;
import app.textile.oltyems.model.ItemNewProduct;

public class AdapterSales extends RecyclerView.Adapter<AdapterSales.ItemViewHolder> {
    private List<ItemNewProduct> itemList;
    Context mCtx;

    public AdapterSales(Context mContext, List<ItemNewProduct> itemList) {
        this.itemList = itemList;
        this.mCtx = mContext;
    }

    @Override
    public AdapterSales.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sales, parent, false);
        return new AdapterSales.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterSales.ItemViewHolder holder, int position) {
        ItemNewProduct item = itemList.get(position);
        holder.titleTextView.setText(item.getTitle());
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
        TextView titleTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.itemTitle);  // Using a simple TextView
        }
    }
}