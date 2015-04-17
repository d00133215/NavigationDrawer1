package com.example.android.navigationdrawerexample;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.andriod.service.MyService;


public class unlockActivity extends Activity implements View.OnClickListener {

    int counter = 0;
    TextView passwordone = null;
    TextView passwordtwo = null;
    TextView passwordthree = null;
    TextView passwordfour = null;
    TextView msg = null;
    boolean newPasswordOrNot = true;
    String numberPassword = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unlock);
        ActionBar bar = getActionBar();
        bar.hide();

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);

        window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);

        counter = 0;


        msg = (TextView) findViewById(R.id.message);

        passwordone = (TextView) findViewById(R.id.passwordone);
        passwordtwo = (TextView) findViewById(R.id.passwordteo);
        passwordthree = (TextView) findViewById(R.id.paswordthree);
        passwordfour = (TextView)findViewById(R.id.paswordfour);


        if (MainActivity.userHero22.getPassword().equals("no password")) {
            msg.setText("password not set");
            newPasswordOrNot = true;
        }
        else{
            msg.setText("please insert password");
            newPasswordOrNot = false;
        }

        final Button buttonNumber1 = (Button) findViewById(R.id.one);
        final Button buttonNumber2 = (Button) findViewById(R.id.two);
        final Button buttonNumber3 = (Button) findViewById(R.id.three);
        final Button buttonNumber4 = (Button) findViewById(R.id.four);
        final Button buttonNumber5 = (Button) findViewById(R.id.five);
        final Button buttonNumber6 = (Button) findViewById(R.id.six);
        final Button buttonNumber7 = (Button) findViewById(R.id.seven);
        final Button buttonNumber8 = (Button) findViewById(R.id.eight);
        final Button buttonNumber9 = (Button) findViewById(R.id.nine);
        final Button buttonNumber0 = (Button) findViewById(R.id.zero);
        final Button buttonNumberD = (Button) findViewById(R.id.delbutton);
        final Button buttonNumberGo = (Button) findViewById(R.id.okbutton);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_unlock, menu);
        return true;
    }

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

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.one:
                // do stuff;
                insertPasswordIntoTextView("*");
                numberPassword = numberPassword +"1";
                break;
            case R.id.two:
                // do stuff;
                insertPasswordIntoTextView("*");
                numberPassword = numberPassword +"2";
                break;
            case R.id.three:
                // do stuff;
                insertPasswordIntoTextView("*");
                numberPassword = numberPassword +"3";
                break;
            case R.id.four:
                // do stuff;
                insertPasswordIntoTextView("*");
                numberPassword = numberPassword +"4";
                break;
            case R.id.five:
                // do stuff;
                insertPasswordIntoTextView("*");
                numberPassword = numberPassword +"5";
                break;
            case R.id.six:
                // do stuff;
                insertPasswordIntoTextView("*");
                numberPassword = numberPassword +"6";
                break;
            case R.id.seven:
                // do stuff;
                insertPasswordIntoTextView("*");
                numberPassword = numberPassword +"7";
                break;
            case R.id.eight:
                // do stuff;
                insertPasswordIntoTextView("*");
                numberPassword = numberPassword +"8";
                break;
            case R.id.nine:
                // do stuff;
                insertPasswordIntoTextView("*");
                numberPassword = numberPassword +"9";
                break;
            case R.id.zero:
                // do stuff;
                insertPasswordIntoTextView("*");
                numberPassword = numberPassword +"0";
                break;
            case R.id.delbutton:
                // do stuff;
                counter=0;
                insertPasswordIntoTextView("");
                insertPasswordIntoTextView("");
                insertPasswordIntoTextView("");
                insertPasswordIntoTextView("");
                numberPassword = "";
                counter=0;

                break;
            case R.id.okbutton:
                if(newPasswordOrNot==true){

                }
                else{
                  //check
                  //  String temp = (String) passwordone.getText()+passwordtwo.getText()+passwordthree.getText()+passwordfour.getText();
                    String temp = numberPassword;
                    if(temp.equals(MainActivity.userHero22.getPassword())){
                       //disable the call function
                        MyService.cancel=false;
                        Intent dialogIntent = new Intent(getBaseContext(), MainActivity.class);
                        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplication().startActivity(dialogIntent);
                        FullscreenTriggerActivity.FSTFunction = false;
                        System.out.println("disable the call function");
                    }
                }



                break;
        }
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
    public void onBackPressed() {
        Intent dialogIntent = new Intent(getBaseContext(), FullscreenTriggerActivity.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(dialogIntent);
    }
}
