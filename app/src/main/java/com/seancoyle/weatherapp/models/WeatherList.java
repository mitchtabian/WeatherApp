package com.seancoyle.weatherapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.seancoyle.weatherapp.persistence.ListConverter;

import java.util.List;

@Entity(tableName = "weatherList")
public class WeatherList {

    @PrimaryKey(autoGenerate = true)
    private long weatherListId;

    @SerializedName("dt")
    @Expose
    @ColumnInfo(name ="date")
    private Long dt;

    @SerializedName("main")
    @Expose
    @ColumnInfo(name ="main")
    @Embedded
    private Main main;

    @SerializedName("weather")
    @Expose
    @ColumnInfo(name ="weather")
    @TypeConverters(ListConverter.class)
    private List<Weather> results;

    @SerializedName("clouds")
    @Expose
    @ColumnInfo(name ="clouds")
    @Embedded
    private Clouds clouds;

    @SerializedName("wind")
    @Expose
    @ColumnInfo(name ="wind")
    @Embedded
    private Wind wind;

    @SerializedName("sys")
    @Expose
    @ColumnInfo(name ="sys")
    @Embedded
    private Sys sys;

    @SerializedName("dt_txt")
    @Expose
    @ColumnInfo(name ="dtTxt")
    private String dtTxt;

    /**
     * No args constructor for use in serialization
     *
     */
    @Ignore
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

    public long getWeatherListId() {
        return weatherListId;
    }

    public void setWeatherListId(long weatherListId) {
        this.weatherListId = weatherListId;
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
