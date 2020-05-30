package com.seancoyle.weatherapp.util;

import android.util.Log;

import com.seancoyle.weatherapp.models.WeatherList;

import java.util.List;

public class Testing {



    public static void printWeather(List<WeatherList> list, String tag){

        for (WeatherList weather : list) {
            Log.d(tag, "onChanged: " + weather.toString());
        }
    }
}
