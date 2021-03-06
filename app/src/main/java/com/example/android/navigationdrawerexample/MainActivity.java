/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigationdrawerexample;

import com.example.andriod.service.MyService;
import com.example.com.example.News.NewActivity;
import com.example.com.example.News.ttss;
import com.loopj.android.http.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts;
import android.provider.ContactsContract;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;

import org.apache.http.Header;

/**
 * This example illustrates a common usage of the DrawerLayout widget
 * in the Android support library.
 * <p/>
 * <p>When a navigation (left) drawer is present, the host activity should detect presses of
 * the action bar's Up affordance as a signal to open and close the navigation drawer. The
 * ActionBarDrawerToggle facilitates this behavior.
 * Items within the drawer should fall into one of two categories:</p>
 * <p/>
 * <ul>
 * <li><strong>View switches</strong>. A view switch follows the same basic policies as
 * list or tab navigation in that a view switch does not create navigation history.
 * This pattern should only be used at the root activity of a task, leaving some form
 * of Up navigation active for activities further down the navigation hierarchy.</li>
 * <li><strong>Selective Up</strong>. The drawer allows the user to choose an alternate
 * parent for Up navigation. This allows a user to jump across an app's navigation
 * hierarchy at will. The application should treat this as it treats Up navigation from
 * a different task, replacing the current task stack using TaskStackBuilder or similar.
 * This is the only form of navigation drawer that should be used outside of the root
 * activity of a task.</li>
 * </ul>
 * <p/>
 * <p>Right side drawers should be used for actions, not navigation. This follows the pattern
 * established by the Action Bar that navigation should be to the left and actions to the right.
 * An action should be an operation performed on the current contents of the window,
 * for example enabling or disabling a data overlay on top of the current content.</p>
 */
public class MainActivity extends Activity {
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    public static  AlertDialog.Builder alertDialogBuilder = null;
    public static boolean onMainActivity = false;

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
   // private userSetting userHero =  null;
    public static userSetting userHero22 =  null;
    static Gson gson = new Gson();
    static  String json = null;
    static SharedPreferences.Editor editor = null;
    // final  SharedPreferences prefs = getPreferences(MODE_PRIVATE);
    private static final int ADD_TODO_ITEM_REQUEST = 0;


    public static locationGPS gpsTracker = null;
    public static String identityNumber;

    public static  SharedPreferences.Editor editor123 = null;
    static final int PICK_CONTACT_REQUEST = 1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ActionBar bar = getActionBar();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#cca20f0b")));
       // bar.setTitle(Html.fromHtml("<font>Wai's Paint </font>"));
        alertDialogBuilder = new AlertDialog.Builder(this);

        ///////
        if(MyService.cancel==true) {
            Intent intent = new Intent(this, unlockActivity.class);
            startActivity(intent);
        }


////////////////////////////////////////////////////////////////////////////////////


        TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        String getSimSerialNumber = telemamanger.getSimSerialNumber();

        identityNumber = getSimSerialNumber;

        System.out.print("dasdasdas"+ identityNumber);


///////////////////////////////////////////////////////////////////////////////////

         gpsTracker = new locationGPS(this);

        if (gpsTracker.canGetLocation())
        {

        }
        else
        {


            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings

            //gpsTracker.showSettingsAlert();

        }



/////////////////////////////////////////////////////////////////////////////////////

        // userHero =  new userSetting(true,80,null,false,1,"0876140810",null,null);

      //  System.out.println(userHero+"  asdasdasdasdasdasdasdasdas");

        String MyPREFERENCES = "userSettingData" ;

        //   System.out.println("abc+"+userHeroService);
        SharedPreferences prefsqwe= getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        final  SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        /*MyPREFERENCES, Context.MODE_PRIVATE
        userHero.setShackInvoke(false);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userHero);
        editor.putString("userHero", json);
        editor.commit();

        userHero.setShackInvoke(true);
        System.out.println(userHero+"  asdasdasdasdasdasdasdasdas");


        json = prefs.getString("userHero", "");
        userHero22 = gson.fromJson(json, userSetting.class);


*/

     //   json = prefs.getString("userHero22", "");
      //  userHero22 = gson.fromJson(json, userSetting.class);
      //  System.out.println(userHero22+"  testing share prefrence");
    //    editor = prefs.edit();

        //



