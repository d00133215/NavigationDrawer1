package com.example.android.navigationdrawerexample;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by waihong on 08/03/2015.
 */
public class emergencyAdapter extends BaseAdapter {

    ArrayList<String> mItems;
    Context mContext;
  String number = null;

    public emergencyAdapter(Context context,ArrayList<String> rowItem) {

        mContext = context;
        mItems = rowItem;
    }

    public void add(String item) {

        mItems.add(item);
      //  MainActivity.userHero22.addSMSList(item);
        notifyDataSetChanged();

    }

    public void remove(String item) {

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
            convertView = mInflater.inflate(R.layout.emergency_layout, null);
             itemLayout = (RelativeLayout) mInflater.inflate(R.layout.emergency_layout, null);
        }


        final RelativeLayout textView = (RelativeLayout) itemLayout.findViewById(R.id.RelativeLayout1);
        number = (String)  mItems.get(position);

        final TextView smsNumber = (TextView) convertView.findViewById(R.id.titleViewSms);

        smsNumber.setText(number.toString());
        System.out.println("new emergency number seted " + number.toString());




        smsNumber.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);

                // set title
                alertDialogBuilder.setTitle("Delete? ");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Click yes to delete!")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // if this button is clicked, close
                                        // current activity
                                       remove(smsNumber.getText().toString());
                                    }
                                })
                        .setNegativeButton("No",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,
                                                        int id) {
                                        // if this button is clicked, just close
                                        // the dialog box and do nothing
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();


                System.out.println("longclicke happened" + smsNumber.getText());
                 return true;
            }
        });

        return convertView;
    }

}
