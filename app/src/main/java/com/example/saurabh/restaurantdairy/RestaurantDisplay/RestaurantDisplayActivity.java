package com.example.saurabh.restaurantdairy.RestaurantDisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.saurabh.restaurantdairy.MenuDisplay.MenuDisplayActivity;
import com.example.saurabh.restaurantdairy.R;
import com.example.saurabh.restaurantdairy.apimodel.LocationModel.Restaurant_;
import com.example.saurabh.restaurantdairy.root.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by saura on 6/20/2017.
 */

public class RestaurantDisplayActivity extends AppCompatActivity
{

    private final String TAG =RestaurantDisplayActivity.class.getName();
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.toolbar_progress_bar) ProgressBar progressBar;
    @BindView(R.id.restaurant_name) TextView RestaurantName;
    @BindView(R.id.rating) EditText rating;
    @BindView(R.id.food_type) TextView Food_Type;
    @BindView(R.id.hours) TextView Hours;
    @BindView(R.id.address) TextView Address;




        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.restaurantdisplay);
            ButterKnife.bind(this);
            setSupportActionBar(toolbar);

            Restaurant_ selectedRestaurant=((App) getApplicationContext()).restaurantData.getRestaurants().get(((App) getApplicationContext()).SelectedRestaurant).getRestaurant();
            getSupportActionBar().setDisplayShowTitleEnabled(false);
           // Log.i(TAG,"selected restaurant="+ selectedRestaurant.getUserRating().getAggregateRating());


            RestaurantName.setText(selectedRestaurant.getName());
            Address.setText(selectedRestaurant.getLocation().getAddress());
            Food_Type.setText(selectedRestaurant.getCuisines()+" ($$)");
            String websitehome[]=selectedRestaurant.getUrl().split("\\?");
           // Website.setText(websitehome[0]);
            rating.setText("4.3");

            Hours.setText("Open Hours Today -: 9.00 am to 10.00 pm");
            //Call.setText("682-583-7746");


        }
        @OnClick(R.id.menu)
        public void DisplayMemu(View view)
        {
            Intent i = new Intent(RestaurantDisplayActivity.this, MenuDisplayActivity.class);
            startActivity(i);
        }

        @OnClick(R.id.locate)
        public void Locate(View view)
        {
            Intent i = new Intent(RestaurantDisplayActivity.this, MenuDisplayActivity.class);
            startActivity(i);
        }

        @OnClick(R.id.website)
        public void Website(View view)
        {
            Intent i = new Intent(RestaurantDisplayActivity.this, MenuDisplayActivity.class);
            startActivity(i);
        }


}
