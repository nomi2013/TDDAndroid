package com.example.noman.tddandroid.ui;

import android.util.Log;

import com.example.noman.tddandroid.mvp.BasePresenter;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



/**
 * Created by noman on 15/2/18.
 */

public class MainPresenter extends BasePresenter<MainView> {

    NetworkHandler networkHandler;

    @Override
    public void attachView(MainView mvpView) {
        super.attachView(mvpView);
        networkHandler = new NetworkHandler();

    }

    public void getData() {
        if (getMvpView() != null) {
            if (getMvpView().getToken().equals("")) {
                getMvpView().emptyToken("Token is empty.");
            } else {
                getMvpView().emptyNonToken("Token is non empty.");

            }
        }
    }

    public void getAllEmployee(NetworkHandler handler) {

     //   Single.fromCallable(handler.provideRetrofit().getEmployees()).

      /*  Single.fromCallable(() -> handler.provideRetrofit().getEmployees())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getMvpView().showProgressIndicator())
                .doOnSuccess( employeeObservable -> getMvpView().getToken())
                .doOnError(throwable -> getMvpView().errorOccured(new Throwable("h")) )
                .subscribe();
      */
            handler.provideRetrofit().getEmployees()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<Employee>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Employee employee) {
                            getMvpView().employeeResponse(employee);
                        }

                        @Override
                        public void onError(Throwable e) {
                            getMvpView().errorOccured(e);
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
    }
}
