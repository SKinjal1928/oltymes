package app.textile.oltyems.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

import app.textile.oltyems.R;
import app.textile.oltyems.common.FetchProductList;
import app.textile.oltyems.model.ItemNewProduct;

public class AdapterNewProduct extends BaseExpandableListAdapter {

    private Context context;
    private List<FetchProductList.Datum> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;
    List<ItemNewProduct> listProduct;
    private OnItemClickListener listener;

    public AdapterNewProduct(Context context, List<FetchProductList.Datum> expandableListTitle,
                             HashMap<String, List<String>> expandableListDetail,
                             OnItemClickListener listener) {
        this.context = context;
        this.expandableListDetail = expandableListDetail;
        this.expandableListTitle = expandableListTitle;
        this.listener = listener;
    }

    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

//        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_child, null);
        }
//        TextView expandedListTextView = (TextView) convertView
//                .findViewById(R.id.itemTitle);
//        expandedListTextView.setText(expandedListText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
//        String listTitle = (String) getGroup(listPosition);
        FetchProductList.Datum datum = (FetchProductList.Datum) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_group, null);
        }
        TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.txt_product_name);
        LinearLayout llSave = (LinearLayout)convertView.findViewById(R.id.txt_save);
        llSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(datum);
                }
            }
        });
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(datum.getProductName());
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
    // Interface for item click callback
    public interface OnItemClickListener {
        void onItemClick(FetchProductList.Datum item);
    }
}