package com.example.android.navigationdrawerexample;

import com.example.andriod.service.MyService;
import com.example.android.navigationdrawerexample.util.SystemUiHider;
import com.google.gson.Gson;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class FullscreenTriggerActivity extends Activity {
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    public static boolean FSTFunction = true;
    private static final boolean AUTO_HIDE = true;
    public static TextView ttxx = null;
    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     *
     *
     */
    public static boolean TrigerActivityOpen = false;
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * If set, will toggle the system UI visibility upon interaction. Otherwise,
     * will show the system UI visibility upon interaction.
     */
    private static final boolean TOGGLE_ON_CLICK = true;

    /**
     * The flags to pass to {@link SystemUiHider#getInstance}.
     */
    private static final int HIDER_FLAGS = SystemUiHider.FLAG_HIDE_NAVIGATION;
    public static   int second = 90;


    SoundPool sp = null;
    MediaPlayer mPlayer = null;
    /**
     * The instance of the {@link SystemUiHider} for this activity.
     */
    private SystemUiHider mSystemUiHider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TrigerActivityOpen = true;



         sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

        /** soundId for Later handling of sound pool **/
        int soundId = sp.load(this, R.raw.sounds, 1); // in 2nd param u have to pass your desire ringtone

        sp.play(soundId, 1, 1, 0, 0, 1);

         mPlayer = MediaPlayer.create(this, R.raw.sounds); // in 2nd param u have to pass your desire ringtone
        //mPlayer.prepare();
        mPlayer.start();




        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ActionBar bar = getActionBar();
        bar.hide();

        setContentView(R.layout.activity_fullscreen_trigger);
        ttxx = (TextView)findViewById(R.id.fullscreen_content);

        final View controlsView = findViewById(R.id.fullscreen_content_controls);
        final View contentView = findViewById(R.id.fullscreen_content);

        // Set up an instance of SystemUiHider to control the system UI for
        // this activity.
        mSystemUiHider = SystemUiHider.getInstance(this, contentView, HIDER_FLAGS);
        mSystemUiHider.setup();
        mSystemUiHider
                .setOnVisibilityChangeListener(new SystemUiHider.OnVisibilityChangeListener() {
                    // Cached values.
                    int mControlsHeight;
                    int mShortAnimTime;

                    @Override
                    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
                    public void onVisibilityChange(boolean visible) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                            // If the ViewPropertyAnimator API is available
                            // (Honeycomb MR2 and later), use it to animate the
                            // in-layout UI controls at the bottom of the
                            // screen.
                            if (mControlsHeight == 0) {
                                mControlsHeight = controlsView.getHeight();
                            }
                            if (mShortAnimTime == 0) {
                                mShortAnimTime = getResources().getInteger(
                                        android.R.integer.config_shortAnimTime);
                            }
                            controlsView.animate()
                                    .translationY(visible ? 0 : mControlsHeight)
                                    .setDuration(mShortAnimTime);
                        } else {
                            // If the ViewPropertyAnimator APIs aren't
                            // available, simply show or hide the in-layout UI
                            // controls.
                            controlsView.setVisibility(visible ? View.VISIBLE : View.GONE);
                        }

                        if (visible && AUTO_HIDE) {
                            // Schedule a hide().
                            delayedHide(AUTO_HIDE_DELAY_MILLIS);
                        }
                    }
                });

        // Set up the user interaction to manually show or hide the system UI.
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TOGGLE_ON_CLICK) {
                    mSystemUiHider.toggle();
                } else {
                    mSystemUiHider.show();
                }
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
       // findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);

        final ImageButton yes = (ImageButton)findViewById(R.id.imageButton2);
        final ImageButton no = (ImageButton)findViewById(R.id.imageButton3);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                helpFunction();
                mPlayer.stop();
            }
        });
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cancelFunction(second);
                mPlayer.stop();
            }
        });
        final Handler h = new Handler();

        h.post(new Runnable() {

            @Override
            public void run() {

                String countDown = String.valueOf(second--);
                ttxx.setText("0:"+countDown+"ms");
                System.out.println("12312312312312310:"+countDown+"ms");

                h.postDelayed(this, 1000);
                if(FSTFunction ==false){
                    h.removeCallbacks(this);
                }
                if(second<0){
                    helpFunction();
                    h.removeCallbacks(this);
                }
            }
        });


        if(second<0){
            ttxx.setText("System activated");
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }










    }

    public void cancelFunction(int number){
     //   Intent intent = new Intent(this, unlockActivity.class);
     //   startActivity(intent);
      Intent dialogIntent = new Intent(getBaseContext(), MainActivity.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       getApplication().startActivity(dialogIntent);

        MyService.cancel =true;
    }

    public  void helpFunction() {

        if(MainActivity.userHero22.isHelp()==false) {
            MainActivity.userHero22.setHelp(true);
            MainActivity.saveObject();

            String number = MainActivity.userHero22.getPoliceNumber();
            Intent intent = new Intent(Intent.ACTION_CALL);
            intent.setData(Uri.parse("tel:" + number));
            startActivity(intent);
            for(int i = 0 ; i < MainActivity.userHero22.getSMSList().size();i++) {
                MainActivity.sendSMS(MainActivity.userHero22.getSMSList().get(i), MainActivity.userHero22.getHelpMsgContent());
            }


            Date time = new Date();
            MainActivity.postToServer(MainActivity.identityNumber, String.valueOf(MainActivity.gpsTracker.getLatitude()), String.valueOf(MainActivity.gpsTracker.getLongitude()), "true", time.toString());
            finish();
        }
        else{
            MainActivity.userHero22.setHelp(false);
            MainActivity.saveObject();
            Date time = new Date();
            MainActivity.postToServer(MainActivity.identityNumber, String.valueOf(MainActivity.gpsTracker.getLatitude()), String.valueOf(MainActivity.gpsTracker.getLongitude()), "false", time.toString());
        }

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }


    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            mSystemUiHider.hide();
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void onPause() {
        super.onPause();
        TrigerActivityOpen = false;
    }

    @Override
    public void onResume() {
        super.onResume();
        TrigerActivityOpen = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        TrigerActivityOpen = false;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        TrigerActivityOpen = false;
    }



}
