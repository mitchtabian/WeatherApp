package com.seancoyle.weatherapp.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.seancoyle.weatherapp.models.AllWeather;
import com.seancoyle.weatherapp.models.WeatherResponse;
import com.seancoyle.weatherapp.repositories.WeatherRepository;

public class WeatherViewModel extends ViewModel {

    /**
     * Instantiate the weather repository.
     */
    private WeatherRepository mWeatherRepository;

    /**
     * Constructor containing the weather repository instance.
     */
    public WeatherViewModel() {
        mWeatherRepository = WeatherRepository.getInstance();
    }

    /**
     * Method to return live weather data from the repository
     * @return
     */
    public LiveData<AllWeather> getWeather() {
        return mWeatherRepository.getWeather();
    }




    public void searchWeatherApi(int locationCode, String apiKey, String metric, int count){
        mWeatherRepository.searchWeatherApi(locationCode, apiKey, metric, count);
    }

}
