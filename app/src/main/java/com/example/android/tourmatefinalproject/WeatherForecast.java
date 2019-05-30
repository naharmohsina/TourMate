package com.example.android.tourmatefinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class WeatherForecast extends AppCompatActivity {
    private double latn,lonn;
    String address;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        tabLayout = findViewById(R.id.tabLayoutId);
        viewPager = findViewById(R.id.viewPagerId);


        adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Intent intent = getIntent();

        latn = intent.getDoubleExtra("latitude",0);
        lonn = intent.getDoubleExtra("longitude",0);
        address = intent.getStringExtra("Address");

        CurrentWeatherFragment currentWeatherFragment = new CurrentWeatherFragment(latn,lonn);
        ForecastWeatherFragment forecastWeatherFragment = new ForecastWeatherFragment(latn,lonn);
        adapter.addFragment(currentWeatherFragment,"Current Weather");
        adapter.addFragment(forecastWeatherFragment,"Forecast Weather");

        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);




    }
}
