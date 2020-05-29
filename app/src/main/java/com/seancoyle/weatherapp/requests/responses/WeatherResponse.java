package com.seancoyle.weatherapp.requests.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.seancoyle.weatherapp.models.Weather;

public class WeatherResponse {

    @SerializedName("coord")
    @Expose()
    private Weather weather;

    public Weather getWeather() {
        return weather;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "weather=" + weather +
                '}';
    }
}
