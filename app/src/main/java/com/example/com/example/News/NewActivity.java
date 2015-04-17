package com.example.com.example.News;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.SwipeRefreshLayout;

import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.ListView;
import android.widget.Toast;

import com.example.android.navigationdrawerexample.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class NewActivity extends Activity  {

    public final static String json = "http://egocart.net/testing/CA2/newsDataOutPutJson.php";
    public static customAdapter adapterEm;
    ArrayList<itemdetails> itemList = new ArrayList<itemdetails>();
    ListView ListView123 = null;

    SwipeRefreshLayout swipeLayout = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.news_main);

        if(networkAvailable(NewActivity.this))
        {
            new newTask().execute(json);
            //ar1[0] = "asdasdasd";
        }else{
            showToast("No Network Connection in your device");
        }
         ListView123 = (ListView) findViewById(R.id.l1);


        swipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);

        swipeLayout.setColorScheme(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {



            @Override

            public void onRefresh() {

                // TODO Auto-generated method stub

                System.out.println("Refresh start!!!");

                if(networkAvailable(NewActivity.this))
                {
                    new newTask().execute(json);
                    //ar1[0] = "asdasdasd";
                }else{
                    showToast("No Network Connection in your device");
                }


                System.out.println("Refresh end!!!");
                swipeLayout.setRefreshing(false);

            }

        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    private customAdapter sta;



    class newTask extends AsyncTask<String, Void, String> {

        ProgressDialog Dlog;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Dlog = new ProgressDialog(NewActivity.this);
            Dlog.setMessage("Loading........");
            Dlog.setCancelable(false);
          //  Dlog.show();
        }


        @Override
        protected String doInBackground(String... params)
        {
            String jsonValue = null;
            HttpURLConnection connectionLink = null;
            try
            {
                URL linkurl = new URL(params[0]);
                connectionLink = (HttpURLConnection) linkurl.openConnection();
                int responseCode = connectionLink.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK)
                {
                    InputStream linkedStream = connectionLink.getInputStream();
                    ByteArrayOutputStream OUT = new ByteArrayOutputStream();
                    int z = 0;
                    while ((z = linkedStream.read()) != -1)
                    {
                        OUT.write(z);
                    }
                    byte[] data = OUT.toByteArray();
                    jsonValue = new String(data);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if (connectionLink != null)
                {
                    connectionLink.disconnect();
                }
            }
            return jsonValue;

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (null != Dlog && Dlog.isShowing())
            {
                Dlog.dismiss();
            }
            if (null == result || result.length() == 0)
            {
                showToast("DATA NOT FOUND");
                NewActivity.this.finish();
            }
            else{
                JSONObject jsonObject;
                try{
                    jsonObject = new JSONObject(result);


                    JSONArray jArray = jsonObject.getJSONArray("array");
                    ArrayList<itemdetails> itemList2 = new ArrayList<itemdetails>();
                    for(int i=0; i<jArray.length(); i++) {
                        JSONObject json_data = jArray.getJSONObject(i);

                        String id = json_data.getString("id");
                        String title = json_data.getString("title");
                        String content = json_data.getString("content");
                        String date = json_data.getString("date");

                        String img = json_data.getString("image");

                        Bitmap bitmap = null;
                        itemdetails item = new itemdetails(id, title, content, date, img, bitmap);
                        itemList2.add(item);
                        System.out.println(item);

                    }
                    System.out.println("item1: " + itemList);
                    System.out.println("item2: " + itemList2);
                    if(itemList.size()==itemList2.size()){
                        showToast("no new content");
                    }
                    else {
                        adapterEm = new customAdapter(NewActivity.this, itemList2);
                        ListView123.setAdapter(adapterEm);

                        itemList =    itemList2;

                            //setListAdapter(sta);

                            for (int i = 0 ; i < itemList2.size();i++) {
                                // START LOADING IMAGES FOR EACH STUDENT
                                itemdetails s = itemList2.get(i);
                                s.loadImage(adapterEm);
                            }
                    }

                }
                catch(JSONException ex){
                    ex.printStackTrace();
                }
            }

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){

        int action = MotionEventCompat.getActionMasked(event);

        switch(action) {
            case (MotionEvent.ACTION_DOWN) :
                System.out.println( "Action was DOWN");
                return true;
            case (MotionEvent.ACTION_MOVE) :
                System.out.println("Action was MOVE");
                return true;
            case (MotionEvent.ACTION_UP) :
                System.out.println("Action was UP");
          //      goToDifferentActivity();
                return true;
            case (MotionEvent.ACTION_CANCEL) :
                System.out.println("Action was CANCEL");
                return true;
            case (MotionEvent.ACTION_OUTSIDE) :
                System.out.println("Movement occurred outside bounds " +
                        "of current screen element");
                return true;
            default :
                return super.onTouchEvent(event);
        }
    }

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {


            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static boolean networkAvailable(Activity activity)
    {
        ConnectivityManager connectivity = (ConnectivityManager) activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity == null)
        {
            return false;
        }
        else
        {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
            {
                for (int i = 0; i < info.length; i++)
                {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED)
                    {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void showToast(String msg) {
        Toast.makeText(NewActivity.this, msg, Toast.LENGTH_LONG).show();
    }
}
