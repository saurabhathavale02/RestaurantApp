package com.example.saurabh.restaurantdairy.MenuDisplay;

import com.example.saurabh.restaurantdairy.apimodel.menujson.MenuData;

import rx.Observable;

/**
 * Created by saura on 7/11/2017.
 */

public class MenuRepository implements MenuApi {

    private MenuApiService menuApiService;
    public MenuRepository(MenuApiService menuApiService)
    {
        this.menuApiService=menuApiService;
    }



    @Override
    public Observable<MenuData> GetMenuData() {
        Observable<MenuData> MenuObservable = menuApiService.getMenu("C");

        return MenuObservable;
    }
}
