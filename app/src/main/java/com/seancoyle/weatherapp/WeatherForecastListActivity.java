package com.seancoyle.weatherapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seancoyle.weatherapp.adapters.EmptyAdapter;
import com.seancoyle.weatherapp.adapters.WeatherRecyclerAdapter;
import com.seancoyle.weatherapp.models.AllWeather;
import com.seancoyle.weatherapp.models.Weather;
import com.seancoyle.weatherapp.models.WeatherResponse;
import com.seancoyle.weatherapp.models.WeatherList;
import com.seancoyle.weatherapp.requests.ServiceGenerator;
import com.seancoyle.weatherapp.requests.WeatherApi;
import com.seancoyle.weatherapp.util.Constants;
import com.seancoyle.weatherapp.viewmodels.WeatherViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherForecastListActivity extends BaseActivity implements WeatherRecyclerAdapter.OnLogListener {

    private Button button;
    private static final String TAG = "WeatherForecast";


    /**
     * Variable which contains the weather View Model.
     */
    private WeatherViewModel mWeatherListViewModel;


    /**
     * Variable which contains the Recycler-View.
     */
    private RecyclerView mRecyclerView;

    /**
     * Variable which contains the Weather Adapter
     */
    private WeatherRecyclerAdapter mWeatherRecyclerAdapter;

    /**
     * List of type weather which contains the models used to parse the json.
     */
    private List<WeatherList> mWeatherList;
    private WeatherResponse mWeatherResponse;
    private Weather mWeather;
   // private List<WeatherResponse> mWeatherResponseList;
    private List<AllWeather> mAllWeather;

    private List<WeatherResponse> mWeatherResponseList=new ArrayList();

    List<WeatherResponse> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout_weather);

        initialiseVariables();

        //subscribeObservers();

        testRetrofitRequest();


    }

    /**
     * Method used to initialise declared variables.
     */
    private void initialiseVariables() {

        // Instantiate the weather View Model.
        mWeatherListViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        mRecyclerView = findViewById(R.id.weatherRecyclerView);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EmptyAdapter emptyAdapter = new EmptyAdapter();
        mRecyclerView.setAdapter(emptyAdapter);


    }

    /**
     * Method which contains the retrofit response from the API.
     *
     * @param
     * @param
     */
    private void setAdapterWithResults(List<WeatherList> mWeatherList, WeatherResponse mWeatherResponseList) {

        mWeatherRecyclerAdapter = new WeatherRecyclerAdapter(this, mWeatherList, mWeatherResponseList);
        mRecyclerView.setAdapter(mWeatherRecyclerAdapter);
        mWeatherRecyclerAdapter.setOnLogListener(this);
        mWeatherRecyclerAdapter.notifyDataSetChanged();

    }

    /**
     * Method used to observe live data- when the weather data updates the observer will refresh.
     */
   /* private void subscribeObservers() {
        mWeatherListViewModel.getWeather().observe(this, new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse weathers) {

                if (weathers != null) {
               /*     Testing.printWeather(weathers, "weather test");
                    mWeatherList = weathers;
                   setAdapterWithResults(mWeatherList);*/
    //   }
    //   }
    // });
    // }
    private void searchWeatherApi(int locationCode, String apiKey, String metric, int count) {
        mWeatherListViewModel.searchWeatherApi(locationCode, apiKey, metric, count);
    }

    private void testRetrofitRequest() {

        // searchWeatherApi(BELFAST_ID, API_KEY, METRIC, COUNT);


        WeatherApi weatherApi = ServiceGenerator.getWeatherApi();

        Call<WeatherResponse> responseCall = weatherApi.getWeather(
                Constants.BELFAST_ID,
                Constants.API_KEY,
                Constants.METRIC,
                Constants.COUNT
        );
        responseCall.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

                Log.d(TAG, "onResponse: server response " + response.toString());

                // response code 200 means a successful request
                // if successful store the response body in the lod
                if (response.code() == 200) {
                   // mWeatherResponseList = response.body();

                    //mWeatherResponseList = response.body();
                    mWeatherResponse = response.body();
                    mWeatherList = response.body().getResults();
                   // mWeather = response.body().getResults().

                  // list = new ArrayList<WeatherResponse>(Collections.singleton(((response.body()))));

                    Log.d(TAG, "onResponse: " + response.body().toString());
                  //  Log.d(TAG, "onResponse: " + mWeatherResponse.toString());

                   setAdapterWithResults(mWeatherList, mWeatherResponse);

               //     Toast.makeText(WeatherForecastListActivity.this, "Test" + response.body(), Toast.LENGTH_SHORT).show();

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


    @Override
    public void onLogClick(int position) {

    }
}