        json = prefsqwe.getString("userHero22", "");
      userHero22 = gson.fromJson(json, userSetting.class);
        editor123 = prefsqwe.edit();
        editor= editor123;
        if(json.equals("")){
            userHero22 = new userSetting();
            saveObject();
            //Toast.makeText(this,"createing nre object",Toast.LENGTH_LONG).show();

        }
        //Toast.makeText(this,"not creating nre object",Toast.LENGTH_LONG).show();
     //  System.out.println(prefsqwe.getString("userHero22", "")+"  testing share prefrence");
    //    System.out.println("old" + prefs.getString("userHero22", "")+"  testing share prefrence old");


     //   json = gson.toJson(userHero22);
    //    editor123.putString("userHero22", json);
     //   editor123.apply();

        System.out.println(userHero22+"  testing share prefrence");
      /*
        editor123.putString("key", "value");
        editor123.commit();

        json = gson.toJson(userHero22);
        editor123.putString("userHero22", json);
        editor123.apply();

        System.out.println("testing123 " + prefsqwe.getString("userHero22",""));

*/

        //



        mTitle = mDrawerTitle = getTitle();
        mPlanetTitles = getResources().getStringArray(R.array.mm_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
                ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        if (savedInstanceState == null) {
            selectItem(0);
        }

        Intent serviceIntent = new Intent(this, MyService.class);
        startService(serviceIntent);
    }



