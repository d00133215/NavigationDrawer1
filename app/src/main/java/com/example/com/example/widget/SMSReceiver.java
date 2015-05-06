package com.example.com.example.widget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.example.andriod.service.MyService;
import com.example.android.navigationdrawerexample.MainActivity;
import com.example.android.navigationdrawerexample.locationGPS;

/**
 * Created by waihong on 15/04/2015.
 */
public class SMSReceiver extends BroadcastReceiver {

    // Get the object of SmsManager
    final SmsManager sms = SmsManager.getDefault();

    public void onReceive(Context context, Intent intent) {

        // Retrieves a map of extended data from the intent.
        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: " + senderNum + "; message: " + message);

                    if(message.contains("find") && message.contains(MyService.userHeroService.getPassword()) && MyService.userHeroService.isSMSRemote()==true) {

                        TelephonyManager telemamanger = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
                        String getSimSerialNumber = telemamanger.getSimSerialNumber();

                        MainActivity.gpsTracker = new locationGPS(context);
                        String locationaddress = MainActivity.gpsTracker.getAddressLine(context) + " " + MainActivity.
                                gpsTracker.getLocality(context) + " " + MainActivity.gpsTracker.getCountryName(context) + " " +
                                MainActivity.gpsTracker.getPostalCode(context) + " Lat: " +MainActivity.gpsTracker.getLatitude()+" Long: " +
                                MainActivity.gpsTracker.getLongitude()+
                                " sim number: "+ getSimSerialNumber ;

                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(senderNum, null, locationaddress, null, null);

                        // Show Alert
                        int duration = Toast.LENGTH_LONG;
                      //  Toast toast = Toast.makeText(context,
                      //          "senderNum: "+ senderNum + ", message: " + message + "location: " + locationaddress, duration);
                      //  toast.show();
                        Toast toast = Toast.makeText(context,
                                "SMS", duration);
                        toast.show();
                    }
                    if(message.contains("activate") && message.contains(MyService.userHeroService.getPassword()) && MyService.userHeroService.isSMSRemote()==true) {
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context,
                                "Help function open", duration);
                        toast.show();
                        MyService.userHeroService.setHelp(true);
                        MyService.saveObject();
                    }
                    if(message.contains("cancel") && message.contains(MyService.userHeroService.getPassword()) && MyService.userHeroService.isSMSRemote()==true) {
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(context,
                                "Help function close", duration);
                        toast.show();
                        MyService.userHeroService.setHelp(false);
                        MyService.saveObject();
                    }







                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" +e);

        }
    }
}