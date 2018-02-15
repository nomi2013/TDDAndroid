package com.example.noman.tddandroid.ui;

import com.example.noman.tddandroid.data.ApiService;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

/**
 * Created by mertsimsek on 25/05/2017.
 */
@Module
public abstract class MainActivityModule {

    @Provides
    static MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Binds
    abstract MainView provideMainView(MainActivity mainActivity);
}
