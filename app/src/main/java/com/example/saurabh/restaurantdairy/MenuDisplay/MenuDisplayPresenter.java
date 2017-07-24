package com.example.saurabh.restaurantdairy.MenuDisplay;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.saurabh.restaurantdairy.apimodel.menujson.MenuData;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by saura on 6/25/2017.
 */

public class MenuDisplayPresenter implements MenuDisplayMVP.Presenter
{

    private MenuDisplayMVP.View view;
    private Subscription subscription = null;
    private MenuDisplayMVP.Model model;

    public MenuDisplayPresenter(MenuDisplayMVP.Model model)
    {
        this.model = model;
    }

    @Override
    public void LoadMenu()
    {
        subscription = model.menudata().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<MenuData>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (view != null) {
                    // view.showSnackbar("Error getting movies");
                }
            }

            @Override
            public void onNext(MenuData menuData)
            {
                if (view != null)
                {
                    view.showMenu(menuData);
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
    public void setView(MenuDisplayMVP.View view)
    {
        this.view = view;
    }

    @Override
    public Boolean isNetworkConnected(ConnectivityManager connectivityManager) {
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    @Override
    public Boolean isInternetAvailable() {
        return true;
    }
}
