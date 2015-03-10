package com.example.android.navigationdrawerexample;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by waihong on 06/03/2015.
 */
public class autoAdapter extends BaseAdapter {


    ArrayList<Date> mItems;
    Context mContext;
    Date times = null;

    public autoAdapter(Context context,ArrayList<Date> rowItem) {

        mContext = context;
        mItems = rowItem;
    }

    public void add(Date item) {

        mItems.add(item);
        notifyDataSetChanged();

    }

    public void remove(Date item) {

        mItems.remove(item);
        notifyDataSetChanged();

    }
    // Clears the list adapter of all items.

    public void clear() {

        mItems.clear();
        notifyDataSetChanged();

    }


    @Override
    public int getCount() {
        return mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return  mItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mItems.indexOf(getItem(position));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext
                    .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.autoactivate_layout, null);
        }


        times = (Date)  mItems.get(position);

        TextView time = (TextView) convertView.findViewById(R.id.titleViewTime);

        time.setText(times.toString());
        System.out.println("time of the date " + times.toString());

        return convertView;
    }

    @Override
    public String toString() {
        return "autoAdapter{" +
                "MainActivity="  +
                ", mItems=" + mItems +
                ", mContext=" + times+
                '}';
    }
}
