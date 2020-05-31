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


    public LiveData<Resource<List<WeatherResponse>>> searchWeatherApi(final int locationCode, final String apiKey, final String metric, final int count) {
        return new NetworkBoundResource<List<WeatherResponse>, WeatherResponse>(AppExecutors.getInstance()) {


            @Override
            protected void saveCallResult(@NonNull WeatherResponse item) {
                if(item.getResults() != null){ // recipe list will be null if api key is expired
                    WeatherResponse[] recipes = new WeatherResponse[item.getResults().size()];

                    int index = 0;
                    for(long rowId: weatherDao.insertWeather((WeatherResponse[])(item.getResults().toArray(recipes)))){
                        if(rowId == -1){ // conflict detected
                            Log.d(TAG, "saveCallResult: CONFLICT... This weather is already in cache.");
                            // if already exists, I don't want to set the ingredients or timestamp b/c they will be erased
                            weatherDao.updateWeather(
                                    recipes[index].getCod(),
                                    recipes[index].getMessage(),
                                    recipes[index].getCnt(),
                                    recipes[index].getTimestamp()

                            );
                        }
                        index++;
                    }
                }

            }

            @Override
            public boolean shouldFetch(@Nullable List<WeatherResponse> data) {
                return true; // always query the network since the queries can be anything
            }

            @NonNull
            @Override
            public LiveData<List<WeatherResponse>> loadFromDb() {
                return weatherDao.searchWeather();
            }

            /**
             * Creates a LiveData retrofit call object
             */
            @NonNull
            @Override
            public LiveData<ApiResponse<WeatherResponse>> createCall() {
                return ServiceGenerator.getWeatherApi()
                        .getWeather(
                                BELFAST_ID,
                                API_KEY,
                                METRIC,
                                COUNT
                        );
            }

        }.getAsLiveData();
    }


}
