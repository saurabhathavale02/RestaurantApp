package com.example.saurabh.restaurantdairy.MenuDisplay;

import com.example.saurabh.restaurantdairy.apimodel.menujson.MenuData;

import rx.Observable;

/**
 * Created by saura on 7/11/2017.
 */

public interface MenuApi
{
    Observable<MenuData> GetMenuData();
}
