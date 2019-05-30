package com.example.android.tourmatefinalproject;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.tourmatefinalproject.Forecast.List;

import java.util.ArrayList;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    ArrayList<List> lists;
   // ArrayList<Weather>weathers;

    public ForecastAdapter(ArrayList<List> lists) {
        this.lists = lists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //(position==0 || position==8 || position==16 || position==24 || position==39) {
            holder.tempTV.setText(lists.get(position).getMain().getTemp().toString() + " C");
            holder.humTV.setText(lists.get(position).getMain().getHumidity().toString() + "%");
            holder.windTV.setText(lists.get(position).getWind().getSpeed().toString());
            //String a=lists.get(position).getWeather().get(position).getDescription();
            holder.descTV.setText(lists.get(position).getClouds().getAll() + "%");
        //}

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tempTV,humTV,windTV,descTV;
        public ViewHolder(View itemView) {
            super(itemView);
            tempTV=itemView.findViewById(R.id.temp);
            humTV = itemView.findViewById(R.id.hum);
            windTV = itemView.findViewById(R.id.wind);
            descTV=itemView.findViewById(R.id.description);
        }
    }

//    public void NewList(List<List> lists){
//        this.lists = lists;
//        notifyDataSetChanged();
//    }
}
