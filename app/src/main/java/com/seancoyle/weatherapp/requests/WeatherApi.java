package com.seancoyle.weatherapp.requests;

import com.seancoyle.weatherapp.models.WeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {


    @GET("/data/2.5/forecast")
    Call<WeatherResponse>getWeather(
            @Query("id") int locationId,
            @Query("APPID") String apiKey,
            @Query("units") String metric,
            @Query("cnt") int count
    );


}
