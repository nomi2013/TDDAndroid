package com.example.noman.tddandroid.di;

import com.example.noman.tddandroid.ui.MainActivity;
import com.example.noman.tddandroid.ui.MainActivityModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by mertsimsek on 25/05/2017.
 */
@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindMainActivity();

}
