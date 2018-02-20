package com.example.noman.tddandroid.ui;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.Toast;

import com.example.noman.tddandroid.R;

import java.io.IOException;
import java.util.List;

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
       // mainPresenter.getTopss();
        mainPresenter.getAllEmployee(new NetworkHandler());
        getZipCode();


    }

    private void  getZipCode()

    {
        Geocoder gc = new Geocoder(this);
        if (gc.isPresent()) {
            try {
                List<Address> list = gc.getFromLocation(37.42279, -122.08506, 1);
                Address address = list.get(0);

                StringBuffer str = new StringBuffer();
//                str.append(“Name: ” + address.getLocality() + “\n”);
//                str.append(“Sub-Admin Ares: ” + address.getSubAdminArea() + “\n”);
//                str.append(“Admin Area: ” + address.getAdminArea() + “\n”);
//                str.append(“Country: ” + address.getCountryName() + “\n”);
//                str.append(“Country Code: ” + address.getCountryCode() + “\n”);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
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
    public void employeeRestopresponseponse(List<Topics> employee) {

    }

    @Override
    public void employeeResponse(Employee employee) {

    }

    @Override
    public void errorOccured(Throwable e) {

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
    public void showProgressIndicator() {

    }

    @Override
    public void makeToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }
}
