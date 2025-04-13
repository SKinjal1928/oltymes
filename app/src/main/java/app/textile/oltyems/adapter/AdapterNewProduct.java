package app.textile.oltyems.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.HashMap;
import java.util.List;
import app.textile.oltyems.R;
import app.textile.oltyems.model.FetchOrderList;
import app.textile.oltyems.model.ItemNewProduct;

public class AdapterNewProduct extends RecyclerView.Adapter<AdapterNewProduct.ItemViewHolder> {

    private Context context;
    private List<FetchOrderList.Datum> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    List<ItemNewProduct> listProduct;
    private OnItemClickListener listener;

    public AdapterNewProduct(Context context, List<FetchOrderList.Datum> expandableListTitle,
                             HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListDetail = expandableListDetail;
        this.expandableListTitle = expandableListTitle;
        this.listener = listener;
    }

    // Interface for item click callback
    public interface OnItemClickListener {
        void onItemClick(FetchOrderList.Datum item);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_group, parent, false);
        return new AdapterNewProduct.ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        FetchOrderList.Datum datum = expandableListTitle.get(position);
        holder.listTitleTextView.setTypeface(null, Typeface.BOLD);
        holder.listTitleTextView.setText(datum.getProductName());
        holder.listWeight.setText(datum.getTotalGrossWeight()+"*"+datum.getTotalPcs());
        holder.listTitleCtn.setText(datum.getOrderOfCtn()+"");
    }

    @Override
    public int getItemCount() {
        return expandableListTitle.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView listTitleTextView, listTitleCtn, listWeight;

        public ItemViewHolder(View itemView) {
            super(itemView);
            listTitleTextView = (TextView) itemView
                    .findViewById(R.id.txt_product_name);
            listTitleCtn = (TextView) itemView
                    .findViewById(R.id.txt_ctn);
            listWeight = (TextView) itemView
                    .findViewById(R.id.txt_weight_quantity); // Using a simple TextView
        }
    }
}