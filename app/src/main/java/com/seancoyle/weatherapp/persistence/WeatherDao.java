package com.seancoyle.weatherapp.persistence;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.seancoyle.weatherapp.models.WeatherResponse;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface WeatherDao {

    @Insert(onConflict = REPLACE)
    long[] insertWeather(WeatherResponse... weather);

    @Query("UPDATE weatherResponse SET cod = :cod, message = :message, cnt = :cnt, timestamp = :timestamp")
    void updateWeather (String cod, Long message, Long cnt, int timestamp);

    @Query("SELECT * FROM weatherResponse")
    LiveData<List<WeatherResponse>> searchWeather ();

}
