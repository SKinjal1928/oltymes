package app.textile.oltyems.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.textile.oltyems.common.FetchProductList;

public class CustomProductAdapter extends ArrayAdapter<FetchProductList.Datum> implements Filterable {
    private ArrayList<FetchProductList.Datum> filteredList;
    private CustomProductAdapter.ItemFilter itemFilter = new CustomProductAdapter.ItemFilter();
    Context mCtx;

    public CustomProductAdapter(Activity context, ArrayList<FetchProductList.Datum> objects) {
        super(context, android.R.layout.simple_dropdown_item_1line, objects);
        this.mCtx = context;
        this.filteredList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate the layout for each dropdown item
        if (convertView == null) {
            convertView = LayoutInflater.from(mCtx).inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
        }

        // Get the current item
        FetchProductList.Datum item = filteredList.get(position);

        // Set the item name (displayed text)
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(item.getProductName());

        return convertView;
    }

    @Override
    public int getCount() {
        return filteredList.size();
    }

    @Override
    public FetchProductList.Datum getItem(int position) {
        return filteredList.get(position);
    }

    // Custom filter for the search functionality
    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();
            List<FetchProductList.Datum> filteredItems = new ArrayList<>();
            // If no query, return the original list
            if (constraint == null || constraint.length() == 0) {
                filteredItems.addAll(filteredList);
            } else {

                for (FetchProductList.Datum item : filteredList) {
                    if (item.getProductName().toLowerCase().contains(constraint.toString().toLowerCase().trim())) {
                        if (item.getProductName().toLowerCase().contains(constraint.toString().toLowerCase().trim()) ||
                                item.getEanCode().contains(constraint.toString().toLowerCase().trim())) {
                            filteredItems.add(item);
                        }
                    }

                }
            }
                results.values = filteredItems;
                results.count = filteredItems.size();
                return results;
            }



        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            clear();
            addAll((List<FetchProductList.Datum>) filterResults.values);
            notifyDataSetChanged();
        }
    }
}
