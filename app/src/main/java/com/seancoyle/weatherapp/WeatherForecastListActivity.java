package com.seancoyle.weatherapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.seancoyle.weatherapp.adapters.EmptyAdapter;
import com.seancoyle.weatherapp.adapters.WeatherRecyclerAdapter;
import com.seancoyle.weatherapp.models.Weather;
import com.seancoyle.weatherapp.models.WeatherResponse;
import com.seancoyle.weatherapp.models.WeatherList;
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
import static com.seancoyle.weatherapp.util.Constants.COUNT;
import static com.seancoyle.weatherapp.util.Constants.METRIC;

public class WeatherForecastListActivity extends BaseActivity implements WeatherRecyclerAdapter.RecyclerOnClickListener {


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
    private List<Weather> baseWeatherList;
    private WeatherResponse mWeatherResponse2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout_weather);

        initialiseVariables();

        subscribeObservers();

        forecastRequestParameters();


    }

    /**
     * Method used to initialise declared variables.
     */
    private void initialiseVariables() {

        // Instantiate the weather View Model.
        mWeatherListViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        // Instantiate the Recyclerview.
        mRecyclerView = findViewById(R.id.weatherRecyclerView);

        // Assign an empty adapter to the RecyclerView to prevent Nulls if error loading data.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        EmptyAdapter emptyAdapter = new EmptyAdapter();
        mRecyclerView.setAdapter(emptyAdapter);

    }

    /**
     * Method which assigns the retrofit response from the API to the RecyclerView.
     *
     * @param
     * @param
     */
    private void setAdapterWithResults(List<WeatherList> mWeatherList, WeatherResponse mWeatherResponseList, List<Weather> mWeather) {

        mWeatherRecyclerAdapter = new WeatherRecyclerAdapter(this, mWeatherList, mWeatherResponseList, mWeather);
        mRecyclerView.setAdapter(mWeatherRecyclerAdapter);
        mWeatherRecyclerAdapter.setOnLogListener(this);
        mWeatherRecyclerAdapter.notifyDataSetChanged();

    }

    /**
     * Method used to observe live data- when the weather data updates the observer will refresh.
     */
    private void subscribeObservers() {
        mWeatherListViewModel.getWeather().observe(this, new Observer<WeatherResponse>() {
            @Override
            public void onChanged(WeatherResponse mWeathersResponse) {

                if (mWeathersResponse != null) {

                    for (WeatherList loop: mWeathersResponse.getResults()){
                        baseWeatherList = loop.getResults();
                    }

                    mWeatherResponse2 = mWeathersResponse;
                    mWeatherList = mWeathersResponse.getResults();
                    setAdapterWithResults(mWeatherList, mWeatherResponse2, baseWeatherList);

                }
            }
        });
    }


    /**
     * Method used to obtain the parameters used to query the API
     */
    private void forecastRequestParameters() {

         searchWeatherApi(BELFAST_ID, API_KEY, METRIC, COUNT);

    }

    /**
     * Method used to send the request to the View Model.
     * @param locationCode
     * @param apiKey
     * @param metric
     * @param count
     */
    private void searchWeatherApi(int locationCode, String apiKey, String metric, int count) {
        mWeatherListViewModel.searchWeatherApi(locationCode, apiKey, metric, count);
    }

    /**
     * On Click listener for the recyclerView
     * @param position
     */
    @Override
    public void onLogClick(int position) {

        WeatherList currentWeather = mWeatherList.get(position);

        // Get the temperature as a double and remove the decimal places
        double tempDouble = currentWeather.getMain().getTempMax();
        int tempInt = (int) Math.round(tempDouble);
        String tempString = String.valueOf(tempInt);

        // Displays a toast message when clicked with the maximum temperature
        Toast.makeText(this, ""+tempString+"°C", Toast.LENGTH_SHORT).show();
    }


    private void TestRetroFit(){

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

                  //  mWeatherResponseList = response.body().getResults().;
                   // mWeatherResponse = response.body();
                    mWeatherList = response.body().getResults();
                   // mWeather = response.body().getResults().

                    Toast.makeText(WeatherForecastListActivity.this, "", Toast.LENGTH_SHORT).show();

                  // list = new ArrayList<WeatherResponse>(Collections.singleton(((response.body()))));

                    Log.d(TAG, "onResponse: " + response.body().toString());
                  //  Log.d(TAG, "onResponse: " + mWeatherResponse.toString());

                  // setAdapterWithResults(mWeatherList, mWeatherResponse);

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

}
