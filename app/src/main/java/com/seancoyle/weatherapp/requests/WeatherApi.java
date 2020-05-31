package com.seancoyle.weatherapp.requests;

import androidx.lifecycle.LiveData;

import com.seancoyle.weatherapp.models.WeatherResponse;
import com.seancoyle.weatherapp.responses.ApiResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {


    @GET("/data/2.5/forecast")
    LiveData<ApiResponse<WeatherResponse>> getWeather(
            @Query("id") int locationId,
            @Query("APPID") String apiKey,
            @Query("units") String metric,
            @Query("cnt") int count
    );


}
