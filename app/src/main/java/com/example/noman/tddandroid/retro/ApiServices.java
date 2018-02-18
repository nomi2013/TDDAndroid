package com.example.noman.tddandroid.retro;

import com.example.noman.tddandroid.ui.Employee;
import com.example.noman.tddandroid.ui.Topics;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by noman on 16/2/18.
 */

public interface ApiServices {

    @GET("/v2/5a864d37310000740025322a")
    Observable<Employee> getEmployees();

    @GET("topics/")
    Observable<List<Topics>> getTopicsRx();
}
