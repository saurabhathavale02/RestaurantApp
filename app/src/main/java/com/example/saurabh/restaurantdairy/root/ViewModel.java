package com.example.saurabh.restaurantdairy.root;

/**
 * Created by saura on 6/18/2017.
 */

public class ViewModel
{
    String RestaurantName;

    public ViewModel(String restaurantName)
    {
        RestaurantName = restaurantName;
    }

    public String getRestaurantName() {
        return RestaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        RestaurantName = restaurantName;
    }
}
