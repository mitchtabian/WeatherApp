package com.seancoyle.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.seancoyle.weatherapp.models.Weather;
import com.seancoyle.weatherapp.requests.ServiceGenerator;
import com.seancoyle.weatherapp.requests.WeatherApi;
import com.seancoyle.weatherapp.requests.responses.WeatherResponse;
import com.seancoyle.weatherapp.util.Constants;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherForecastListActivity extends BaseActivity {

    private Button button;
    private static final String TAG = "WeatherForecast";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast_list);


        button = findViewById(R.id.test);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               testRetrofitRequest();
            }
        });

    }

    private void testRetrofitRequest(){

        WeatherApi weatherApi = ServiceGenerator.getWeatherApi();

        Call<WeatherResponse> responseCall = weatherApi.getWeather(
                Constants.BELFAST_ID,
                Constants.API_KEY
        );
        responseCall.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                Log.d(TAG, "onResponse: server response " + response.toString());

                // response code 200 means a successful request
                // if successful store the response body in the lod
                if(response.code() == 200){
                    Weather weather = response.body().getWeather();
                    Log.d(TAG, "onResponse: " + response.body().toString());

                } else {
                    Log.d(TAG, "onResponse: " + response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                Log.d(TAG, "onResponse: ERROR: " + t.getMessage());
            }
        });
    }
}
