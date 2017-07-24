package com.example.saurabh.restaurantdairy.RestaurantLocate;

import android.location.Location;

import com.example.saurabh.restaurantdairy.apimodel.LocationModel.ResultData;
import com.example.saurabh.restaurantdairy.root.Repository;

import rx.Observable;

/**
 * Created by saura on 6/18/2017.
 */

public class RestaurantLocateModel implements RestaurantLocateActivityMVP.Model
{
    private Repository repository;

    public RestaurantLocateModel(Repository repository)
    {
        this.repository = repository;
    }



    @Override
    public Observable<ResultData> result(Location mCurrentLocation)
        {
            return repository.GetRestaurantData(mCurrentLocation);
        }

}
