package com.example.saurabh.restaurantdairy.RestaurantLocate;

import com.example.saurabh.restaurantdairy.root.Repository;
import com.example.saurabh.restaurantdairy.root.RestaurantApiService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by saura on 6/18/2017.
 */
@Module
public class RestaurantLocateModule
{
    @Provides
    public RestaurantLocateActivityMVP.Presenter provideTopMoviesActivityPresenter(RestaurantLocateActivityMVP.Model restaurantModel) {
        return new RestaurantLocatePresenter(restaurantModel);
    }

    @Provides
    public RestaurantLocateActivityMVP.Model provideTopMoviesActivityModel(Repository repository) {
        return new RestaurantLocateModel(repository);
    }

    @Singleton
    @Provides
    public Repository provideRepo(RestaurantApiService restaurantApiService) {
        return new RestaurantLocateRepository(restaurantApiService);
    }


}
