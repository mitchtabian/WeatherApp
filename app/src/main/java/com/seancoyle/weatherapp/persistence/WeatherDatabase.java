package com.seancoyle.weatherapp.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.seancoyle.weatherapp.models.WeatherResponse;


public abstract class WeatherDatabase  {

    /*
    public static final String DATABASE_NAME = "weather_db";

    private static WeatherDatabase instance;

    public static WeatherDatabase getInstance(final Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    WeatherDatabase.class,
                    DATABASE_NAME
            ).build();
        }
        return instance;
    }*/
}
