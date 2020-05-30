package com.seancoyle.weatherapp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class AppExecutors {

    /**
     * Instantiate an instance of the AppExecutor
     */
    private static AppExecutors instance;

    /**
     * Use a Singleton of the AppExecutor
     *
     * @return
     */
    public static AppExecutors getInstance(){
        if(instance == null){
            instance = new AppExecutors();
        }
        return instance;
    }

    /**
     * Instantiating 3 background threads used to manage network connections.
     */
    private final ScheduledExecutorService mNetworkIO = Executors.newScheduledThreadPool(3);

    /**
     * public method to access our threads
     * @return
     */
    public ScheduledExecutorService netWorkIO(){
        return mNetworkIO;
    }


}
