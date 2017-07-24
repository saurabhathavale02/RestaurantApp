package com.example.saurabh.restaurantdairy.MenuDisplay;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by saura on 6/25/2017.
 */
@Module
public class MenuDisplayModule
{
    @Provides
    public MenuDisplayMVP.Presenter provideMenuPresenter(MenuDisplayMVP.Model menuModel) {
        return new MenuDisplayPresenter(menuModel);
    }

    @Provides
    public MenuDisplayMVP.Model provideMenuModel(MenuRepository menuRepository) {
        return new MenuDisplayModel(menuRepository);
    }

    @Singleton
    @Provides
    public MenuRepository provideRepository(MenuApiService menuApiService) {
        return new MenuRepository(menuApiService);
    }


}
