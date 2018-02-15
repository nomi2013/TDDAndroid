package com.example.noman.tddandroid;

import com.example.noman.tddandroid.di.AppComponent;
import com.example.noman.tddandroid.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;


/**
 * Created by mertsimsek on 25/05/2017.
 */

public class AndroidSampleApp extends DaggerApplication {


    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        AppComponent appComponent = DaggerAppComponent.builder().application(this).build();
        appComponent.inject(this);
        return appComponent;
    }
}
