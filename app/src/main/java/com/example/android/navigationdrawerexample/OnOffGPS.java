package com.example.android.navigationdrawerexample;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

/**
 * Created by waihong on 13/03/2015.
 */
public class OnOffGPS {

    private final Context ctx;



    public OnOffGPS(Context context)
    {
        this.ctx = context;

    }



    public void turnGPSOn()
    {
        try {
            Intent intent = new Intent("android.location.GPS_ENABLED_CHANGE");
            intent.putExtra("enabled", true);

            this.ctx.sendBroadcast(intent);

            String provider = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            if (!provider.contains("gps")) { //if gps is disabled
                final Intent poke = new Intent();
                poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
                poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
                poke.setData(Uri.parse("3"));
                this.ctx.sendBroadcast(poke);


            }
        }
        catch(Exception e){

        }
    }
    // automatic turn off the gps
    public void turnGPSOff()
    {
        String provider = Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
        if(provider.contains("gps")){ //if gps is enabled
            final Intent poke = new Intent();
            poke.setClassName("com.android.settings", "com.android.settings.widget.SettingsAppWidgetProvider");
            poke.addCategory(Intent.CATEGORY_ALTERNATIVE);
            poke.setData(Uri.parse("3"));
            this.ctx.sendBroadcast(poke);
        }
    }
}
