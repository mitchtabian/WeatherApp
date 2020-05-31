package com.seancoyle.weatherapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "sys")
public class Sys {

    @PrimaryKey(autoGenerate = true)
    long sysId;

    @SerializedName("pod")
    @Expose
    @ColumnInfo(name ="pod")
    private String pod;

    /**
     * No args constructor for use in serialization
     *
     */
    @Ignore
    public Sys() {
    }

    /**
     *
     * @param pod
     */
    public Sys(String pod) {
        super();
        this.pod = pod;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }


    @Override
    public String toString() {
        return "Sys{" +
                "pod='" + pod + '\'' +
                '}';
    }
}
