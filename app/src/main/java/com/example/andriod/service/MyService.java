package com.example.andriod.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.android.navigationdrawerexample.FullscreenTriggerActivity;
import com.example.android.navigationdrawerexample.MainActivity;
import com.example.android.navigationdrawerexample.userSetting;
import com.google.gson.Gson;

import java.util.concurrent.atomic.AtomicBoolean;

public class MyService extends Service implements SensorListener, LocationListener {
    public static float speed = 8000;
    SharedPreferences sharedpreferences;
    static SharedPreferences.Editor editor = null;
    public static boolean cancel = false;
    public static boolean MainActivityOpen = true;

    private static final int FORCE_THRESHOLD = 350;
    private static final int TIME_THRESHOLD = 100;
    private static final int SHAKE_TIMEOUT = 500;
    private static final int SHAKE_DURATION = 1000;
    private static final int SHAKE_COUNT = 3;

    private SensorManager mSensorMgr;
    private float mLastX=-1.0f, mLastY=-1.0f, mLastZ=-1.0f;
    private long mLastTime;
    //private OnShakeListener mShakeListener;
    private Context mContext;
    private int mShakeCount = 0;
    private long mLastShake;
    private long mLastForce;

    private float currentAcceleration;
    public static Sensor mAccelerometer;
    public static SensorManager sensorMgr;

    MyService locationService;
    LocationManager locationManager;
    Location lastLocation;
    private final String TAG = "LocationService" ;
    boolean isGPSEnabled = true;
    boolean isNetworkEnabled =true;

    public static userSetting userHeroService =  null;
    static Gson gsonService = new Gson();
    static  String jsonService = null;

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {

        super.onCreate();
        sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorMgr.registerListener(this,
                SensorManager.SENSOR_ACCELEROMETER,
                SensorManager.SENSOR_DELAY_GAME);

        locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);

        isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        //getting network status
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGPSEnabled && !isNetworkEnabled)
        {
            // no network provider is enabled
            System.out.println("GPS and internet closed ");
            Toast.makeText(this, "no GPS and Internet", Toast.LENGTH_SHORT).show();
        }


        Log.e("In on receive", "In Method:  service started");



    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


    //    return super.onStartCommand(intent, flags, startId);
        return START_STICKY;


    }
    public static long lastUpdate;
    public static float x;
    public static float y;
    public static float z;
    public static float last_x;
    public static float last_y;
    public static float last_z;
    public static  int SHAKE_THRESHOLD = 6500;

    @Override
    public void onSensorChanged(int sensor, float[] values) {

        // currentAcceleration = mAccelerometer.getPower();
        //   System.out.println("sensor: " + values[sensorMgr.DATA_X]);

        if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
            long curTime = System.currentTimeMillis();
            // only allow one update every 100ms.
            if ((curTime - lastUpdate) > 100) {
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                x = values[SensorManager.DATA_X];
                y = values[SensorManager.DATA_Y];
                z = values[SensorManager.DATA_Z];

                speed = Math.abs(x+y+z - last_x -last_y-last_z) / diffTime * 10000;



                String MyPREFERENCES = "userSettingData" ;

             //   System.out.println("abc+"+userHeroService);
                 sharedpreferences= getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);



                jsonService = sharedpreferences.getString("userHero22", "");
                userHeroService = gsonService.fromJson(jsonService, userSetting.class);
                editor = sharedpreferences.edit();

                SHAKE_THRESHOLD = userHeroService.getLevelOfVibration();
               // System.out.println(SHAKE_THRESHOLD);
    //System.out.println("FullscreenTriggerActivity.TrigerActivityOpen: " +FullscreenTriggerActivity.TrigerActivityOpen);
        //        System.out.println("MainActivity.userHero22.isHelp()"+FullscreenTriggerActivity.funtionOpen);
                if (speed > SHAKE_THRESHOLD && FullscreenTriggerActivity.TrigerActivityOpen == false
                     &&userHeroService.isHelp()==false&&userHeroService.isShackInvoke()==true  ) {
                   // Log.d("sensor", "shake detected w/ speed: " + speed);
                    //Toast.makeText(this, "shake detected w/ speed: " + speed, Toast.LENGTH_SHORT).show();
                    Intent dialogIntent = new Intent(getBaseContext(), FullscreenTriggerActivity.class);
                    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplication().startActivity(dialogIntent);


                }
                last_x = x;
                last_y = y;
                last_z = z;
            }
        }
    }


    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {
        //  System.out.println("sensor: " + accuracy);
    }


    @Override
    public void onLocationChanged(Location location) {
       // Toast.makeText(this, "your speed is: " + location.getSpeed(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public static void saveObject(){
        //userHero22 = new userSetting();
        jsonService = gsonService.toJson(userHeroService);
        editor.putString("userHero22", jsonService);
        editor.apply();



    }
}
