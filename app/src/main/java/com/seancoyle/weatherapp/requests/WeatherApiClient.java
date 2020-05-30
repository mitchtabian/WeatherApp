package com.seancoyle.weatherapp.requests;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.seancoyle.weatherapp.AppExecutors;
import com.seancoyle.weatherapp.models.Weather;

import java.io.IOException;

import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

import static com.seancoyle.weatherapp.util.Constants.API_KEY;
import static com.seancoyle.weatherapp.util.Constants.BELFAST_ID;
import static com.seancoyle.weatherapp.util.Constants.NETWORK_TIMEOUT;

public class WeatherApiClient {

    /**
     * Tag for identifying the log
     */
    private static final String TAG = "WeatherApiClient";

    /**
     * Instantiate an instance of the WeatherApiClient
     */
    private static WeatherApiClient instance;


    private MutableLiveData<List<Weather>> mWeather;

    private RetrieveWeatherRunnable mRetrieveWeatherRunnable;

    /**
     * Use a Singleton of the WeatherApiClient
     *
     * @return
     */
    public static WeatherApiClient getInstance() {
        if (instance == null) {
            instance = new WeatherApiClient();
        }
        return instance;
    }


    private WeatherApiClient() {
        mWeather = new MutableLiveData<>();

    }

    /**
     * Method to return live weather data.
     *
     * @return
     */
    public LiveData<List<Weather>> getWeather() {
        return mWeather;
    }


    public void searchWeatherAPI(int locationCode, String apiKey) {

        if (mRetrieveWeatherRunnable != null) {
            mRetrieveWeatherRunnable = null;
        }
        mRetrieveWeatherRunnable = new RetrieveWeatherRunnable(locationCode, apiKey);

        final Future handler = AppExecutors.getInstance().netWorkIO().submit(mRetrieveWeatherRunnable);

        // Thread which will stop the network request after a specified amount of time.
        AppExecutors.getInstance().netWorkIO().schedule(new Runnable() {
            @Override
            public void run() {

                // let the user know its timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);
    }

    private class RetrieveWeatherRunnable implements Runnable {

        private int locationCode;
        private String apiKey;
        boolean cancelRequest;

        private RetrieveWeatherRunnable(int locationCode, String apiKey) {
            this.locationCode = locationCode;
            this.apiKey = apiKey;
            cancelRequest = false;
        }

        @Override
        public void run() {

            // Executes the network request on a background thread
            try {
                Response<Weather> response = getWeather(locationCode, apiKey).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {


                    //  Weather weather = response.body().getWeather();
                    Log.d(TAG, "onResponse: " + response.body().toString());


                } else {

                    // In case of error, post null.
                    String error = response.errorBody().string();
                    Log.e(TAG, "onResponse: " + error);
                    mWeather.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mWeather.postValue(null);
            }

        }

        /**
         * Method which will make the request to the API
         *
         * @param locationCode
         * @param apiKey
         * @return
         */
        private Call<Weather> getWeather(int locationCode, String apiKey) {
            return ServiceGenerator.getWeatherApi().getWeather(
                    locationCode,
                    apiKey);
        }

        /**
         * Method which sets the cancel request boolean to true
         */
        private void CancelRequest() {
            Log.d(TAG, "cancelRequest: Cancelling the request");
            cancelRequest = true;
        }
    }
}
