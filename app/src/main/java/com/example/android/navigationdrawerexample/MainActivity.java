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

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.app.SearchManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.telephony.SmsManager;
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
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.google.gson.Gson;

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

    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private String[] mPlanetTitles;
   // private userSetting userHero =  null;
    private static userSetting userHero22 =  null;
    static Gson gson = new Gson();
    static  String json = null;
    static SharedPreferences.Editor editor = null;
    // final  SharedPreferences prefs = getPreferences(MODE_PRIVATE);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

       // userHero =  new userSetting(true,80,null,false,1,"0876140810",null,null);

      //  System.out.println(userHero+"  asdasdasdasdasdasdasdasdas");

        final  SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        /*
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

         json = prefs.getString("userHero22", "");
        userHero22 = gson.fromJson(json, userSetting.class);
        System.out.println(userHero22+"  testing share prefrence");
        editor = prefs.edit();
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
    }








    public void helpPlease(View view) {
        String number = "23454568678";
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + number));
        startActivity(intent);
        sendSMS("5556", "Hi You got a message!");


    }

    private void sendSMS(String phoneNumber, String message)
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
        Fragment fragment = null;

        if(position==0){
             fragment = new Fragment0();

        }
        else if(position==1){
            fragment = new Fragment1();

        }
        else if(position==2){
            fragment = new Fragment2();

        }
        else if(position==3){
            fragment = new Fragment3();

        }
        else if(position==4){
            fragment = new Fragment4();

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

    }

    public static class Fragment2 extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {


            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.fragment_fragment2, container, false);
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
            seekBar.setProgress(userHero22.getLevelOfVibration());

            mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                @Override
                public void onCheckedChanged(CompoundButton buttonView,
                                             boolean isChecked) {

                    if(isChecked){
                        userHero22.setShackInvoke(true);
                        userHero22 =  new userSetting(true,80,null,false,1,"0876140810",null,null);
                    }else{
                        userHero22.setShackInvoke(false);
                        userHero22 =  new userSetting(false,80,null,false,1,"0876140810",null,null);
                    }
                    json = gson.toJson(userHero22);
                    editor.putString("userHero22", json);
                    editor.apply();

                    System.out.println(userHero22.toString()+"asdasdasdasd");



                }
            });

            seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                int progress = 0;



                @Override

                public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {

                    progress = progresValue;


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

                }


            });


        }







    }

    public static class ChangePasword extends Fragment {
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // Inflate the layout for this fragment
            return inflater.inflate(R.layout.password, container, false);
        }
    }

    /*public void saveObject(){
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userHero);
        editor.putString("userHero", json);
        editor.commit();

    }

    public void retriveObject(){

        Gson gson = new Gson();
        String json = prefs.getString("userHero", "");
        userHero = gson.fromJson(json, userSetting.class);
    }*/

}