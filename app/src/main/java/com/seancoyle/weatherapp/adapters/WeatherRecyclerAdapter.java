package com.seancoyle.weatherapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.seancoyle.weatherapp.R;
import com.seancoyle.weatherapp.models.Weather;
import com.seancoyle.weatherapp.models.WeatherList;
import com.seancoyle.weatherapp.models.WeatherResponse;
import com.seancoyle.weatherapp.util.DateManagement;

import java.util.List;

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.WeatherViewHolder> {

    /**
     * Sets the context to this class.
     */
    private Context mContext;

    /**
     * List of type weather results
     */
    private List<WeatherList> mWeatherList;
    private List<Weather> mWeather;
    private WeatherResponse mWeatherResponse;

    /**
     * Listener to detect user clicks on a specific viewholder in the recycler view.
     */
    private RecyclerOnClickListener mOnLogListener;

    /**
     * Int which is used to track the position in the recycler view.
     */
    private int position;


    /**
     * Custom OnClick Interface
     */
    public interface RecyclerOnClickListener {
        void onLogClick(final int position);
    }

    /**
     * Method which contains the custom onclick listener for the recyclerview
     * @param listener
     */
    public void setOnLogListener(RecyclerOnClickListener listener) {
        mOnLogListener = listener;
    }


    /**
     *
     * @param context
     * @param mWeatherList
     * @param mWeatherResponse2
     * @param mWeather2
     */
    public WeatherRecyclerAdapter(Context context, List<WeatherList> mWeatherList, WeatherResponse mWeatherResponse2, List<Weather> mWeather2) {
        this.mContext = context;
        this.mWeatherList = mWeatherList;
        this.mWeatherResponse = mWeatherResponse2;
        this.mWeather = mWeather2;

    }

    @NonNull
    @Override
    public WeatherRecyclerAdapter.WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.layout_weather_list_item, parent, false);
        return new WeatherViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull WeatherRecyclerAdapter.WeatherViewHolder holder, int position) {


        // Only displays one forecast for each day at 12:00:00
        String date[] = DateManagement.getDayOfDate(mWeatherList.get(0).getDtTxt(), position + 1).split(" ");
        holder.mDate.setText(date[1]);
        int index = getIndexOfDate(date[0] + " 12:00:00");

        // Get the temperature as a double and remove the decimal places
        double tempDouble = mWeatherList.get(index).getMain().getTemp();
        int tempInt = (int) Math.round(tempDouble);
        String tempString = String.valueOf(tempInt);
        holder.mTemperature.setText(tempString+"°C");

        // Set the Icons for the corresponding weather
        holder.mWeatherImage.setImageResource(DateManagement.getIconOfDay(mWeatherList.get(index).getResults().get(0)));

        // Set the weather description
        holder.mDescription.setText(""+mWeatherList.get(index).getResults().get(0).getDescription());

        // Set the city name
        holder.mLocation.setText(mWeatherResponse.getCity().getName());

    }


    @Override
    public int getItemCount() {

        // Returns a forecast list for 5 days
        return mWeatherList.size()> 0 ? 5 : 0;
    }


    /**
     * Loops through the list of results and gets the entries which match the specified date/time
     * @param date
     * @return
     */
    public int getIndexOfDate(String date) {
        int index = 0;
        for (int i = 0; i < mWeatherList.size(); i++) {
            if (date.equals(mWeatherList.get(i).getDtTxt())) {
                index = i;
                break;
            }
        }
        return index;
    }


    public class WeatherViewHolder extends RecyclerView.ViewHolder {

        /**
         * Variables which contain the Text and Image views from the XML file.
         */
        private TextView mTemperature;
        private TextView mDate;
        private TextView mDescription;
        private TextView mLocation;
        private ImageView mWeatherImage;


        public WeatherViewHolder(@NonNull View itemView) {
            super(itemView);

            //Assign variables to the XMl file.
            mTemperature = itemView.findViewById(R.id.temperature);
            mDate = itemView.findViewById(R.id.date);
            mDescription = itemView.findViewById(R.id.description);
            mLocation = itemView.findViewById(R.id.location);
            mWeatherImage = itemView.findViewById(R.id.weatherImage);


            // Sets the custom on click listener for the recycler view using the position.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnLogListener != null) {
                        position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mOnLogListener.onLogClick(position);
                        }
                    }
                }

            });


        }
    }


}
