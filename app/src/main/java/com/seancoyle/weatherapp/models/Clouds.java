package com.seancoyle.weatherapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Clouds {

    @SerializedName("all")
    @Expose
    private Integer all;

    public Clouds() {
    }

    public Clouds(Integer all) {
        super();
        this.all = all;
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }


    @Override
    public String toString() {
        return "Clouds{" +
                "all=" + all +
                '}';
    }
}
