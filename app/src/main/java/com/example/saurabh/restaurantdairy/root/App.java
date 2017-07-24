package com.example.saurabh.restaurantdairy.root;

import android.app.Application;

import com.example.saurabh.restaurantdairy.MenuDisplay.MenuDisplayModule;
import com.example.saurabh.restaurantdairy.RestaurantLocate.RestaurantLocateModule;
import com.example.saurabh.restaurantdairy.SplashScreen.SplashScreenModule;
import com.example.saurabh.restaurantdairy.apimodel.LocationModel.ResultData;
import com.example.saurabh.restaurantdairy.apimodel.selectedfoodmodel.SelectedFood;
import com.google.android.gms.location.LocationRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by saura on 6/18/2017.
 */

public class App extends Application
{
    private ApplicationComponent component;
    private static final long INTERVAL = 1000 * 10;
    private static final long FASTEST_INTERVAL = 1000 * 5;
    public LocationRequest mLocationRequest;
    public ResultData restaurantData;
    public Integer SelectedRestaurant;
    public List<SelectedFood> selectedFood;
    @Override
    public void onCreate()
    {
        super.onCreate();
        selectedFood=new ArrayList<SelectedFood>();
        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .restaurantLocateModule(new RestaurantLocateModule())
                .restaurantLocateApiModule(new RestaurantLocateApiModule())
                .splashScreenModule(new SplashScreenModule())
                .menuDisplayModule(new MenuDisplayModule())
                .build();

        createLocationRequest();
    }
    public ApplicationComponent getComponent()
    {
        return component;
    }

    public void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }



}
