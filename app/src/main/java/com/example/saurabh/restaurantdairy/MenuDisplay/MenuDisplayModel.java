package com.example.saurabh.restaurantdairy.MenuDisplay;

import com.example.saurabh.restaurantdairy.apimodel.menujson.MenuData;

import rx.Observable;

/**
 * Created by saura on 6/25/2017.
 */

public class MenuDisplayModel  implements MenuDisplayMVP.Model
{
    private MenuRepository menuRepository;

    public MenuDisplayModel(MenuRepository menuRepository)
    {
        this.menuRepository = menuRepository;
    }

    @Override
    public Observable<MenuData> menudata()
    {
        return menuRepository.GetMenuData();
    }
}
