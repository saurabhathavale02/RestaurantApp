package com.example.saurabh.restaurantdairy.RestaurantLocate;

import android.location.Location;

import com.example.saurabh.restaurantdairy.MenuDisplay.MenuApi;
import com.example.saurabh.restaurantdairy.apimodel.LocationModel.ResultData;
import com.example.saurabh.restaurantdairy.apimodel.menujson.MenuData;
import com.example.saurabh.restaurantdairy.root.Repository;
import com.example.saurabh.restaurantdairy.root.RestaurantApiService;

import rx.Observable;

/**
 * Created by saura on 6/19/2017.
 */

public class RestaurantLocateRepository implements Repository,MenuApi
{
    private RestaurantApiService restaurantApiService;
    public RestaurantLocateRepository(RestaurantApiService restaurantApiService)
    {
        this.restaurantApiService=restaurantApiService;
    }

    @Override
    public Observable<ResultData> GetRestaurantData(Location mCurrentLocation)
    {
        String lat=String.valueOf(mCurrentLocation.getLatitude());
        String lon=String.valueOf(mCurrentLocation.getLongitude());
        Observable<ResultData> RestaurantObservable = restaurantApiService.getRestaurant("20",lat,lon);

        return RestaurantObservable;

    }

    @Override
    public Observable<MenuData> GetMenuData()
    {
        return null;
    }


}
