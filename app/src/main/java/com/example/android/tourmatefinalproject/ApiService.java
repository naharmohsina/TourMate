package com.example.android.tourmatefinalproject;

import com.example.android.tourmatefinalproject.Nearby.NearbyResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ApiService {

    @GET()
    Call<NearbyResponse> getNearby(@Url String url);

    @GET
    Call<NearbyResponse> getNextPageToken(@Url String url);
}