    public  void help() {


        ImageButton btn = (ImageButton)findViewById(R.id.imageButton);
        TextView recentMsg = (TextView)findViewById(R.id.t2);

        if(userHero22.isHelp()==false) {

            recentMsg.setText("Recently activity : activated all help message send!");
            btn.setImageResource(R.drawable.stopstop);
            userHero22.setHelp(true);
            saveObject();

            String number = userHero22.getPoliceNumber();

            if(userHero22.callPolice==true) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:" + number));
                startActivity(intent);
            }
            if(userHero22.autoInvoke==true) {
                gpsTracker = new locationGPS(this);

                TelephonyManager telemamanger = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
                String getSimSerialNumber = telemamanger.getSimSerialNumber();

                String newMsg = userHero22.getHelpMsgContent() + " sim number is: " + getSimSerialNumber +" Lat: "+gpsTracker.getLatitude()+" Log: "+gpsTracker.getLongitude()
                        +"to check activity pls visit";
                System.out.println("abc" + newMsg);
                for (int i = 0; i < userHero22.getSMSList().size(); i++) {
                    sendSMS(userHero22.getSMSList().get(i), newMsg);
                }
            }


            Date time = new Date();
            postToServer(identityNumber, String.valueOf(gpsTracker.getLatitude()), String.valueOf(gpsTracker.getLongitude()),"true",time.toString());
        }
        else{
            recentMsg.setText("Recently activity : none");
            btn.setImageResource(R.drawable.hhh);
            userHero22.setHelp(false);
            saveObject();




            // set title
            alertDialogBuilder.setTitle("Do you want to send a msg to tell your friends you are fine now? ");

            // set dialog message
            alertDialogBuilder
                    .setMessage("Click yes to send!")
                    .setCancelable(false)
                    .setPositiveButton("Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // if this button is clicked, close
                                    // current activity
                                    //remove(smsNumber.getText().toString());
                                    String mm = "I am fine now. Don't worry";
                                    for (int i = 0; i < userHero22.getSMSList().size(); i++) {
                                        sendSMS(userHero22.getSMSList().get(i), mm);
                                    }

                                }
                            })
                    .setNegativeButton("No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                    // if this button is clicked, just close
                                    // the dialog box and do nothing
                                    // dialog.cancel();
                                }
                            });

            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
            //System.out.println("sdfsdfsfsdf");


            Date time = new Date();
            postToServer(identityNumber, String.valueOf(gpsTracker.getLatitude()), String.valueOf(gpsTracker.getLongitude()),"false",time.toString());
        }

    }

    public void helpPlease(View view) {
        Toast.makeText(this,"hold pls",Toast.LENGTH_SHORT).show();
    }

    public static void sendSMS(String phoneNumber, String message)
    {

        SmsManager sms = SmsManager.getDefault();

        sms.sendTextMessage(phoneNumber, null, message, null, null);
        System.out.println("sending a text");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_websearch).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action buttons
        switch(item.getItemId()) {
        case R.id.action_websearch:
            // create intent to perform web search for this planet
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

    /* The click listner for ListView in the navigation drawer */
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        // update the main content by replacing fragments
        /*
        Fragment fragment = new PlanetFragment();
        Bundle args = new Bundle();
        args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
        fragment.setArguments(args);
 */
        Fragment fragment =new Fragment0();



        if(position==0){
             fragment = new Fragment0();

        }
        if(position==1){
            Intent i = new Intent(this, ttss.class);
            startActivity(i);
            MainActivity.onMainActivity=false;
        }
        else if(position==2){
          //  Intent intent = new Intent(this, unlockActivity.class);
          //  startActivity(intent);
            fragment = new Fragment1();
            MainActivity.onMainActivity=false;
        }
        else if(position==3){
            fragment = new Fragment2();
            MainActivity.onMainActivity=false;
        }
        else if(position==4){
            fragment = new Fragment3();
            MainActivity.onMainActivity=false;
        }
        else if(position==5){
            fragment = new Fragment4();
            MainActivity.onMainActivity=false;
        }



        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

        // update selected item and title, then close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);




    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    /**
     * Fragment that appears in the "content_frame", shows a planet
     */
    public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";

        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_planet, container, false);
            int i = getArguments().getInt(ARG_PLANET_NUMBER);
            String planet = getResources().getStringArray(R.array.mm_array)[i];

            int imageId = getResources().getIdentifier(planet.toLowerCase(Locale.getDefault()),
                            "drawable", getActivity().getPackageName());
            ((ImageView) rootView.findViewById(R.id.image)).setImageResource(imageId);
            getActivity().setTitle(planet);
            return rootView;
        }
    }

    public static class Fragment0 extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            return inflater.inflate(R.layout.fragment_fragment0, container, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {

            super.onActivityCreated(savedInstanceState);
            if (!(userHero22.getSMSList().size() == 0)) {
                TextView alertMsg = (TextView) getActivity().findViewById(R.id.t1);

                alertMsg.setText("Your device is fully set up");
            }
            TextView recentMsg = (TextView) getActivity().findViewById(R.id.t2);
            ImageButton btn = (ImageButton)getActivity().findViewById(R.id.imageButton);

            if(userHero22.isHelp()==true){
                recentMsg.setText("recently action : activated all help message send");
                btn.setImageResource(R.drawable.stopstop);




            }
            else
            {
                recentMsg .setText("recently action : none");
                btn.setImageResource(R.drawable.hhh);
            }

            btn.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ImageButton btn = (ImageButton)getActivity().findViewById(R.id.imageButton);
                    TextView recentMsg = (TextView)getActivity().findViewById(R.id.t2);

                    if(userHero22.isHelp()==false) {

                        recentMsg.setText("Recently activity : activated all help message send!");
                        btn.setImageResource(R.drawable.stopstop);
                        userHero22.setHelp(true);
                        saveObject();

                        String number = userHero22.getPoliceNumber();

                        if(userHero22.callPolice==true) {
                            Intent intent = new Intent(Intent.ACTION_CALL);
                            intent.setData(Uri.parse("tel:" + number));
                            startActivity(intent);
                        }
                        if(userHero22.autoInvoke==true) {
                            gpsTracker = new locationGPS(getActivity());

                            TelephonyManager telemamanger = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
                            String getSimSerialNumber = telemamanger.getSimSerialNumber();

                            String newMsg = userHero22.getHelpMsgContent() + " sim number is: " + getSimSerialNumber +" Lat: "+gpsTracker.getLatitude()+" Log: "+gpsTracker.getLongitude()
                                    +"to check activity pls visit http://egocart.net/testing/waihongsiew/searchSite/ ";
                            System.out.println("abc" + newMsg);
                            for (int i = 0; i < userHero22.getSMSList().size(); i++) {
                                sendSMS(userHero22.getSMSList().get(i), newMsg);
                            }
                        }


                        Date time = new Date();
                        postToServer(identityNumber, String.valueOf(gpsTracker.getLatitude()), String.valueOf(gpsTracker.getLongitude()),"true",time.toString());
                    }
                    else{
                        recentMsg.setText("Recently activity : none");
                        btn.setImageResource(R.drawable.hhh);
                        userHero22.setHelp(false);
                        saveObject();




                        // set title
                        alertDialogBuilder.setTitle("Do you want to send a msg to tell your friends you are fine now? ");

                        // set dialog message
                        alertDialogBuilder
                                .setMessage("Click yes to send!")
                                .setCancelable(false)
                                .setPositiveButton("Yes",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                // if this button is clicked, close
                                                // current activity
                                                //remove(smsNumber.getText().toString());
                                                String mm = "I am fine now. Don't worry";
                                                for (int i = 0; i < userHero22.getSMSList().size(); i++) {
                                                    sendSMS(userHero22.getSMSList().get(i), mm);
                                                }

                                            }
                                        })
                                .setNegativeButton("No",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,
                                                                int id) {
                                                // if this button is clicked, just close
                                                // the dialog box and do nothing
                                                // dialog.cancel();
                                            }
                                        });

                        // create alert dialog
                        AlertDialog alertDialog = alertDialogBuilder.create();

                        // show it
                        alertDialog.show();
                        //System.out.println("sdfsdfsfsdf");


                        Date time = new Date();
                        postToServer(identityNumber, String.valueOf(gpsTracker.getLatitude()), String.valueOf(gpsTracker.getLongitude()),"false",time.toString());
                    }
                    return true;
                }
            });

            onMainActivity=true;  // for the location textview in locationGPS class

            TextView locaton = (TextView) getActivity().findViewById(R.id.textView);
            if(!(gpsTracker ==null)){


                locaton.setText("your location is : " + String.valueOf(gpsTracker.getLatitude()) +" " + String.valueOf(gpsTracker.getLongitude()));
            }
            Button bt1 = (Button) getActivity().findViewById(R.id.button4);
            if(locaton.getText().toString().equals("your location is : 0.0 0.0")){
                bt1.setVisibility(View.VISIBLE);
            }
            else{
                bt1.setVisibility(View.GONE);
            }
            bt1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //when play is clicked show stop button and hide play button
                    getActivity().finish();
                    startActivity(getActivity().getIntent());

                }
            });


        }

    }

    public static class Fragment2 extends Fragment {

        private TextView mQuoteView = null;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_fragment2, container, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {

            super.onActivityCreated(savedInstanceState);
            String aboutUs= "This app is free for use,\nno term and policies,\n" +
                    "app is created by waihong , siew ";

            mQuoteView=  (TextView) getActivity().findViewById(R.id.aboutUsFragment);
            mQuoteView.setText(aboutUs);

        }



    }

    public static class Fragment1 extends ListFragment implements AdapterView.OnItemClickListener {

        String[] menutitles;
        TypedArray menuIcons;

        CustomAdapter adapter;
        private List<RowItem> rowItems;



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_fragment1, null, false);
        }

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {

            super.onActivityCreated(savedInstanceState);

            menutitles = getResources().getStringArray(R.array.titles);
            menuIcons = getResources().obtainTypedArray(R.array.icons);

            rowItems = new ArrayList<RowItem>();

            for (int i = 0; i < menutitles.length; i++) {
                RowItem items = new RowItem(menutitles[i], menuIcons.getResourceId(
                        i, -1));

                rowItems.add(items);
            }

            adapter = new CustomAdapter(getActivity(), rowItems);
            setListAdapter(adapter);
            getListView().setOnItemClickListener(this);

        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position,
                                long id) {

            Toast.makeText(getActivity(), menutitles[position], Toast.LENGTH_SHORT)
                    .show();

            if(position == 0){
                Fragment fragment = new shakeInvokeDeviceFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }

            if(position == 1){
                Fragment fragment = new ChangePasword();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }
            if(position==2){
                Fragment fragment = new messageFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            }
            if(position == 3){

                Intent i = new Intent(getActivity(), ttss.class);
                startActivity(i);

            //    Fragment fragment = new NewFeedsFragment();
              //  FragmentManager fragmentManager = getFragmentManager();
              //  fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }

            if(position ==4){
                Fragment fragment = new emergencyContactDetails();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            }
            if(position ==5 ){

                Fragment fragment = new contactPoliceFragment();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }
            if(position ==6 ){

                Fragment fragment = new smsRemoteAccess();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }
            if(position ==7 ){

                Fragment fragment = new Fragment4();
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
            }

        }


    }

    public static class smsRemoteAccess extends Fragment{
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.sms_remote_access_fragment, container, false);
        }


        @Override
        public void onActivityCreated(Bundle savedInstanceState) {

            super.onActivityCreated(savedInstanceState);

            TextView switchStatus;
            Switch mySwitch;

            switchStatus = (TextView) getActivity().findViewById(R.id.switch2);
            mySwitch = (Switch) getActivity().findViewById(R.id.switch2);


            mySwitch.setChecked(userHero22.isSMSRemote());

            mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {

                    if(isChecked){
                        userHero22.setSMSRemote(true);
                    }else{
                        userHero22.setSMSRemote(false);
                    }

                    saveObject();
                    System.out.println(userHero22.toString()+"new object value");



                }
            });

            final Button button = (Button) getActivity().findViewById(R.id.button2);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), instructionActivity.class);
                    startActivityForResult(intent, ADD_TODO_ITEM_REQUEST);
                }
            });

        }


    }

    public static class contactPoliceFragment extends Fragment{
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.contact_fragment, container, false);
        }


        @Override
        public void onActivityCreated(Bundle savedInstanceState) {

            super.onActivityCreated(savedInstanceState);
            TextView switchStatus;
            Switch mySwitch;
            TextView switchStatus3;
            Switch mySwitch3;

            switchStatus = (TextView) getActivity().findViewById(R.id.switch2);
            mySwitch = (Switch) getActivity().findViewById(R.id.switch2);

            switchStatus3 = (TextView) getActivity().findViewById(R.id.switch3);
            mySwitch3 = (Switch) getActivity().findViewById(R.id.switch3);

            mySwitch.setChecked(userHero22.isCallPolice());
            mySwitch3.setChecked(userHero22.isAutoInvoke());

            mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {

                    if(isChecked){
                        userHero22.setCallPolice(true);
                    }else{
                        userHero22.setCallPolice(false);
                    }

                    saveObject();
                    System.out.println(userHero22.toString()+"new object value");
                }
            });
            mySwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {

                    if(isChecked){
                        userHero22.setAutoInvoke(true);
                    }else{
                        userHero22.setAutoInvoke(false);
                    }

                    saveObject();
                    System.out.println(userHero22.toString()+"new object value");
                }
            });

            final Button button = (Button) getActivity().findViewById(R.id.button2);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    Intent intent = new Intent(getActivity(), changePoliceNumberActivity.class);
                    startActivityForResult(intent, ADD_TODO_ITEM_REQUEST);
                }
            });




        }


    }
    public static class Fragment3 extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_fragment3, container, false);
        }

    }
    public static class Fragment4 extends Fragment {

        private TextView mQuoteView = null;
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment

            return inflater.inflate(R.layout.fragment_fragment4, container, false);
        }
        @Override
        public void onActivityCreated(Bundle savedInstanceState) {

            super.onActivityCreated(savedInstanceState);


            String aboutUs= "Nowadays the world is getting depraved in law and order, we can never expect what will happen on the next moment, so it is always important to be alert and ready. At this crucial moment, spreading the help message as fast as possible may save your life. Therefore, I carried out this project corresponding with the problem above. The objective of this project is to develop an application which utilizes communication technology for seeking help. Using the application, the users maximize the possibility of getting rescued by invoking various functions of the application. For passive invocation, the mobile device detects certain magnitude level of vibration. \n" +
                    "This application targets on the teenage user group because today’s young people always have fun at night and only go home very late at night, this is the product that can help you and your parent  in emergency situation. The system will inform the device’s location to a list of contacts that you wish to send, by just double pressing the power button on the mobile device. The system is also allowed to detect a danger situation such as car crash etc. it automatically activate without pressing any buttons. \n" +
                    "We have also considered that user might be in a different situation in which the user is unable to reach the button and activate the function. We have taken 2 different scenario into our consideration when developing the application. First, in a car crash, user may fail to activate the application. Second, the user may find themselves being trapped and would like to activate the application secrecy. This application allows them to activate in different way such as pressing power button twice etc. This project aim is to develop an android application that help user to get rescued in a bad situation by sending text, ring the local police etc. \nThis project is based on the problem of corrupted law and order in some of the countries and the challenges faced by developing a system that fits in every single bad situation. In order to make the application more reliable, the developer should take in the plentiful considerations to avoid miscellaneous error. For instance, the differentiation has to be identified whether the device is dropped on ground or in the actual accident. \n" +
                    "Emergencies often mean that someone is in trouble or injured. An emergency help seeking application is a system that aids to help the users when accident occurs. As we know, at an emergency moment, every single second is critical. A lot of victims tend to loss their life because help couldn’t arrive on time. It can also deal with kids or teenager who always go out to have fun without considering their parents’ anxiety. Thus, this is the application that allows parents to be at ease.   \n" +
                    "There are several typical emergencies and disasters application using GPS tracking, SOS light etc. already existed on the market, but my project is a little bit different. By developing the application we have to consider all the different scenarios for instance user in a car crash, phone stolen etc. It is possible that user may in trouble, but unable to launch the application.  \n" +
                    "This report will mainly discuss different type of technologies available at the moment that might be useful to apply in the application, such as Global position system (GPS), mobile sensor etc. \n"; // can manipulate using substring also

            mQuoteView=  (TextView) getActivity().findViewById(R.id.aboutUsFragment);
            mQuoteView.setText(aboutUs);
        }
    }

    public static class messageFragment extends Fragment  {



        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.message_content_fragment, container, false);
        }



        public static String content = "";

        @Override
        public void onActivityCreated(Bundle savedInstanceState) {

            super.onActivityCreated(savedInstanceState);

            final EditText editText = (EditText)getActivity().findViewById(R.id.editTextmsg);
            editText.setText( MainActivity.userHero22.getHelpMsgContent(), TextView.BufferType.EDITABLE);

            content = editText.getText().toString();

            System.out.println("message details : " + content);



            final Button button = (Button) getActivity().findViewById(R.id.button5);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    MainActivity.userHero22.setHelpMsgContent(editText.getText().toString());
                    MainActivity.saveObject();

                }
            });


        }

    }
    public static emergencyAdapter adapterEm;
    public static class emergencyContactDetails  extends ListFragment {


        private ArrayList<String> smsList;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.emergency_fragment, container, false);
        }

        public void onActivityCreated(Bundle savedInstanceState) {

            super.onActivityCreated(savedInstanceState);

        smsList = userHero22.getSMSList();
       // smsList.add("0876140810");
            adapterEm = new emergencyAdapter(getActivity(), smsList);
            setListAdapter(adapterEm);


            Button more = (Button) getActivity().findViewById(R.id.button);
            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), addPhoneNumber.class);
                    startActivityForResult(intent, ADD_TODO_ITEM_REQUEST);
                }
            });
            Button get = (Button) getActivity().findViewById(R.id.button2);
            get.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
                    pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
                    startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
                }
            });




        }

        @Override
        public void onActivityResult(int reqCode, int resultCode, Intent data) {

            System.out.println("qweqweqeqweqeqeq");
            // Check which request it is that we're responding to
            if (reqCode == PICK_CONTACT_REQUEST) {
                // Make sure the request was successful
                if (resultCode == RESULT_OK) {
                    // Get the URI that points to the selected contact
                    Uri contactUri = data.getData();
                    // We only need the NUMBER column, because there will be only one row in the result
                    String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};

                    // Perform the query on the contact to get the NUMBER column
                    // We don't need a selection or sort order (there's only one result for the given URI)
                    // CAUTION: The query() method should be called from a separate thread to avoid blocking
                    // your app's UI thread. (For simplicity of the sample, this code doesn't do that.)
                    // Consider using CursorLoader to perform the query.
                    Cursor cursor = getActivity().getContentResolver().query(contactUri, projection, null, null, null);
                    cursor.moveToFirst();

                    // Retrieve the phone number from the NUMBER column
                    int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                    String number = cursor.getString(column);

                    // Do something with the phone number...



                    MainActivity.adapterEm.add(number);
                    MainActivity.saveObject();
                }
            }

        }
    }



            public static class shakeInvokeDeviceFragment extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.shackfragment, container, false);
        }
        public void onActivityCreated(Bundle savedInstanceState) {

            super.onActivityCreated(savedInstanceState);

            TextView switchStatus;
            Switch mySwitch;
            SeekBar seekBar;

            switchStatus = (TextView) getActivity().findViewById(R.id.switch1);
            mySwitch = (Switch) getActivity().findViewById(R.id.switch1);
            mySwitch.setChecked(userHero22.isShackInvoke());
            seekBar = (SeekBar) getActivity().findViewById(R.id.seekBar);
            seekBar.setProgress((userHero22.getLevelOfVibration()-6000)/40);

            mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {

                    if(isChecked){
                        userHero22.setShackInvoke(true);
                       // ArrayList<Date> times =  new ArrayList<Date>();
                       // times.add(new Date());
                       // userHero22 =  new userSetting(true,80,times,false,1,"0876140810",null,null);
                    }else{
                        userHero22.setShackInvoke(false);
                     //   userHero22 =  new userSetting(false,80,null,false,1,"0876140810",null,null);
                    }

                    saveObject();




                }
            });

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                int progress = 0;



                @Override

                public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {

                    progress = (progresValue*40)+6000;



                }




                @Override

                public void onStartTrackingTouch(SeekBar seekBar) {



                }



                @Override

                public void onStopTrackingTouch(SeekBar seekBar) {
                    userHero22.setLevelOfVibration(progress);
                    json = gson.toJson(userHero22);
                    editor.putString("userHero22", json);
                    editor.apply();
                    MyService.SHAKE_THRESHOLD =userHero22.getLevelOfVibration();

                }



            });

            System.out.println("level:" + userHero22.getLevelOfVibration());


        }







    }

    public static class ChangePasword extends Fragment implements View.OnClickListener {
        int counter = 0;
        TextView passwordone = null;
        TextView passwordtwo = null;
        TextView passwordthree = null;
        TextView passwordfour = null;
        TextView msg = null;
        boolean newPasswordOrNot = true;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            counter = 0;
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.password, container, false);
        }

        public void onActivityCreated(Bundle savedInstanceState) {

            super.onActivityCreated(savedInstanceState);


            msg = (TextView) getActivity().findViewById(R.id.message);

             passwordone = (TextView) getActivity().findViewById(R.id.passwordone);
             passwordtwo = (TextView) getActivity().findViewById(R.id.passwordteo);
             passwordthree = (TextView) getActivity().findViewById(R.id.paswordthree);
             passwordfour = (TextView) getActivity().findViewById(R.id.paswordfour);


            if (userHero22.getPassword().equals("no password")) {
                msg.setText("please insert a new password");
                newPasswordOrNot = true;
            }
            else{
                msg.setText("please insert current password");
                newPasswordOrNot = false;
            }

            final Button buttonNumber1 = (Button) getActivity().findViewById(R.id.one);
            final Button buttonNumber2 = (Button) getActivity().findViewById(R.id.two);
            final Button buttonNumber3 = (Button) getActivity().findViewById(R.id.three);
            final Button buttonNumber4 = (Button) getActivity().findViewById(R.id.four);
            final Button buttonNumber5 = (Button) getActivity().findViewById(R.id.five);
            final Button buttonNumber6 = (Button) getActivity().findViewById(R.id.six);
            final Button buttonNumber7 = (Button) getActivity().findViewById(R.id.seven);
            final Button buttonNumber8 = (Button) getActivity().findViewById(R.id.eight);
            final Button buttonNumber9 = (Button) getActivity().findViewById(R.id.nine);
            final Button buttonNumber0 = (Button) getActivity().findViewById(R.id.zero);
            final Button buttonNumberD = (Button) getActivity().findViewById(R.id.delbutton);
            final Button buttonNumberGo = (Button) getActivity().findViewById(R.id.okbutton);

            buttonNumber1.setOnClickListener(this);
            buttonNumber2.setOnClickListener(this);
            buttonNumber3.setOnClickListener(this);
            buttonNumber4.setOnClickListener(this);
            buttonNumber5.setOnClickListener(this);
            buttonNumber6.setOnClickListener(this);
            buttonNumber7.setOnClickListener(this);
            buttonNumber8.setOnClickListener(this);
            buttonNumber9.setOnClickListener(this);
            buttonNumber0.setOnClickListener(this);
            buttonNumberD.setOnClickListener(this);
            buttonNumberGo.setOnClickListener(this);


        }


        public void insertPasswordIntoTextView(String number) {

            if (!(counter > 4)) {

                if (counter == 0) {

                    passwordone.setText(number);
                } else if (counter == 1) {

                    passwordtwo.setText(number);
                } else if (counter == 2) {

                    passwordthree.setText(number);
                } else if (counter == 3) {

                    passwordfour.setText(number);
                }
                counter++;
            }

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.one:
                    // do stuff;
                    insertPasswordIntoTextView("1");
                    break;
                case R.id.two:
                    // do stuff;
                    insertPasswordIntoTextView("2");
                    break;
                case R.id.three:
                    // do stuff;
                    insertPasswordIntoTextView("3");
                    break;
                case R.id.four:
                    // do stuff;
                    insertPasswordIntoTextView("4");
                    break;
                case R.id.five:
                    // do stuff;
                    insertPasswordIntoTextView("5");
                    break;
                case R.id.six:
                    // do stuff;
                    insertPasswordIntoTextView("6");
                    break;
                case R.id.seven:
                    // do stuff;
                    insertPasswordIntoTextView("7");
                    break;
                case R.id.eight:
                    // do stuff;
                    insertPasswordIntoTextView("8");
                    break;
                case R.id.nine:
                    // do stuff;
                    insertPasswordIntoTextView("9");
                    break;
                case R.id.zero:
                    // do stuff;
                    insertPasswordIntoTextView("0");
                    break;
                case R.id.delbutton:
                    // do stuff;
                    counter=0;
                    insertPasswordIntoTextView("");
                    insertPasswordIntoTextView("");
                    insertPasswordIntoTextView("");
                    insertPasswordIntoTextView("");
                    counter=0;

                    break;
                case R.id.okbutton:
                    if(newPasswordOrNot==true){
                        String temp = (String) passwordone.getText()+passwordtwo.getText()+passwordthree.getText()+passwordfour.getText();
                        System.out.println("setting a new password" + temp);
                        userHero22.setPassword(temp);
                        saveObject();
                        System.out.println("userHero: " + userHero22.toString());
                        Toast.makeText(this.getActivity(),"password changed",Toast.LENGTH_SHORT).show();
                        Fragment fragment = new Fragment1();

                        FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
                    }
                    else{
                        String temp = (String) passwordone.getText()+passwordtwo.getText()+passwordthree.getText()+passwordfour.getText();
                        if(temp.equals(userHero22.getPassword())){
                            newPasswordOrNot=true;
                            msg.setText("insert a new password");
                            counter=0;
                            insertPasswordIntoTextView("");
                            insertPasswordIntoTextView("");
                            insertPasswordIntoTextView("");
                            insertPasswordIntoTextView("");
                            counter=0;
                        }

                    }



                    break;
            }
        }
    }



    public static void saveObject(){
       //userHero22 = new userSetting();
       json = gson.toJson(userHero22);
                    editor.putString("userHero22", json);
                    editor.apply();



    }
