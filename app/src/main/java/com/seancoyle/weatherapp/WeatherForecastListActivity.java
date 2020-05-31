package com.seancoyle.weatherapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;
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
import com.seancoyle.weatherapp.util.Resource;
import com.seancoyle.weatherapp.viewmodels.WeatherViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.seancoyle.weatherapp.util.Constants.API_KEY;
import static com.seancoyle.weatherapp.util.Constants.BELFAST_ID;
import static com.seancoyle.weatherapp.util.Constants.COUNT;
import static com.seancoyle.weatherapp.util.Constants.METRIC;
import static com.seancoyle.weatherapp.util.Resource.Status.ERROR;
import static com.seancoyle.weatherapp.util.Resource.Status.LOADING;
import static com.seancoyle.weatherapp.util.Resource.Status.SUCCESS;

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
    private ArrayList mWeatherList = new ArrayList();
    private List<Weather> baseWeatherList;
    private WeatherResponse mWeatherResponse2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout_weather);

        initialiseVariables();

        subscribeObservers();

//        forecastRequestParameters();

        getData();

    }

    private void getData(){
        mWeatherListViewModel.getWeather(
                BELFAST_ID,
                API_KEY,
                METRIC,
                COUNT);
    }

    /**
     * Method used to initialise declared variables.
     */
    private void initialiseVariables() {

        // Instantiate the weather View Model.
        mWeatherListViewModel = ViewModelProviders.of(this).get(WeatherViewModel.class);

        // Instantiate the Recyclerview.
        mRecyclerView = findViewById(R.id.weatherRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mWeatherRecyclerAdapter = new WeatherRecyclerAdapter(this);
    }

    private void subscribeObservers(){
        Log.d(TAG, "subscribeObservers: " + mWeatherListViewModel);

        mWeatherListViewModel.data().observe(this, new Observer<Resource<WeatherResponse>>() {
            @Override
            public void onChanged(@Nullable Resource<WeatherResponse> weatherResource) {
                if(weatherResource != null){
                    if(weatherResource.data != null){
                        switch (weatherResource.status){

                            case LOADING:{
                                showProgressBar(true);
                                break;
                            }

                            case ERROR:{
                                Log.e(TAG, "onChanged: status: ERROR, Recipe: " + weatherResource.data.getResults() );
                                Log.e(TAG, "onChanged: ERROR message: " + weatherResource.message );

                                break;
                            }

                            case SUCCESS:{
                                Log.d(TAG, "onChanged: cache has been refreshed.");
                                Log.d(TAG, "onChanged: status: SUCCESS, Weather: " + weatherResource.data.getResults());
                                showProgressBar(false);
                                List<WeatherList> data = weatherResource.data.getResults();
                                // do something with the data
                                break;
                            }
                        }
                    }
                }
            }
        });
    }


    /**
     * On Click listener for the recyclerView
     * @param position
     */
    @Override
    public void onLogClick(int position) {

    }




}
