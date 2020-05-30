package com.seancoyle.weatherapp.requests;

import com.seancoyle.weatherapp.models.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {


    @GET("/data/2.5/weather")
    Call<Weather> getWeather(
            @Query("id") int BELFAST_ID,
            @Query("APPID") String API_KEY
    );


}
