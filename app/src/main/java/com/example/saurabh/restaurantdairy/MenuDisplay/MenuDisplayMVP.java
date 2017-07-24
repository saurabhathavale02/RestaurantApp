package com.example.saurabh.restaurantdairy.MenuDisplay;

import android.net.ConnectivityManager;

import com.example.saurabh.restaurantdairy.apimodel.menujson.MenuData;

import rx.Observable;

/**
 * Created by saura on 6/25/2017.
 */

public interface MenuDisplayMVP
{
    public interface View
    {
        void showError(String message);
        void showMenu(MenuData menuData);
    }

    public  interface Presenter
    {
        void LoadMenu();
        void rxUnsubscribe();
        void setView(MenuDisplayMVP.View view);
        Boolean isNetworkConnected(ConnectivityManager cm);
        Boolean isInternetAvailable();
    }

    public interface Model
    {
        Observable<MenuData> menudata();
    }
}
