package com.seancoyle.weatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Weather implements Parcelable {


    @SerializedName("coord")
    @Expose
    private CoOrdinateModel coOrdinateModel;

    @SerializedName("weather")
    @Expose
    private List<WeatherModel> weatherModel;

    @SerializedName("main")
    @Expose
    private MainModel main;

    @SerializedName("visibility")
    @Expose
    private String visibility;

    @SerializedName("wind")
    @Expose
    private WindModel wind;

    @SerializedName("dt")
    @Expose
    private Date dt;

    @SerializedName("sys")
    @Expose
    private SysModel sys;

    @SerializedName("name")
    @Expose
    private String name;


    public Weather() {
    }

    public Weather(CoOrdinateModel coOrdinateModel, List<WeatherModel> weatherModel, MainModel main, String visibility, WindModel wind, Date dt, SysModel sys, String name) {
        this.coOrdinateModel = coOrdinateModel;
        this.weatherModel = weatherModel;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.dt = dt;
        this.sys = sys;
        this.name = name;
    }

    protected Weather(Parcel in) {
        visibility = in.readString();
        name = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(visibility);
        dest.writeString(name);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel in) {
            return new Weather(in);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    public CoOrdinateModel getCoOrdinateModel() {
        return coOrdinateModel;
    }

    public void setCoOrdinateModel(CoOrdinateModel coOrdinateModel) {
        this.coOrdinateModel = coOrdinateModel;
    }

    public List<WeatherModel> getWeatherModel() {
        return weatherModel;
    }

    public void setWeatherModel(List<WeatherModel> weatherModel) {
        this.weatherModel = weatherModel;
    }

    public static Creator<Weather> getCREATOR() {
        return CREATOR;
    }

    public MainModel getMain() {
        return main;
    }

    public void setMain(MainModel main) {
        this.main = main;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public WindModel getWind() {
        return wind;
    }

    public void setWind(WindModel wind) {
        this.wind = wind;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public SysModel getSys() {
        return sys;
    }

    public void setSys(SysModel sys) {
        this.sys = sys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

