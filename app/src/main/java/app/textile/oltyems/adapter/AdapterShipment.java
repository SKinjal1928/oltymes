package app.textile.oltyems.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import app.textile.oltyems.R;
import app.textile.oltyems.model.FetchShipmentList;
import app.textile.oltyems.model.ItemNewProduct;

public class AdapterShipment extends RecyclerView.Adapter<AdapterShipment.ItemViewHolder> {
    private List<FetchShipmentList.Datum> itemList;
    Context mCtx;

    public AdapterShipment(Context context, List<FetchShipmentList.Datum> itemList) {
        this.itemList = itemList;
        this.mCtx = context;
    }

    @Override
    public AdapterShipment.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shipmen, parent, false);
        return new AdapterShipment.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AdapterShipment.ItemViewHolder holder, int position) {
        FetchShipmentList.Datum item = itemList.get(position);
        holder.titleTextView.setText(item.getShipmentName());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.txt_shipment);  // Using a simple TextView
        }
    }
}
