package app.textile.oltyems.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.textile.oltyems.R;
import app.textile.oltyems.model.ItemNewProduct;

public class AdapterPurchase  extends RecyclerView.Adapter<AdapterPurchase.ItemViewHolder> {
    private List<ItemNewProduct> itemList;
    Context mCtx;

    public AdapterPurchase(Context context, List<ItemNewProduct> itemList) {
        this.itemList = itemList;
        this.mCtx = context;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        ItemNewProduct item = itemList.get(position);
        holder.titleTextView.setText(item.getTitle());
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