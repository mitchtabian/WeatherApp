package com.seancoyle.weatherapp.util;

import android.util.Log;

import com.seancoyle.weatherapp.R;
import com.seancoyle.weatherapp.models.Weather;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateManagement {


    /**
     * Convert milliseconds to time
     *
     * @param longDateTime
     * @return time
     */
    public static String millisecondToHour(long longDateTime) {
        String time = "";
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            time = formatter.format(longDateTime);
        } catch (Exception e) {
            Log.e("formattedDateFromString", "Exception in formateDateFromstring(): " + e.getMessage());
        }
        return time;
    }

    /**
     * Add day to dateTime
     *
     * @param dateTime
     * @param dayCount add as many days as you want
     * @return Get dateTime with name of day
     */
    public static String getDayOfDate(String dateTime, int dayCount) {
        String date = dateTime;
        String inputFormat = "yyyy-MM-dd HH:mm:ss";
        Date parsed = null;
        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());

        try {
            parsed = df_input.parse(dateTime);
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd EEEE");
            date = formatter.format(parsed.getTime() + (dayCount * 24 * 60 * 60 * 1000));
        } catch (Exception e) {
            Log.e("formattedDateFromString", "Exception in formateDateFromstring(): " + e.getMessage());
        }
        return date;
    }

    /**
     * Convert dateTime to time
     *
     * @param dateTime
     * @return time
     */
    public static String dateToTime(String dateTime) {
        String time = dateTime;
        String inputFormat = "yyyy-MM-dd HH:mm:ss";
        Date parsed = null;
        SimpleDateFormat df_input = new SimpleDateFormat(inputFormat, java.util.Locale.getDefault());

        try {
            parsed = df_input.parse(dateTime);
            SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
            time = formatter.format(parsed.getTime());
        } catch (Exception e) {
            Log.e("formattedDateFromString", "Exception in formateDateFromstring(): " + e.getMessage());
        }
        return time;
    }

    /**
     * Get icon of day from weather data
     *
     * @param weather
     * @return drawable icon
     */
    public static int getIconOfDay(Weather weather) {
        boolean day = weather.getIcon().contains("d");
        switch (weather.getIcon().substring(0, 2)) {

            case "01":
                return day ? R.drawable.ic_clear_sky : R.drawable.ic_clear_sky_night;
            case "02":
                return day ? R.drawable.ic_few_clouds : R.drawable.ic_few_clouds_night;
            case "03":
                return day ? R.drawable.ic_scattered_clouds : R.drawable.ic_scattered_clouds_night;
            case "04":
                return day ? R.drawable.ic_broken_clouds : R.drawable.ic_broken_clouds_night;
            case "09":
                return day ? R.drawable.ic_shower_rain : R.drawable.ic_shower_rain_night;
            case "10":
                return day ? R.drawable.ic_rain : R.drawable.ic_rain_night;
            case "11":
                return day ? R.drawable.ic_thunderstorm : R.drawable.ic_thunderstorm_night;
            case "13":
                return day ? R.drawable.ic_snow : R.drawable.ic_snow_night;
            case "50":
                return day ? R.drawable.ic_mist : R.drawable.ic_mist_night;
        }
        return day ? R.drawable.ic_clear_sky : R.drawable.ic_clear_sky_night;

        }
    }

