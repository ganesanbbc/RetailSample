package com.cognizant.sample;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CartListViewAdapter extends BaseAdapter{
    private final Activity activity;
    private final ArrayList<HashMap<String, String>> hashMaps;

    public CartListViewAdapter(Activity activity, ArrayList<HashMap<String, String>> hashMaps) {
        super();
        this.activity = activity;
        this.hashMaps = hashMaps;
    }

    @Override
    public int getCount() {
        return hashMaps.size();
    }

    @Override
    public Object getItem(int position) {
        return hashMaps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        TextView productName;
        TextView productPrice;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater inflater =  activity.getLayoutInflater();
        if (convertView == null)
        {
            convertView = inflater.inflate(R.layout.cart_list, null);
            holder = new ViewHolder();
            holder.productName = (TextView) convertView.findViewById(R.id.product_name);
            holder.productPrice = (TextView) convertView.findViewById(R.id.product_cost);
            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) convertView.getTag();
        }
       HashMap<String, String> map = hashMaps.get(position);
        holder.productName.setText(map.get(DBHelper.PRODUCT_NAME));
        holder.productPrice.setText(map.get(DBHelper.PRODUCT_PRICE));


        return convertView;
    }

}
