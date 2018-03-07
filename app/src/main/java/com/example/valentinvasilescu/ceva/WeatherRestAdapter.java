package com.example.valentinvasilescu.ceva;

import retrofit.RestAdapter;

/**
 * Created by Valentin Vasilescu on 8/18/2017.
 */

public class WeatherRestAdapter {
    protected RestAdapter mRestAdapter;
    protected WeatherApi mApi;
    static final String WEATHER_URL="http://api.openweathermap.org";
    static final String OPEN_WEATHER_API = "51337ba29f38cb7a5664cda04d84f4cd";

    public WeatherRestAdapter() {
        mRestAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(WEATHER_URL)
                .build();
        mApi = mRestAdapter.create(WeatherApi.class); // create the interface
    }
    public WeatherData testWeatherApiSync(String city) {
        WeatherData result;
        result = mApi.getWeatherFromApiSync(city,OPEN_WEATHER_API);
        return result;
    }
}
