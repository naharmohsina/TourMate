package com.example.android.tourmatefinalproject;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.tourmatefinalproject.Forecast.ForecastResponse;
import com.example.android.tourmatefinalproject.Forecast.List;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class ForecastWeatherFragment extends Fragment {

    double lattt,lonnn;
    private ApiS service;
    private Double lat=23.750841;
    private Double lon=90.393293;
    private TextView placeText;
    //private int radious=1000;
    // private String place_type;
    // private RecyclerView recyclerView;
    private ForecastAdapter adapter;
    RecyclerView recyclerView;
    private String unit = "metric";
    ArrayList<List> lists;
    // ArrayList<Weather>weathers;



    @SuppressLint("ValidFragment")
    public ForecastWeatherFragment(double lat, double lon) {
        // Required empty public constructor
        this.lattt=lat;
        this.lonnn=lon;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_forecast_weather, container, false);
        recyclerView = view.findViewById(R.id.rid);
        placeText=view.findViewById(R.id.p);

        //String url = String.format("place/nearbysearch/json?location=%f,%f&radius=%d&type=%s&key=%s",lat,lon,radious,place_type,getResources().getString(R.string.place_key));
        String url = String.format("forecast?lat=%f&lon=%f&units=%s&appid=%s",lattt,lonnn,unit,getResources().getString(R.string.forecast_key));
        service = ApiR.getForecast().create(ApiS.class);
        Call<ForecastResponse> forecastResponseCall = service.getAllForecast(url);
        forecastResponseCall.enqueue(new Callback<ForecastResponse>() {
            @Override
            public void onResponse(Call<ForecastResponse> call, Response<ForecastResponse> response) {
                if (response.code()==200){
                    ForecastResponse forecastResponse = response.body();
                    placeText.setText(forecastResponse.getCity().getName());
                    lists = (ArrayList<List>) forecastResponse.getList();
                    //weathers=(ArrayList<List>) forecastResponse.;
                    adapter = new ForecastAdapter(lists);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView.setAdapter(adapter);
                    //Toast.makeText(MainActivity.this, String.valueOf(lists.size()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ForecastResponse> call, Throwable t) {

            }
        });



        return view;
    }

}
