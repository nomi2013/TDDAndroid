package com.example.noman.tddandroid.ui;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by noman on 16/2/18.
 */

public class Employee {
    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("error discription")
    @Expose
    private String errorDiscription;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("status code")
    @Expose
    private String statusCode;

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public String getErrorDiscription() {
        return errorDiscription;
    }

    public void setErrorDiscription(String errorDiscription) {
        this.errorDiscription = errorDiscription;
    }

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }
}
