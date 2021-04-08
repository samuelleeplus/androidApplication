package com.example.myapplication.ui.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.data.model.RestaurantData;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {
    Context mContext;
    LayoutInflater inflater;

    private ArrayList<RestaurantData> arraylist;
    private List<RestaurantData> resNamesList = null;

    public ListViewAdapter(Context mContext, ArrayList<RestaurantData> resNamesList) {
        this.mContext = mContext;
        this.inflater = LayoutInflater.from(mContext);
        this.arraylist = resNamesList ;

    }

    public void filter(String charText) {

        charText = charText.toLowerCase(Locale.getDefault());
        arraylist.clear();
        if (charText.length() == 0) {
            resNamesList.addAll(arraylist);
        } else {
            for (RestaurantData wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    resNamesList.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }


    public class ResSearchViewHolder{

        TextView name ;

    }

    @Override
    public int getCount() {
        return arraylist.size();
    }

    @Override
    public Object getItem(int position) {
        return arraylist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ResSearchViewHolder holder;
        if (convertView == null) {
            holder = new ResSearchViewHolder();
            convertView = inflater.inflate(R.layout.search_restaurant_list, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) convertView.findViewById(R.id.search_restaurant_name);
            convertView.setTag(holder);
        } else {
            holder = (ResSearchViewHolder)convertView.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(arraylist.get(position).getName());
        return convertView;




    }
}
