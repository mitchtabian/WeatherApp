package com.seancoyle.weatherapp.requests;

import com.seancoyle.weatherapp.requests.responses.WeatherResponse;
import com.seancoyle.weatherapp.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {


    @GET("/data/2.5/weather")
    Call<WeatherResponse> getWeather(
            @Query("id") String id,
            @Query("APPID") String api_key
    );


}
