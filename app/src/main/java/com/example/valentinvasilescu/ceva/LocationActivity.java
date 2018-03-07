package com.example.valentinvasilescu.ceva;

import android.*;
import android.Manifest;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class LocationActivity extends AppCompatActivity {

    //private Button getlocation;
    //private TextView coordonate;
    //private LocationManager locationManager;
    // private LocationListener locationListener;
    LocationListener locationListener;
    LocationManager locationManager;
    private BroadcastReceiver broadcastReceiver;
    TextView text;
    private String altcv = null;
    //StringBuilder s = new StringBuilder(100);
    TextView text1;
    int k = 0;

    private final String TAG = "LocationActivity";

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        if (broadcastReceiver == null) {
            broadcastReceiver = new BroadcastReceiver() {
                @Override
                public void onReceive(Context context, Intent intent) {
                    Log.d(TAG, "Text has been updated");
                    text.append("\n" +intent.getExtras().get("coordinates"));

                }
            };
        }

        registerReceiver(broadcastReceiver, new IntentFilter("location_update"));
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent i = new Intent(this,LocationService.class);
        startService(i);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        text = (TextView) findViewById(R.id.TextView);
        text1 = (TextView) findViewById(R.id.TextView1);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        altcv = getSharedPreferences("pref", MODE_PRIVATE).getString("coord", altcv);
        k = getSharedPreferences("pref", MODE_PRIVATE).getInt("nr", k);
        text1.append(altcv);
        k++;

        String cv = text.getText().toString();
        altcv = altcv.concat(cv);
        SharedPreferences settings = getSharedPreferences("pref", MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        if (k > 10) {
            altcv = cv;
        }
        editor.putString("coord", altcv);
        editor.commit();
        editor.putInt("nr", k);


        };


    }



