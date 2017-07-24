package com.example.saurabh.restaurantdairy.MenuDisplay;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.saurabh.restaurantdairy.CartDisplay.CartActivity;
import com.example.saurabh.restaurantdairy.MenuDisplay.Adapter.MenuListFragment;
import com.example.saurabh.restaurantdairy.MenuDisplay.Adapter.PagerAdapter;
import com.example.saurabh.restaurantdairy.R;
import com.example.saurabh.restaurantdairy.apimodel.menujson.MenuData;
import com.example.saurabh.restaurantdairy.root.App;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by saura on 6/25/2017.
 */

public class MenuDisplayActivity extends AppCompatActivity implements MenuDisplayMVP.View
{
    private final String TAG =MenuDisplayActivity.class.getName();

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.id_viewpager)
    ViewPager viewPager;
    @BindView(R.id.id_tabs)
    TabLayout tableLayout;
    @BindView(R.id.showcart)
    Button showcart;
    @BindView(R.id.dashboard)
    Button Dashboard;
    @BindView(R.id.linearlayout)
    LinearLayout linearLayout;
    PagerAdapter adapter;
    @Inject
    MenuDisplayMVP.Presenter presenter;

    ConnectivityManager connectivityManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menudisplay);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        connectivityManager = (ConnectivityManager)this.getSystemService (Context.CONNECTIVITY_SERVICE);


        ((App) getApplication()).getComponent().inject(this);
        if(presenter.isNetworkConnected(connectivityManager) && presenter.isInternetAvailable())
        {
            presenter.LoadMenu();
        }
        else
        {
            Log.i(TAG, "No internet");
            showError("No internet");
        }
        showcart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuDisplayActivity.this, CartActivity.class);
                startActivity(intent);

            }
        });

    }

    @Override
    public void showError(String message)
    {
        Snackbar snackbar = Snackbar
                .make(linearLayout, "No Internet Connectivity", Snackbar.LENGTH_LONG)
                .setAction("Retry", new View.OnClickListener() {
                    @Override
                    public void onClick(View view)
                    {
                        presenter.LoadMenu();
                    }
                });

        snackbar.show();
    }

    @Override
    public void showMenu(MenuData menuData)
    {
        Log.i(TAG,"test");
        Log.i(TAG,"menuData="+menuData.getMenuItems().get(3).getName());
        adapter= new PagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new MenuListFragment(0,menuData.getMenuItems()), "Starters");
        adapter.addFragment(new MenuListFragment(1,menuData.getMenuItems()), "Main Course");
        adapter.addFragment(new MenuListFragment(2,menuData.getMenuItems()), "Deserts");
        viewPager.setAdapter(adapter);
        tableLayout.setTabTextColors(Color.parseColor("#F2F2F2"), Color.parseColor("#FFFFFF"));
        tableLayout.setupWithViewPager(viewPager);

    }




    @Override
    protected void onStart()
    {
        super.onStart();
        presenter.setView(this);

    }
    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxUnsubscribe();
    }


}
