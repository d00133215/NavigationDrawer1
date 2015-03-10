package com.example.android.navigationdrawerexample;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class addPhoneNumber extends Activity {
    EditText text = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone_number);

        text = (EditText) findViewById(R.id.editText);

        Button more = (Button) findViewById(R.id.button3);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(text.getText().equals("")){

                        Toast.makeText(addPhoneNumber.this, "please insert a valid number", Toast.LENGTH_SHORT);
                    }
                    else {
                        String number = text.getText().toString();
                       // MainActivity.userHero22.addSMSList(number); //put in the adpater class
                        MainActivity.adapterEm.add(number);
                        MainActivity.saveObject();
                        finish();

                    }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_phone_number, menu);
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
}
