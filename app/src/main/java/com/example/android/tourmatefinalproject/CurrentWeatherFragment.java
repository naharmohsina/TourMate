package com.example.android.tourmatefinalproject;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.tourmatefinalproject.weather.WeatherResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class CurrentWeatherFragment extends Fragment {

    private  double latt,lonn;
    double la=23.750883;
    double lo=90.393404;
    private TextView temp,humidity,place;
    private Api_service service;
    private String unit = "metric";




    @SuppressLint("ValidFragment")
    public CurrentWeatherFragment(double lat, double lon) {
        // Required empty public constructor
        latt=lat;
        lonn=lon;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_current_weather, container, false);
        temp=view.findViewById(R.id.temp);
        humidity=view.findViewById(R.id.hum);
        place=view.findViewById(R.id.place);

        String url = String.format("weather?lat=%f&lon=%f&units=%s&appid=%s",latt,lonn,unit,getResources().getString(R.string.weather_key));

        service = Api_response.getUser().create(Api_service.class);
        Call<WeatherResponse> weatherResponseCall = service.getAllUser(url);
        weatherResponseCall.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call <WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.code() == 200) {
                    WeatherResponse weatherResponse = response.body();
                    temp.setText("Temp "+weatherResponse.getMain().getTempMin().toString()+ " C");
                    humidity.setText("Humidity "+weatherResponse.getMain().getHumidity().toString()+" %");
                    place.setText(weatherResponse.getName());
                }
            }

            @Override
            public void onFailure(Call <WeatherResponse> call, Throwable t) {

            }
        });

        return  view;
    }


}
