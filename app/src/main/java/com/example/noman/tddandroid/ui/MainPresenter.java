package com.example.noman.tddandroid.ui;

import android.text.TextUtils;

import com.example.noman.tddandroid.mvp.BasePresenter;

/**
 * Created by noman on 15/2/18.
 */

public class MainPresenter extends BasePresenter<MainView> {

    @Override
    public void attachView(MainView mvpView) {
        super.attachView(mvpView);
    }

    public void getData() {
        if (getMvpView() != null) {
            if(getMvpView().getToken() == "") {
                getMvpView().emptyNonToken("Token is non empty.");
            } else {
                getMvpView().emptyToken("Token is empty.");
            }
        }
    }

    public void getRealData() {
        if (getMvpView() != null) {
           // int length = getMvpView().getRealToken().length();
            if(true) {
                getMvpView().emptyNonToken("Token is non empty.");
            } else {
                getMvpView().emptyToken("Token is empty.");
            }
        }
    }
}
