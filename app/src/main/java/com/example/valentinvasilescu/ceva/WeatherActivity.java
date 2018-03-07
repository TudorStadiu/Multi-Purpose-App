package com.example.valentinvasilescu.ceva;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.JsonWriter;
import android.widget.EditText;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

import static android.R.attr.data;
import static android.R.id.message;
import static java.lang.System.out;

public class WeatherActivity extends AppCompatActivity {
    //protected final String TAG = getClass().getSimpleName();
    private RetainedAppData mRetainedAppData;
    private EditText    mInputCityName;
    private TextView mCityNameTextView;
    private TextView    mCountryNameTextView;
    private TextView    mCoordsTextView;
    private TextView    mTempTextView;
    private TextView    mSunriseTextView;
    private TextView    mSunsetTextView;
    private StorageReference mStorageRef;
    private final String TAG = "WeatherActivity";

    private static String KEY_WeatherData = "WeatherData";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        Log.d(TAG, "FFS");
        mInputCityName = (EditText) findViewById(R.id.input_city_id);
        mInputCityName = (EditText) findViewById(R.id.input_city_id);
        mCityNameTextView = (TextView) findViewById(R.id.city_name_id);
        mCountryNameTextView = (TextView) findViewById(R.id.country_name_id);
        mCoordsTextView = (TextView) findViewById(R.id.coords_id);
        mTempTextView = (TextView) findViewById(R.id.temp_id);
        mSunriseTextView = (TextView) findViewById(R.id.sunrise_id);
        mSunsetTextView = (TextView) findViewById(R.id.sunset_id);

        // salveaza datele
        if (savedInstanceState != null) {
            if (getLastCustomNonConfigurationInstance() != null) {
                mRetainedAppData = (RetainedAppData) getLastCustomNonConfigurationInstance();
            }
        } else {
            mRetainedAppData = new RetainedAppData();
        }

        mRetainedAppData.setAppContext(this);
        if (mRetainedAppData.mData != null) {
            updateUXWithWeatherData(mRetainedAppData.mData);

        }



    }

    public void writeWeatherData(WeatherData tudor){
        Gson gson = new Gson();
        String jsonUserData = gson.toJson(tudor);
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor prefsEditor = mPrefs.edit();
        prefsEditor.putString(KEY_WeatherData, jsonUserData);
        prefsEditor.commit();
        //putString(KEY_SummaryResponse ,jsonUserData);
    }

    public WeatherData getWeatherData(){
        Gson gson = new Gson();
        Type type = new TypeToken<WeatherData>(){}.getType();
        SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
        String data = mPrefs.getString(KEY_WeatherData,"");
        WeatherData weatherResponse ;
        if (data != null){
            weatherResponse = gson.fromJson(data, type);
            return  weatherResponse ;
        }
        else{
            weatherResponse = null ;
            return  weatherResponse ;
        }
    }
    void DisplayData() {

        DateFormat dfLocalTz = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.UK);
        Date sunriseTime = new Date(getWeatherData().getSunrise() * 1000);
        Date sunsetTime = new Date(getWeatherData().getSunset() * 1000);
        mCityNameTextView.append(getWeatherData().getName());
        mCountryNameTextView.append(getWeatherData().getCountry());
        mCoordsTextView.append(getWeatherData().getLat()+" / "+getWeatherData().getLon());
        mTempTextView.append("25 C");
        mSunriseTextView.append(dfLocalTz.format(sunriseTime));
        mSunsetTextView.append(dfLocalTz.format(sunsetTime));


    }



    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return mRetainedAppData;
    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public void expandWeatherSync(View v) {
        String city = mInputCityName.getText().toString();
        if (city.isEmpty()) {
            Toast.makeText(getApplicationContext(),"No city specified.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        mRetainedAppData.runRetrofitTestSync(city);
    }





    void updateUXWithWeatherData (final WeatherData data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                DateFormat dfLocalTz = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.UK);
                Date sunriseTime = new Date(data.getSunrise() * 1000);
                Date sunsetTime = new Date(data.getSunset() * 1000);
                String big = data.getName()+"`"+data.getCountry()+"`"+"(" + data.getLat() + "," + data.getLon() + ")"+"`"+(data.getTemp() - 273.15)+"`"+dfLocalTz.format(sunriseTime)+"`"+ dfLocalTz.format(sunsetTime);
                TextView str= (TextView) findViewById(R.id.bigdata);
                str.append(big);
                Gson gson = new Gson();
                String result = gson.toJson(data);
                SharedPreferences mPrefs = getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor prefsEditor = mPrefs.edit();
                prefsEditor.putString("info", result);
                prefsEditor.commit();
                writeWeatherData(data);
                DisplayData();





            }
        });
    }
    private static class RetainedAppData {
        private  WeakReference<WeatherActivity> mActivityRef;
        //protected final String TAG = "RTD";
        private WeatherData mData; // date rerceptionate
        private AtomicBoolean mInProgress = new AtomicBoolean(false);
        private WeatherRestAdapter mWeatherRestAdapter;

        public void runRetrofitTestSync (final String city) {

            if ((mActivityRef.get() != null) && (mInProgress.get())) {
                Toast.makeText(mActivityRef.get(),"Weather fetch in progress.",
                        Toast.LENGTH_LONG).show();

                return;
            }

            if (mWeatherRestAdapter == null)
                mWeatherRestAdapter = new WeatherRestAdapter();

            mInProgress.set(true);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        WeatherData data = mWeatherRestAdapter.testWeatherApiSync(city);
                        mData = data;
                        if (mActivityRef.get() != null) {
                            mActivityRef.get().updateUXWithWeatherData(mData);

                        }
                    } catch (Exception ex) {
                        //Log.e(TAG, "Sync: exception", ex);

                    } finally {
                        mInProgress.set(false);
                    }
                }
            }).start();
        }
        void setAppContext (WeatherActivity ref) {
            mActivityRef = new WeakReference<>(ref);
        }
    }
}
