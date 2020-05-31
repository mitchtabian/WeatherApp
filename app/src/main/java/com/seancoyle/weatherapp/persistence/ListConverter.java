package com.seancoyle.weatherapp.persistence;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.seancoyle.weatherapp.models.WeatherList;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

public class ListConverter {

    @TypeConverter
    public static List<WeatherList> stringToSomeObjectList(String data) {
        if (data == null) {
            return Collections.emptyList();
        }

        Type listType = new TypeToken<List<WeatherList>>() {}.getType();
        return new Gson().fromJson(data, listType);
    }

    @TypeConverter
    public static String someObjectListToString(List<WeatherList> someObjects) {
        return new Gson().toJson(someObjects);
    }
}
