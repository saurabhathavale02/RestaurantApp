package com.example.saurabh.restaurantdairy.SplashScreen;

/**
 * Created by saura on 6/25/2017.
 */

public interface SplashScreenMVP
{
    interface View
    {
        void showError(String message);
        void showComplete();
    }

    interface Presenter
    {
        void WaitForSecond();
        void rxUnsubscribe();
        void setView(SplashScreenMVP.View view);
    }

}
