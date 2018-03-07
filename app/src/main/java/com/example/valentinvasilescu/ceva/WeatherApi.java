package com.example.valentinvasilescu.ceva;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Valentin Vasilescu on 8/18/2017.
 */

public interface WeatherApi {
    @GET("/data/2.5/weather")
    WeatherData getWeatherFromApiSync (
            @Query("q") String cityName,
            @Query("APPID") String appId);
}

