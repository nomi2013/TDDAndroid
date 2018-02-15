package com.example.noman.tddandroid.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.noman.tddandroid.R;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MainActivity extends DaggerAppCompatActivity implements MainView{


    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainPresenter.attachView(this);
       // mainPresenter.getData();
        mainPresenter.getRealData();
    }

    @Override
    public void onMainLoaded() {
        makeToast("Nice..");
    }

    @Override
    public String getToken() {
        return "";
    }

    @Override
    public String getRealToken() {
        return "hgfhddgdgfdgd";
    }

    @Override
    public void emptyToken(String s) {
        makeToast(s);
    }

    @Override
    public void emptyNonToken(String s) {
        makeToast(s);
    }

    @Override
    public void makeToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
