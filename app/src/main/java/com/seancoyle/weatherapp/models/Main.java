package com.seancoyle.weatherapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "main")
public class Main {

    @PrimaryKey(autoGenerate = true)
    long mainId;

    @SerializedName("temp")
    @Expose
    @ColumnInfo(name ="temp")
    private Double temp;

    @SerializedName("feels_like")
    @Expose
    @ColumnInfo(name ="feelsLike")
    private Double feelsLike;

    @SerializedName("temp_min")
    @Expose
    @ColumnInfo(name ="tempMin")
    private Double tempMin;

    @SerializedName("temp_max")
    @Expose
    @ColumnInfo(name ="tempMax")
    private Double tempMax;

    @SerializedName("pressure")
    @Expose
    @ColumnInfo(name ="pressure")
    private Long pressure;

    @SerializedName("sea_level")
    @Expose
    @ColumnInfo(name ="seaLevel")
    private Long seaLevel;

    @SerializedName("grnd_level")
    @Expose
    @ColumnInfo(name ="grndLevel")
    private Long grndLevel;

    @SerializedName("humidity")
    @Expose
    @ColumnInfo(name ="humidity")
    private Long humidity;

    @SerializedName("temp_kf")
    @Expose
    @ColumnInfo(name ="tempKf")
    private Double tempKf;

    /**
     * No args constructor for use in serialization
     *
     */
    public Main() {
    }

    /**
     *
     * @param feelsLike
     * @param tempMax
     * @param temp
     * @param seaLevel
     * @param humidity
     * @param pressure
     * @param tempKf
     * @param grndLevel
     * @param tempMin
     */
    public Main(Double temp, Double feelsLike, Double tempMin, Double tempMax, Long pressure, Long seaLevel, Long grndLevel, Long humidity, Double tempKf) {
        super();
        this.temp = temp;
        this.feelsLike = feelsLike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.seaLevel = seaLevel;
        this.grndLevel = grndLevel;
        this.humidity = humidity;
        this.tempKf = tempKf;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getFeelsLike() {
        return feelsLike;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feelsLike = feelsLike;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Long getPressure() {
        return pressure;
    }

    public void setPressure(Long pressure) {
        this.pressure = pressure;
    }

    public Long getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(Long seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Long getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(Long grndLevel) {
        this.grndLevel = grndLevel;
    }

    public Long getHumidity() {
        return humidity;
    }

    public void setHumidity(Long humidity) {
        this.humidity = humidity;
    }

    public Double getTempKf() {
        return tempKf;
    }

    public void setTempKf(Double tempKf) {
        this.tempKf = tempKf;
    }

    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                ", feelsLike=" + feelsLike +
                ", tempMin=" + tempMin +
                ", tempMax=" + tempMax +
                ", pressure=" + pressure +
                ", seaLevel=" + seaLevel +
                ", grndLevel=" + grndLevel +
                ", humidity=" + humidity +
                ", tempKf=" + tempKf +
                '}';
    }
}
