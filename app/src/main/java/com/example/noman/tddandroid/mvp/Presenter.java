package com.example.noman.tddandroid.mvp;

/**
 * Created by noman on 15/2/18.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}
