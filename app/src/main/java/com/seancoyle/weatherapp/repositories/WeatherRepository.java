package com.seancoyle.weatherapp.repositories;

import androidx.lifecycle.LiveData;
import com.seancoyle.weatherapp.models.Weather;
import com.seancoyle.weatherapp.requests.WeatherApiClient;

import java.util.List;

public class WeatherRepository {

    /**
     * Instantiate an instance of the repository
     */
    private static WeatherRepository instance;

    /**
     * Instantiate an instance of the WeatherApiClient
     */
    private WeatherApiClient mWeatherApiClient;


    /**
     * Use a Singleton of the weather repository
     *
     * @return
     */
    public static WeatherRepository getInstance() {
        if (instance == null) {
            instance = new WeatherRepository();
        }
        return instance;

    }

    private WeatherRepository() {
        mWeatherApiClient = WeatherApiClient.getInstance();
    }

    /**
     * Method to return live weather data.
     *
     * @return
     */
    public LiveData<List<Weather>> getWeather() {
        return mWeatherApiClient.getWeather();
    }

    public void searchWeatherApi(int locationCode, String apiKey){

        mWeatherApiClient.searchWeatherAPI(locationCode, apiKey);
    }

}
