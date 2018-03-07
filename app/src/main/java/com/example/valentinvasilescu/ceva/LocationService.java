package com.example.valentinvasilescu.ceva;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.IBinder;
import android.support.annotation.Nullable;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;


import static com.example.valentinvasilescu.ceva.R.id.location;

/**
 * Created by Valentin Vasilescu on 8/22/2017.
 */

public class LocationService extends Service {
    private LocationListener listener;
    private LocationManager locationManager;
    private final String TAG = "Service";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                Log.d(TAG,"Location has been updated");

                Intent i = new Intent("location_update");
                i.putExtra("coordinates", "\n " + location.getLatitude() + "  " + location.getLongitude() + "\n");
                Log.d(TAG,"Broadcast sent");
                sendBroadcast(i);

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        };


        locationManager = (LocationManager) getBaseContext().getSystemService(Context.LOCATION_SERVICE);

        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0, 0, listener);
    }

}
