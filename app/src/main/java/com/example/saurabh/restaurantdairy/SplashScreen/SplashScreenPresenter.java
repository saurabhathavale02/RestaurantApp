package com.example.saurabh.restaurantdairy.SplashScreen;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by saura on 6/25/2017.
 */

public class SplashScreenPresenter implements SplashScreenMVP.Presenter
{
    private SplashScreenMVP.View view;
    private Subscription subscription = null;


    @Override
    public void WaitForSecond()
    {
        subscription=Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {


                subscriber.onNext("complete");

            }
        })
                .debounce(2, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(final String s)
                    {
                        view.showComplete();
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
    public void setView(SplashScreenMVP.View view)
    {
        this.view = view;

    }
}
