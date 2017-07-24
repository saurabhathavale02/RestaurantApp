package com.example.saurabh.restaurantdairy.root;

/**
 * Created by saura on 6/18/2017.
 */

import com.example.saurabh.restaurantdairy.MenuDisplay.MenuDisplayActivity;
import com.example.saurabh.restaurantdairy.MenuDisplay.MenuDisplayModule;
import com.example.saurabh.restaurantdairy.RestaurantLocate.RestaurantLocateActivity;
import com.example.saurabh.restaurantdairy.RestaurantLocate.RestaurantLocateModule;
import com.example.saurabh.restaurantdairy.SplashScreen.SplashScreenActivity;
import com.example.saurabh.restaurantdairy.SplashScreen.SplashScreenModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class,RestaurantLocateModule.class, RestaurantLocateApiModule.class, SplashScreenModule.class, MenuDisplayModule.class})
public interface ApplicationComponent
{
    void inject(RestaurantLocateActivity restaurantLocateActivity);
    void inject(SplashScreenActivity splashScreenActivity);
    void inject(MenuDisplayActivity menuDisplayActivity);
}
