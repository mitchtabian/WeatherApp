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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherRecyclerAdapter.WeatherViewHolder> {

    /**
     * Sets the context to this class.
     */
    private Context mContext;

    /**
     * List of type weather
     */
    private List<WeatherList> mWeatherList;
   // private List<WeatherResponse> mWeatherResponse;
    private List<Weather> mWeather;

    private WeatherResponse mWeatherResponse;

    /**
     * Listener to detect user clicks on a specific viewholder in the recycler view.
     */
    private OnLogListener mOnLogListener;

    /**
     * Int which is used to track the position in the recycler view.
     */
    private int position;


    private Date date;

    private String formattedDate, baseDate;

    /**
     * Custom OnClick Interface
     */
    public interface OnLogListener {
        void onLogClick(final int position);
    }

    public void setOnLogListener(OnLogListener listener) {
        mOnLogListener = listener;
    }


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

        // moodIconArrayList();

        WeatherList currentWeather = mWeatherList.get(position);
       // WeatherResponse weatherResponse = new WeatherResponse();

        // Gets date from the database for the log
        long weatherDate = currentWeather.getDt()*1000;
         Date date=new Date(weatherDate);
        SimpleDateFormat df2 = new SimpleDateFormat("dd/MM/yy HH:mm");
        String dateText = df2.format(date);



        // Converts the date into the desired format to be displayed on screen to the user.
        // DateFormat toFormat = new SimpleDateFormat("E, d MMMM, yyyy HH:mm");
        //String workoutDateString = toFormat.format(weatherDate);


        // BELOW CODE IS FOR HANDLING DATES STORES IN THE DB AS A STRING
        /*
        DateFormat fromFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        fromFormat.setLenient(false);
        DateFormat toFormat = new SimpleDateFormat("E, d MMMM, yyyy HH:mm");
        toFormat.setLenient(false);
        try {
             inputDate = fromFormat.parse(baseDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        formattedDate = toFormat.format(inputDate);*/


        // Sets the date string to the View holder
        holder.mDate.setText(dateText);
        holder.mTemperature.setText(currentWeather.getMain().getTemp().toString());
        holder.mWeatherImage.setImageResource(R.drawable.sunny_clear);
        holder.mLocation.setText(mWeatherResponse.getCity().getName());
       //holder.mDescription.setText(m);


    }


    @Override
    public int getItemCount() {

        return mWeatherList.size();
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
