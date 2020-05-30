package com.seancoyle.weatherapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.seancoyle.weatherapp.models.Weather;
import com.seancoyle.weatherapp.requests.ServiceGenerator;
import com.seancoyle.weatherapp.requests.WeatherApi;
import com.seancoyle.weatherapp.util.Constants;
import com.seancoyle.weatherapp.viewmodels.WeatherViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.seancoyle.weatherapp.util.Constants.API_KEY;
import static com.seancoyle.weatherapp.util.Constants.BELFAST_ID;

public class WeatherForecastListActivity extends BaseActivity {

    private Button button;
    private static final String TAG = "WeatherForecast";


    private WeatherViewModel mWeatherListViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast_list);

        // Instantiate the weather View Model.
        mWeatherListViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        subscribeObservers();
        button = findViewById(R.id.test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testRetrofitRequest();
            }
        });

    }

    /**
     * Method used to observe live data- when the weather data updates the observer will refresh.
     */
    private void subscribeObservers() {
        mWeatherListViewModel.getWeather().observe(this, new Observer<List<Weather>>() {
            @Override
            public void onChanged(List<Weather> weathers) {

                if (weathers != null) {
                    for (Weather weather : weathers) {
                        Log.d(TAG, "onChanged: " + weather.toString());
                    }
                }
            }
        });
    }

    private void testRetrofitRequest() {

        searchWeatherApi(BELFAST_ID, API_KEY);

        /*
        WeatherApi weatherApi = ServiceGenerator.getWeatherApi();

        Call<Weather> responseCall = weatherApi.getWeather(
                Constants.BELFAST_ID,
                Constants.API_KEY
        );
        responseCall.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                Log.d(TAG, "onResponse: server response " + response.toString());

                // response code 200 means a successful request
                // if successful store the response body in the lod
                if (response.code() == 200) {
                    //  Weather weather = response.body().getWeather();
                    Log.d(TAG, "onResponse: " + response.body().toString());

                    Toast.makeText(WeatherForecastListActivity.this, "Test" + response.body().getMain().toString(), Toast.LENGTH_SHORT).show();

                } else {
                    Log.d(TAG, "onResponse: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.d(TAG, "onResponse: ERROR: " + t.getMessage());

            }
        });*/
    }


    private void searchWeatherApi(int locationCode, String apiKey) {
        mWeatherListViewModel.searchWeatherApi(locationCode, apiKey);
    }

}
