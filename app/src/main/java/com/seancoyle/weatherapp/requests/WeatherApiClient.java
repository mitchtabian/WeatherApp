package com.seancoyle.weatherapp.requests;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.seancoyle.weatherapp.models.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherApiClient {
/*
    /**
     * Tag for identifying the log
     */
 /*   private static final String TAG = "WeatherApiClient";

    /**
     * Instantiate an instance of the WeatherApiClient
     */
  /*  private static WeatherApiClient instance;


    private MutableLiveData<WeatherResponse> mWeather;


  /*  private RetrieveWeatherRunnable mRetrieveWeatherRunnable;

    /**
     * Use a Singleton of the WeatherApiClient
     *
     * @return
     */
 /*   public static WeatherApiClient getInstance() {
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
  /*  public LiveData<WeatherResponse> getWeather() {
        return mWeather;
    }


    public void searchWeatherAPI(int locationCode, String apiKey, String metric, int count) {

        WeatherApi weatherApi = ServiceGenerator.getWeatherApi();

        Call<WeatherResponse> responseCall = weatherApi.getWeather(
                locationCode,
                apiKey,
                metric,
                count
        );
        responseCall.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                Log.d(TAG, "onResponse: server response " + response.toString());

                // response code 200 means a successful request
                // if successful store the response body in the lod
                if (response.code() == 200) {
                    // mWeatherResponseList = response.body();

                    mWeather.setValue(response.body());

                    Log.d(TAG, "onResponse: " + response.body().toString());
                    //  Log.d(TAG, "onResponse: " + mWeatherResponse.toString());


                } else {
                    Log.d(TAG, "onResponse: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d(TAG, "onResponse: ERROR: " + t.getMessage());

            }
        });
        /*
        if (mRetrieveWeatherRunnable == null) {
            mRetrieveWeatherRunnable = new RetrieveWeatherRunnable(locationCode, apiKey, metric, count);
        }

        final Future handler = AppExecutors.getInstance().netWorkIO().submit(mRetrieveWeatherRunnable);

        // Thread which will stop the network request after a specified amount of time.
        AppExecutors.getInstance().netWorkIO().schedule(new Runnable() {
            @Override
            public void run() {

                // let the user know its timed out
                handler.cancel(true);
            }
        }, NETWORK_TIMEOUT, TimeUnit.MILLISECONDS);*/
  /*  }

    /*private class RetrieveWeatherRunnable implements Runnable {

        private int locationCode;
        private String apiKey;
        private String metric;
        private int count;
        boolean cancelRequest;

        private RetrieveWeatherRunnable(int locationCode, String apiKey, String metric, int count) {
            this.locationCode = locationCode;
            this.apiKey = apiKey;
            this.metric = metric;
            this.count = count;
            cancelRequest = false;
        }

        @Override
        public void run() {

            // Executes the network request on a background thread
       /*     try {
                Response response = getWeather(locationCode, apiKey, metric, count).execute();
                if (cancelRequest) {
                    return;
                }
                if (response.code() == 200) {

                    //  Weather weather = response.body().getWeather();
                    Log.d(TAG, "onResponse: " + response.body().toString());

                    //List<WeatherList> movies = response.body().;
                   // List<OpenWeatherMap> list = new ArrayList<OpenWeatherMap>((Collection<? extends OpenWeatherMap>) response.body());
                    mWeather.postValue((WeatherResponse) response.body());

                } else {

                    // In case of error, post null.
                    String error = response.errorBody().string();
                    Log.e(TAG, "onResponse: " + error);
                    mWeather.postValue(null);
                }

            } catch (IOException e) {
                e.printStackTrace();
                mWeather.postValue(null);
            }*/

    /*    }

        /**
         * Method which will make the request to the API
         *
         * @param locationCode
         * @param apiKey
         * @return
         */
      /*  private Call<WeatherResponse> getWeather(int locationCode, String apiKey, String metric, int count) {
            return ServiceGenerator.getWeatherApi().getWeather(
                    locationCode,
                    apiKey,
                    metric,
                    count);
        }

        /**
         * Method which sets the cancel request boolean to true
         */
      /*  private void CancelRequest() {
            Log.d(TAG, "cancelRequest: Cancelling the request");
            cancelRequest = true;
        }
    }

    private void testRetrofitRequest() {

    }*/

}