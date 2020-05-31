package com.seancoyle.weatherapp.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.seancoyle.weatherapp.models.WeatherResponse;
import com.seancoyle.weatherapp.repositories.WeatherRepository;
import com.seancoyle.weatherapp.util.Resource;

import java.util.List;

public class WeatherViewModel extends AndroidViewModel {


    /**
     * Instantiate the weather repository.
     */
    private WeatherRepository mWeatherRepository;


    public WeatherViewModel(Application application) {
        super(application);
        mWeatherRepository = WeatherRepository.getInstance(application);
    }

    /**
     * Constructor containing the weather repository instance.
     */


    /**
     * Method to return live weather data from the repository
     * @return
     */
    public LiveData<Resource<WeatherResponse>> getWeather(int locationCode, String apiKey, String metric, int count) {
        return mWeatherRepository.searchWeatherApi(locationCode, apiKey, metric, count);
    }



    public void searchWeatherApi(int locationCode, String apiKey, String metric, int count){
        mWeatherRepository.searchWeatherApi(locationCode, apiKey, metric, count);
    }

}
