package com.seancoyle.weatherapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "coord")
public class Coord {

    @PrimaryKey(autoGenerate = true)
    private long coordId;

    @SerializedName("lon")
    @Expose
    @ColumnInfo(name ="lon")
    private double lon;

    @SerializedName("lat")
    @Expose
    @ColumnInfo(name ="lat")
    private double lat;

    @Ignore
    public Coord() {
    }

    public Coord(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public long getCoordId() {
        return coordId;
    }

    public void setCoordId(long coordId) {
        this.coordId = coordId;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}
