package com.seancoyle.weatherapp.repositories;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.seancoyle.weatherapp.AppExecutors;
import com.seancoyle.weatherapp.models.WeatherResponse;
import com.seancoyle.weatherapp.persistence.WeatherDao;
import com.seancoyle.weatherapp.persistence.WeatherDatabase;
import com.seancoyle.weatherapp.requests.ServiceGenerator;
import com.seancoyle.weatherapp.requests.WeatherApiClient;
import com.seancoyle.weatherapp.responses.ApiResponse;


public class WeatherRepository {


    private static final String TAG ="SaveCallResult" ;
    /**
     * Instantiate an instance of the repository
     */
    private static WeatherRepository instance;

    /**
     * Instantiate an instance of the WeatherApiClient
     */
    private WeatherApiClient mWeatherApiClient;

    /**
     * Instantiate an instance of the WeatherDao
     */
    private WeatherDao weatherDao;

    private AppExecutors appExecutors = AppExecutors.getInstance();

    /**
     * Use a Singleton of the weather repository
     *
     * @return
     */
    public static WeatherRepository getInstance(Context context) {
        if (instance == null) {
            instance = new WeatherRepository(context);
        }
        return instance;
    }

    /**
     * Constructor used to get instances of the DAO and API client
     *
     * @param
     */
    private WeatherRepository(Context context) {
        weatherDao = WeatherDatabase.getInstance(context).getWeatherDao();
    }


    public LiveData<ApiResponse<WeatherResponse>> getWeather(int locationCode, String apiKey, String metric, int count){
      return ServiceGenerator.getWeatherApi().getWeather(
              locationCode,
              apiKey,
              metric,
              count
      );
  }



}
