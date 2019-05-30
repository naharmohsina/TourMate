package com.example.android.tourmatefinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class TourActivity extends AppCompatActivity {
    private Button addButton;
    private ListView listViewTour;
    ArrayList<EventPojo>events;
    String userId;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);
        addButton=(Button)findViewById(R.id.addeventButton);
        listViewTour=(ListView)findViewById(R.id.listViewTourEvent);
        userId= FirebaseAuth.getInstance().getUid();
        events=new ArrayList<EventPojo>();
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("Event");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TourActivity.this,CreateEvent.class));
            }
        });

      listViewTour.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              EventPojo artist=events.get(i);
              Intent intent=new Intent(TourActivity.this,DashboardEvent.class);
             String getId = artist.getId();
             String getBudget = artist.getTravelEstimatedBudget();
             intent.putExtra("eventId",getId);
             intent.putExtra("budget",getBudget);
            startActivity(intent);
          }
      });
    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                events.clear();
                for(DataSnapshot child: dataSnapshot.getChildren()) {
                    EventPojo eventClass = child.getValue(EventPojo.class);

                    events.add(eventClass);
                    //finalEventListAdapter.notifyDataSetChanged();

                }
                Collections.reverse(events);
                TourAdapter tourAdapter=new TourAdapter(TourActivity.this, events);
                listViewTour.setAdapter(tourAdapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private boolean updateEvent(String travelDestination, String travelEndingDate,String travelStartingDate,String travelEstimatedBudget,String id) {
//        //getting the specified artist reference
//        DatabaseReference dR = databaseReference;
//        //String travelDestination, String travelEstimatedBudget, String travelStartingDate, String travelEndingDate, String id) {
//        //
//
//        //updating artist
//        EventPojo eventPojo = new EventPojo(travelDestination, travelEstimatedBudget, travelStartingDate,travelEndingDate,id);
//        dR.setValue(eventPojo);
//        Toast.makeText(getApplicationContext(), "Event Updated", Toast.LENGTH_LONG).show();
//        return true;
//    }

}
