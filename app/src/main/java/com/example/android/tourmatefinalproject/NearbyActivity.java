package com.example.android.tourmatefinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.android.tourmatefinalproject.Nearby.NearbyResponse;
import com.example.android.tourmatefinalproject.Nearby.Result;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NearbyActivity extends AppCompatActivity {


    private ApiService service;
    private Double lat=23.750841;
    private Double lon=90.393293;
    private int radious=1000;
    private double latt,lonn;
    // private String place_type;
    private RecyclerView recyclerView;
    private NearbyAdapter adapter;
    private Spinner spinner;
    private String pageToken;
    List<Result> results;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby);
        recyclerView = findViewById(R.id.recyclerviewID);
        spinner = findViewById(R.id.spinnerID);


        Intent intent = getIntent();

        latt = intent.getDoubleExtra("latitude",0);
        lonn = intent.getDoubleExtra("longitude",0);

        List<String> placeList= new ArrayList<>();
        placeList.add("bank");
        placeList.add("restaurant");
        placeList.add("police");
        placeList.add("atm");
        placeList.add("cafe");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,android.R.layout.simple_dropdown_item_1line,placeList);
        spinner.setAdapter(spinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String place_type= parent.getItemAtPosition(position).toString();
                getNearby(place_type);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }

    private void getNearby(String place_type) {
        String url = String.format("place/nearbysearch/json?location=%f,%f&radius=%d&type=%s&key=%s",latt,lonn,radious,place_type,getResources().getString(R.string.place_key));
        service = ApiClient.getRetrofit().create(ApiService.class);
        Call<NearbyResponse> nearbyResponseCall = service.getNearby(url);
        nearbyResponseCall.enqueue(new Callback<NearbyResponse>() {
            @Override
            public void onResponse(Call<NearbyResponse> call, Response<NearbyResponse> response) {
                if (response.code()==200){
                    NearbyResponse nearbyResponse = response.body();
                    if (nearbyResponse.getNextPageToken()!=null){
                        pageToken= nearbyResponse.getNextPageToken();
                    }
                    results = nearbyResponse.getResults();
                    adapter = new NearbyAdapter(results);
                    recyclerView.setLayoutManager(new LinearLayoutManager(NearbyActivity.this));
                    recyclerView.setAdapter(adapter);
                    // Toast.makeText(MainActivity.this, String.valueOf(results.size()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NearbyResponse> call, Throwable t) {

            }
        });
    }


    public void moreResult(View view) {

        if (!pageToken.isEmpty()){
            service = ApiClient.getRetrofit().create(ApiService.class);
            String url= String.format("place/nearbysearch/json?pagetoken=%s&key=%s",pageToken,getResources().getString(R.string.place_key));
            Call<NearbyResponse> nearbyResponseCall = service.getNextPageToken(url);
            nearbyResponseCall.enqueue(new Callback<NearbyResponse>() {
                @Override
                public void onResponse(Call<NearbyResponse> call, Response<NearbyResponse> response) {
                    if(response.code()==200){

                        NearbyResponse nearbyResponse = response.body();

                        List<Result> newResult = nearbyResponse.getResults();
                        results.addAll(newResult);
                        adapter.NewList(results);

                    }
                }

                @Override
                public void onFailure(Call<NearbyResponse> call, Throwable t) {

                }
            });
        }
    }
}
