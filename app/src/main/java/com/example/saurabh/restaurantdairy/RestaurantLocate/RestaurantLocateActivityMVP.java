package com.example.saurabh.restaurantdairy.RestaurantLocate;

import android.location.Location;
import android.net.ConnectivityManager;

import com.example.saurabh.restaurantdairy.apimodel.LocationModel.ResultData;

import rx.Observable;

/**
 * Created by saura on 6/18/2017.
 */

public interface RestaurantLocateActivityMVP
{
    public interface View
    {
        void showError(String message);
        void showRestaurant(ResultData viewModel);

    }

    public  interface Presenter
    {
        void LoadRestaurant(Location mCurrentLocation);
        void rxUnsubscribe();
        void setView(RestaurantLocateActivityMVP.View view);
        Boolean isNetworkConnected(ConnectivityManager cm);
        Boolean isInternetAvailable();
    }

    public interface Model
    {
        Observable<ResultData> result(Location mCurrentLocation);
    }
}