/*
    public static void retriveObject(){

        Gson gson = new Gson();
        String json = prefs.getString("userHero", "");
        userHero = gson.fromJson(json, userSetting.class);
    }

    */

    public static void postToServer(String number, String la, String lo, String OnOff, String time){


        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        try {
            //  HashMap<String, String> queryValues = new HashMap<String, String>();
            // queryValues.put("userName", "testing");
            // params.put("userName", queryValues.get("userName"));
            //params.put("1", "test");
            params.put("number", number);
            params.put("latitude", la);
            params.put("longitude", lo);
            params.put("emgOnOff", OnOff);
            params.put("happentime", time);

            client.post("http://egocart.net/testing/waihongsiew/searchSite/wel.php", params, new AsyncHttpResponseHandler() {

                @Override
                public void onStart() {
                    super.onStart();
                }

                @Override
                public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                      Throwable arg3) {
                    // TODO Auto-generated method stub
                    // Toast.makeText(getApplicationContext(), "failure...!", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                    // TODO Auto-generated method stub
                    //  Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                }

            });
        } catch(Exception e) {
            Log.d("MyApp", "File not found!!!");


        }

    }

    @Override
    public void onPause() {
        super.onPause();
        onMainActivity = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        onMainActivity = false;
    }

    @Override
    public void onStop() {
        super.onStop();
        onMainActivity = false;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        onMainActivity = false;
    }

    public static class NewFeedsFragment  extends Fragment {


        private ArrayList<String> smsList;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.newfeedsfragment, container, false);
        }

        public void onActivityCreated(Bundle savedInstanceState) {

            super.onActivityCreated(savedInstanceState);


            Button bt = (Button) getActivity().findViewById(R.id.buttonM);
            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     Intent i = new Intent(getActivity(), NewActivity.class);
                     startActivity(i);

                }
            });
        }
    }

    @Override
    public void onBackPressed() {
    }



}