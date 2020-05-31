package com.seancoyle.weatherapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "weather")
public class Weather {

    @SerializedName("id")
    @Expose
    @PrimaryKey
    private Long id;

    @SerializedName("main")
    @Expose
    @ColumnInfo(name ="main")
    private String main;

    @SerializedName("description")
    @Expose
    @ColumnInfo(name ="description")
    private String description;

    @SerializedName("icon")
    @Expose
    @ColumnInfo(name ="icon")
    private String icon;

    @Ignore
    public Weather() {
    }

    public Weather(Long id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
