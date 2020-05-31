package com.seancoyle.weatherapp.repositories;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.seancoyle.weatherapp.AppExecutors;
import com.seancoyle.weatherapp.models.Weather;
import com.seancoyle.weatherapp.models.WeatherResponse;
import com.seancoyle.weatherapp.persistence.WeatherDao;
import com.seancoyle.weatherapp.persistence.WeatherDatabase;
import com.seancoyle.weatherapp.requests.ServiceGenerator;
import com.seancoyle.weatherapp.requests.WeatherApiClient;
import com.seancoyle.weatherapp.responses.ApiResponse;
import com.seancoyle.weatherapp.util.Constants;
import com.seancoyle.weatherapp.util.NetworkBoundResource;
import com.seancoyle.weatherapp.util.Resource;

import java.util.List;

import static com.seancoyle.weatherapp.util.Constants.API_KEY;
import static com.seancoyle.weatherapp.util.Constants.BELFAST_ID;
import static com.seancoyle.weatherapp.util.Constants.COUNT;
import static com.seancoyle.weatherapp.util.Constants.METRIC;


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
       // mWeatherApiClient = WeatherApiClient.getInstance();
    }


    /**
     * Method used to pass the parameters to make a network request to the API
     *
     * @param locationCode
     * @param apiKey
     * @param metric
     * @param count
     */
    public void searchWeatherApi2(int locationCode, String apiKey, String metric, int count) {
      //  mWeatherApiClient.searchWeatherAPI(locationCode, apiKey, metric, count);
    }

    /**
     * Method to return live weather data from the API.
     *
     * @return
     */
  /*  public LiveData<WeatherResponse> getWeather() {
        return mWeatherApiClient.getWeather();
    }*/



    public LiveData<Resource<WeatherResponse>> searchRecipesApi(){
        return new NetworkBoundResource<WeatherResponse, WeatherResponse>(AppExecutors.getInstance()){
            @Override
            protected void saveCallResult(@NonNull WeatherResponse item) {

               /* // will be null if API key is expired
                if(item.getRecipe() != null){
                    item.getRecipe().setTimestamp((int)(System.currentTimeMillis() / 1000));
                    recipeDao.insertRecipe(item.getRecipe());
                }*/
            }

            @Override
            protected boolean shouldFetch(@Nullable WeatherResponse data) {
                Log.d(TAG, "shouldFetch: recipe: " + data.toString());
                int currentTime = (int)(System.currentTimeMillis() / 1000);
                Log.d(TAG, "shouldFetch: current time: " + currentTime);
                int lastRefresh = data.getTimestamp();
                Log.d(TAG, "shouldFetch: last refresh: " + lastRefresh);
                Log.d(TAG, "shouldFetch: it's been " + ((currentTime - lastRefresh) / 60 / 60 / 24) +
                        " days since this recipe was refreshed. 30 days must elapse before refreshing. ");
                if((currentTime - data.getTimestamp()) >= Constants.RECIPE_REFRESH_TIME){
                    Log.d(TAG, "shouldFetch: SHOULD REFRESH RECIPE?! " + true);
                    return true;
                }
                Log.d(TAG, "shouldFetch: SHOULD REFRESH RECIPE?! " + false);
                return false;
            }

            @NonNull
            @Override
            protected LiveData<WeatherResponse> loadFromDb() {
                return weatherDao.();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<WeatherResponse>> createCall() {
                return ServiceGenerator.getWeatherApi().getWeather(
                        BELFAST_ID,
                        API_KEY,
                        METRIC,
                        COUNT
                );
            }
        }.getAsLiveData();
    }




}
