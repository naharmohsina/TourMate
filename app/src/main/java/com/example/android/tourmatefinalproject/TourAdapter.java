package com.example.android.tourmatefinalproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TourAdapter extends ArrayAdapter<EventPojo> {


    public TourAdapter(Activity context, ArrayList<EventPojo> eventPojoList) {
        super(context, 0, eventPojoList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listViewItem = convertView;
        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.event_list_item, parent, false);

        }
        EventPojo eventPojo = getItem(position);




        //EventPojo eventPojo=eventPojoList.get(position);
        //Collections.reverse();

        TextView textViewPlace=(TextView)listViewItem.findViewById(R.id.place);
        TextView textViewFdate=(TextView)listViewItem.findViewById(R.id.fdate);
        TextView textViewTdate=(TextView)listViewItem.findViewById(R.id.tdate);
        TextView textViewBudget=(TextView)listViewItem.findViewById(R.id.taka);
        textViewPlace.setText(eventPojo.getTravelDestination());
        textViewBudget.setText(eventPojo.getTravelEstimatedBudget());
        textViewTdate.setText(eventPojo.getTravelEndingDate());
        textViewFdate.setText(eventPojo.getTravelStartingDate());
        return listViewItem;
    }
}
