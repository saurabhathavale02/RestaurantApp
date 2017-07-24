package com.example.saurabh.restaurantdairy.root;

import android.location.Location;

import com.example.saurabh.restaurantdairy.apimodel.LocationModel.ResultData;

import rx.Observable;

/**
 * Created by saura on 6/18/2017.
 */

public interface Repository
{
    Observable<ResultData> GetRestaurantData(Location mCurrentLocation);

}
