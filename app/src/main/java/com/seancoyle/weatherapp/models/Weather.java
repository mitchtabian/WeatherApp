package com.seancoyle.weatherapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Arrays;
import java.util.Date;

public class Weather implements Parcelable {


    @SerializedName("coord")
    private float coOrd[];

    @SerializedName("weather")
    private String weather[];

    @SerializedName("main")
    private float main[];

    @SerializedName("visibility")
    private String visibility;

    @SerializedName("wind")
    private String wind[];

    @SerializedName("clouds")
    private String clouds[];

    @SerializedName("dt")
    private Date dt;

    @SerializedName("sys")
    private Date sys[];

    @SerializedName("name")
    private String name;


    public Weather() {
    }


    public Weather(float[] coOrd, String[] weather, float[] main, String visibility, String[] wind, String[] clouds, Date dt, Date[] sys, String name) {
        this.coOrd = coOrd;
        this.weather = weather;
        this.main = main;
        this.visibility = visibility;
        this.wind = wind;
        this.clouds = clouds;
        this.dt = dt;
        this.sys = sys;
        this.name = name;
    }

    protected Weather(Parcel in) {
        coOrd = in.createFloatArray();
        weather = in.createStringArray();
        main = in.createFloatArray();
        visibility = in.readString();
        wind = in.createStringArray();
        clouds = in.createStringArray();
        name = in.readString();
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

    public float[] getCoOrd() {
        return coOrd;
    }

    public void setCoOrd(float[] coOrd) {
        this.coOrd = coOrd;
    }

    public String[] getWeather() {
        return weather;
    }

    public void setWeather(String[] weather) {
        this.weather = weather;
    }

    public float[] getMain() {
        return main;
    }

    public void setMain(float[] main) {
        this.main = main;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String[] getWind() {
        return wind;
    }

    public void setWind(String[] wind) {
        this.wind = wind;
    }

    public String[] getClouds() {
        return clouds;
    }

    public void setClouds(String[] clouds) {
        this.clouds = clouds;
    }

    public Date getDt() {
        return dt;
    }

    public void setDt(Date dt) {
        this.dt = dt;
    }

    public Date[] getSys() {
        return sys;
    }

    public void setSys(Date[] sys) {
        this.sys = sys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "coOrd=" + Arrays.toString(coOrd) +
                ", weather=" + Arrays.toString(weather) +
                ", main=" + Arrays.toString(main) +
                ", visibility='" + visibility + '\'' +
                ", wind=" + Arrays.toString(wind) +
                ", clouds=" + Arrays.toString(clouds) +
                ", dt=" + dt +
                ", sys=" + Arrays.toString(sys) +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloatArray(coOrd);
        dest.writeStringArray(weather);
        dest.writeFloatArray(main);
        dest.writeString(visibility);
        dest.writeStringArray(wind);
        dest.writeStringArray(clouds);
        dest.writeString(name);
    }
}
