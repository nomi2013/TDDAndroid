package com.example.noman.tddandroid.ui;

import com.example.noman.tddandroid.retro.ApiServices;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Inject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by noman on 16/2/18.
 */

public class NetworkHandler {

    @Inject
    public NetworkHandler() {

    }

    public ApiServices provideRetrofit() {
       // http://www.mocky.io/v2/5a864d37310000740025322a
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.mocky.io")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(ApiServices.class);
    }

}
