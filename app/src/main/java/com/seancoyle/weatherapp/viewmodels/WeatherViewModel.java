package com.seancoyle.weatherapp.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.seancoyle.weatherapp.models.WeatherResponse;
import com.seancoyle.weatherapp.repositories.WeatherRepository;
import com.seancoyle.weatherapp.responses.ApiResponse;
import com.seancoyle.weatherapp.util.Resource;


public class WeatherViewModel extends AndroidViewModel {

    private String TAG ="AppDebug";

    /**
     * Instantiate the weather repository.
     */
    private WeatherRepository mWeatherRepository;

    private final MediatorLiveData<Resource<WeatherResponse>> _data = new MediatorLiveData<>();

    public MutableLiveData<Resource<WeatherResponse>> data() {
        return _data;
    }

    public WeatherViewModel(Application application) {
        super(application);
        mWeatherRepository = WeatherRepository.getInstance(application);
    }

    /**
     * Constructor containing the weather repository instance.
     */

    public void getWeather(int locationCode, String apiKey, String metric, int count) {

        final LiveData<ApiResponse<WeatherResponse>> source = mWeatherRepository.getWeather(locationCode, apiKey, metric,count);
        Log.d(TAG, "getWeather: called.");
        _data.addSource(source, new Observer<ApiResponse<WeatherResponse>>() {
            @Override
            public void onChanged(ApiResponse<WeatherResponse> response) {
                Log.d(TAG, "onChanged: called.");
                _data.removeSource(source);
                if(response instanceof ApiResponse.ApiSuccessResponse) {
                    Log.d(TAG, "onChanged: ApiSuccessResponse.");
                    WeatherResponse wr = (WeatherResponse) ((ApiResponse.ApiSuccessResponse) response).getBody() ;
                    _data.setValue(Resource.success(wr));
                }
                else if(response instanceof ApiResponse.ApiEmptyResponse){
                    Log.d(TAG, "onChanged: ApiEmptyResponse");
                    _data.setValue(Resource.error("error", (WeatherResponse)null));
                }
                else if(response instanceof ApiResponse.ApiErrorResponse){
                    Log.d(TAG, "onChanged: ApiErrorResponse.");
                    _data.setValue(Resource.error("error", (WeatherResponse)null));
                }
            }
        });
    }


}
