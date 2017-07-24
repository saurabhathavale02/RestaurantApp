package com.example.saurabh.restaurantdairy.SplashScreen;

import android.util.Log;

import dagger.Module;
import dagger.Provides;

/**
 * Created by saura on 6/26/2017.
 */

@Module
public class SplashScreenModule
{
    @Provides
    public SplashScreenMVP.Presenter provideSplashScreenActivityPresenter()
    {
        Log.i("test","in splash module");
        return new SplashScreenPresenter();
    }
}
