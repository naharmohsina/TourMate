package com.example.android.tourmatefinalproject;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit;
    private static String base_url="https://maps.googleapis.com/maps/api/";

    public static Retrofit getRetrofit(){

        retrofit = new Retrofit.Builder().baseUrl(base_url).addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }
}
