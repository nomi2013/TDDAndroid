package com.example.noman.tddandroid.ui;

import com.example.noman.tddandroid.mvp.MvpView;

import java.util.List;

/**
 * Created by mertsimsek on 25/05/2017.
 */

public interface MainView extends MvpView{
    void onMainLoaded();
    void makeToast(String s);
    String getToken();
    void emptyToken(String s);
    void emptyNonToken(String s);

    void employeeResponse(Employee employee);

    void errorOccured(Throwable e);

    void employeeRestopresponseponse(List<Topics> employee);

    void showProgressIndicator();
}
