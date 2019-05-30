package com.example.android.tourmatefinalproject;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class CreateEvent extends AppCompatActivity {
   private EditText travelDestinatio,estimatedBudge,fromDate,toDate;
    private Button createButton;
    FirebaseAuth mAuth;
    private String userId;
    private DatePickerDialog.OnDateSetListener onDateSetListener,onDateSetListener1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        travelDestinatio=(EditText)findViewById(R.id.travelDestination);
        estimatedBudge=(EditText)findViewById(R.id.estimatedBudet);
        fromDate=(EditText)findViewById(R.id.fromDate);
        toDate=(EditText)findViewById(R.id.todate);
        createButton=(Button)findViewById(R.id.createEventBT);
        mAuth=FirebaseAuth.getInstance();
        userId=mAuth.getCurrentUser().getUid();

        fromDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateEvent.this,
                        R.style.AddedTheme,
                        onDateSetListener, year, month, dayOfMonth);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                datePickerDialog.show();
            }
        });

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                Log.d("ShowDate", "onDateSet: dd/mm/yy: "+ dayOfMonth + "." + month + "." + year);
                String date = dayOfMonth + "/" + month + "/" + year;
                fromDate.setText(date);
            }
        };


        toDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(CreateEvent.this,
                        R.style.AddedTheme,
                        onDateSetListener1, year, month, dayOfMonth);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.LTGRAY));
                datePickerDialog.show();
            }
        });

        onDateSetListener1 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                Log.d("ShowDate", "onDateSet: dd/mm/yy: "+ dayOfMonth + "." + month + "." + year);
                String date = dayOfMonth + "/" + month + "/" + year;
                toDate.setText(date);
            }
        };



        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String travelDestination = travelDestinatio.getText().toString();
                String travelEstimatedBudget = estimatedBudge.getText().toString();
                String travelStartingDate = toDate.getText().toString();
                String travelEndingDate = fromDate.getText().toString();

                if (TextUtils.isEmpty(travelDestination)) {
                    travelDestinatio.setError("Enter your travel destination");
                    travelDestinatio.requestFocus();
                    return;

                } else if (TextUtils.isEmpty(travelEstimatedBudget)) {
                    estimatedBudge.setError("Enter your estimated budget");
                    estimatedBudge.requestFocus();
                    return;

                } else if (TextUtils.isEmpty(travelStartingDate)) {
                    toDate.setError("Enter your travel starting date");
                    toDate.requestFocus();
                    return;

                } else if (TextUtils.isEmpty(travelEndingDate)) {
                    fromDate.setError("Enter your travel ending date");
                    fromDate.requestFocus();
                    return;

                }
                else
                {
                    final ProgressDialog progressDialog = new ProgressDialog(CreateEvent.this);
                    progressDialog.setMessage("Please waiting...");
                    progressDialog.show();



                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("Event");
                    String eventId=databaseReference.push().getKey();
                    EventPojo eventPojo = new EventPojo(travelDestination, travelEstimatedBudget, travelStartingDate, travelEndingDate,eventId);
                    databaseReference.child(eventId).setValue(eventPojo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(CreateEvent.this, "Thank you", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(CreateEvent.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    //travelDestinatio,estimatedBudge,fromDate,toDate;
                    travelDestinatio.setText("");
                    estimatedBudge.setText("");
                    fromDate.setText("");
                    toDate.setText("");



                    startActivity(new Intent(CreateEvent.this,TourActivity.class));


                }
            }
        });

    }
}
