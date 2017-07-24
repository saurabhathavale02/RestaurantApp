package com.example.saurabh.restaurantdairy.root;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.saurabh.restaurantdairy.MenuDisplay.MenuApiService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by saura on 6/19/2017.
 */

@Module
public class RestaurantLocateApiModule
{
    String mBaseUrl="https://developers.zomato.com/api/v2.1/";

    @Provides
    public SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }
/*
    @Provides
    @Singleton
    public Cache provideHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }
*/
    /*
    @Provides
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

*/
    public Interceptor interceptor = new Interceptor()
    {
        @Override
        public Response intercept(Chain chain) throws IOException {
            final Request request = chain.request().newBuilder()
                    .addHeader("user-key", "9e41f370cb28137632e5d41ce822dadd")
                    .addHeader("Accept", "application/json")
                    .build();

            return chain.proceed(request);
        }
    };

    @Provides
    @Singleton
    public OkHttpClient provideOkhttpClient() {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);
        //client.cache(cache);
        client.connectTimeout(15, TimeUnit.SECONDS);
        client.readTimeout(15L, TimeUnit.SECONDS);
        client.writeTimeout(15L, TimeUnit.SECONDS);

        return client.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(String BaseUrl, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BaseUrl)
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    public RestaurantApiService provideApiService()
    {
        return provideRetrofit(mBaseUrl, provideOkhttpClient()).create(RestaurantApiService.class);

    }

    @Provides
    @Singleton
    public MenuApiService provideMenuApiService()
    {
        return provideRetrofit(mBaseUrl, provideOkhttpClient()).create(MenuApiService.class);

    }
}
