package com.seancoyle.weatherapp.models;

import java.util.List;

public class AllWeather {

    private List<WeatherResponse> getResults;

    public AllWeather() {
    }

    public AllWeather(List<WeatherResponse> getResults) {
        this.getResults = getResults;
    }

    public List<WeatherResponse> getGetResults() {
        return getResults;
    }

    public void setGetResults(List<WeatherResponse> getResults) {
        this.getResults = getResults;
    }
}
