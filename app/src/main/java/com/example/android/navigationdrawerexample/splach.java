package com.example.android.navigationdrawerexample;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;



public class splach extends Activity {


    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        ActionBar bar = getActionBar();
        bar.hide();
        bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#AB9528")));
        bar.setTitle(Html.fromHtml("<font>Developed by Wai Hong </font>"));


        setContentView(R.layout.activity_splach);
       // setContentView(R.layout.activity_fullscreen_activity_strat);

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);


        new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

            @Override
            public void run() {

                Intent i = new Intent(splach.this, MainActivity.class);
                startActivity(i);


                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}

