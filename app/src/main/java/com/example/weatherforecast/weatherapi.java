package com.example.weatherforecast;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface weatherapi {
    @GET("onecall")
    Call<ExampleList> getweather(@Query("lat") String lat,
                                   @Query("lon") String lon,
                                   @Query("exclude") String exclude,
                                   @Query("units") String units,
                                   @Query("appid") String apikey);
}