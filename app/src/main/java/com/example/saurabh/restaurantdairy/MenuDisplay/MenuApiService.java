package com.example.saurabh.restaurantdairy.MenuDisplay;

import com.example.saurabh.restaurantdairy.apimodel.menujson.MenuData;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by saura on 7/11/2017.
 */

public interface MenuApiService
{
    @GET("https://davids-restaurant.herokuapp.com/menu_items.json?")
    Observable<MenuData> getMenu(@Query("category") String category);
}
