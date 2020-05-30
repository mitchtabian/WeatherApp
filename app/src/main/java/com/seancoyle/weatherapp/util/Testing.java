package com.seancoyle.weatherapp.util;

import android.util.Log;

import com.seancoyle.weatherapp.models.Weather;

import java.util.List;

public class Testing {



    public static void printWeather(List<Weather> list, String tag){

        for (Weather weather : list) {
            Log.d(tag, "onChanged: " + weather.toString());
        }
    }
}
