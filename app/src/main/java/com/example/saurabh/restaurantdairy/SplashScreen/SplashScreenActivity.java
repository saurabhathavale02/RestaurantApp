package com.example.saurabh.restaurantdairy.SplashScreen;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.saurabh.restaurantdairy.R;
import com.example.saurabh.restaurantdairy.RestaurantLocate.RestaurantLocateActivity;
import com.example.saurabh.restaurantdairy.root.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by saura on 6/25/2017.
 */

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenMVP.View
{
    private final String TAG =SplashScreenActivity.class.getName();

    @BindView(R.id.moto)
    TextView moto;
    @BindView(R.id.app_name)
    TextView AppName;


    @Inject
    SplashScreenMVP.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);
        Typeface face= Typeface.createFromAsset(getAssets(), "fonts/angelina.ttf");
        AppName.setTypeface(face);
        moto.setTypeface(face);

        presenter.WaitForSecond();

    }


    @Override
    public void showError(String message)
    {

    }

    @Override
    public void showComplete()
    {
        Intent intent =new Intent(SplashScreenActivity.this,RestaurantLocateActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    protected void onStart()
    {
        super.onStart();
        presenter.setView(this);


    }

    @Override
    protected void onPause()
    {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxUnsubscribe();

    }
}
