package com.seancoyle.weatherapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherList {

    @SerializedName("dt")
    @Expose
    private Long dt;
    @SerializedName("main")
    @Expose
    private Main main;
    @SerializedName("weather")
    @Expose
    private List<Weather> results;
    @SerializedName("clouds")
    @Expose
    private Clouds clouds;
    @SerializedName("wind")
    @Expose
    private Wind wind;
    @SerializedName("sys")
    @Expose
    private Sys sys;
    @SerializedName("dt_txt")
    @Expose
    private String dtTxt;

    /**
     * No args constructor for use in serialization
     *
     */
    public WeatherList() {
    }

    public WeatherList(Long dt, Main main, List<Weather> results, Clouds clouds, Wind wind, Sys sys, String dtTxt) {
        this.dt = dt;
        this.main = main;
        this.results = results;
        this.clouds = clouds;
        this.wind = wind;
        this.sys = sys;
        this.dtTxt = dtTxt;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getResults() {
        return results;
    }

    public void setResults(List<Weather> results) {
        this.results = results;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

    public Sys getSys() {
        return sys;
    }

    public void setSys(Sys sys) {
        this.sys = sys;
    }

    public String getDtTxt() {
        return dtTxt;
    }

    public void setDtTxt(String dtTxt) {
        this.dtTxt = dtTxt;
    }

    @Override
    public String toString() {
        return "WeatherList{" +
                "dt=" + dt +
                ", main=" + main +
                ", results=" + results +
                ", clouds=" + clouds +
                ", wind=" + wind +
                ", sys=" + sys +
                ", dtTxt='" + dtTxt + '\'' +
                '}';
    }
}
