package com.example.coviddata;

import androidx.core.view.accessibility.AccessibilityEventCompat;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MyInterface {

    @GET("dayone/country/{input}")
    Call<String>getValue(@Path("input") String i);

}
