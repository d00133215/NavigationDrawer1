package com.example.com.example.News;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.navigationdrawerexample.R;

import java.util.ArrayList;

/**
 * Created by waihong on 25/03/2015.
 */
public class customAdapter extends BaseAdapter {

    ArrayList<itemdetails> mItems;
    Context mContext;
    String number = null;

    public customAdapter(Context context,ArrayList<itemdetails> rowItem) {

        mContext = context;
        mItems = rowItem;
    }

    public void add(itemdetails item) {

        mItems.add(item);
        //  MainActivity.userHero22.addSMSList(item);
        notifyDataSetChanged();

    }

    public void remove(itemdetails item) {

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


    RelativeLayout itemLayout = null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.custom_adapter, null);
            itemLayout = (RelativeLayout) mInflater.inflate(R.layout.custom_adapter, null);
        }

        final TextView title = (TextView) convertView.findViewById(R.id.title);
        final TextView content = (TextView) convertView.findViewById(R.id.content);
        ImageView img = (ImageView) convertView.findViewById(R.id.imgs);
        final TextView date = (TextView) convertView.findViewById(R.id.date);

        title.setText(mItems.get(position).getTitle());
        content.setText(mItems.get(position).getContent());

        String uri = "@drawable/myresource.png";

       // img.setImageDrawable(getResources().getDrawable(R.drawable.frnd_inactive));


        if (mItems.get(position).getImg() != null && !mItems.get(position).getImg().equals("")) {
           // String url = "http://egocart.net/testing/CA2/"+mItems.get(position).getImg();
         //   String url = "http://www.sportsdestinations.com/files/sports_destination_management/nodes/2015/8968/IMG.jpg";
          //  new ImageLoadTask().execute(url);
        }


        img.setImageBitmap(mItems.get(position).getImage());

        date.setText(mItems.get(position).getDate());

        return convertView;
    }


}


