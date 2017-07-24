package com.example.saurabh.restaurantdairy.root;

import com.example.saurabh.restaurantdairy.apimodel.LocationModel.ResultData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by saura on 6/19/2017.
 */

public interface RestaurantApiService
{

        @GET("https://developers.zomato.com/api/v2.1/search?")
        Observable<ResultData> getRestaurant(@Query("count") String count, @Query("lat") String lat, @Query("lon") String lon);




}
