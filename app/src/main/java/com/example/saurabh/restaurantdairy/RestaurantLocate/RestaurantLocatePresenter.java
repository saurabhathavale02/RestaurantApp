package com.example.saurabh.restaurantdairy.RestaurantLocate;

import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.saurabh.restaurantdairy.apimodel.LocationModel.ResultData;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by saura on 6/18/2017.
 */

public class RestaurantLocatePresenter implements RestaurantLocateActivityMVP.Presenter
{

    private RestaurantLocateActivityMVP.View view;
    private Subscription subscription = null;
    private RestaurantLocateActivityMVP.Model model;
    public RestaurantLocatePresenter(RestaurantLocateActivityMVP.Model model)
    {
        this.model = model;
    }

    @Override
    public void LoadRestaurant(Location mCurrentLocation)
    {

        subscription = model.result(mCurrentLocation).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<ResultData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (view != null) {
                    view.showError("Error getting movies");
                }
            }

            @Override
            public void onNext(ResultData resultData) {
                if (view != null) {
                    view.showRestaurant(resultData);
                }
            }
        });
    }

    @Override
    public void rxUnsubscribe()
    {
        if (subscription != null)
        {
            if (!subscription.isUnsubscribed()) {
                subscription.unsubscribe();
            }
        }
    }

    @Override
    public void setView(RestaurantLocateActivityMVP.View view)
    {
        this.view = view;

    }

    @Override
    public Boolean isNetworkConnected(ConnectivityManager connectivityManager)
    {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }



    @Override
    public Boolean isInternetAvailable()
    {
            return true;


    }
}
