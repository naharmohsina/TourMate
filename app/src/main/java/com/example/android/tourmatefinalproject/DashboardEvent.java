package com.example.android.tourmatefinalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class DashboardEvent extends AppCompatActivity  {
    String eventIdT;
    String estimatedBudget;

    int amount,amountCheck;
    Boolean isFirst=false;

    EditText expamount,exapD;
   //Button addExp;
   FirebaseAuth mAuth;
    private String userId;


    int estimatedBudgetInteger;
    Button budget;
    private ListView listViewExpense;
    ArrayList<ExpensePojo> expenses;
   // private BottomSheetBehavior sheetBehavior;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_event);
        budget=(Button)findViewById(R.id.budget);
        listViewExpense=(ListView)findViewById(R.id.listviewExpense);
        userId= FirebaseAuth.getInstance().getUid();
        expenses=new ArrayList<ExpensePojo>();
        Intent intent=getIntent();
        eventIdT = intent.getStringExtra("eventId");
        estimatedBudget = intent.getStringExtra("budget");


       expamount=(EditText)findViewById(R.id.expenseAmount);
       exapD=(EditText)findViewById(R.id.expenseDetails);
       //addExp=(Button)findViewById(R.id.addExpenseBtn);




        estimatedBudgetInteger = Integer.parseInt(estimatedBudget);
         //DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("Event").child(eventIdT).child("Expense");

        budget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent=new Intent(DashboardEvent.this,AddExpense.class);
//                intent.putExtra("eventIdd",eventIdT);
//                intent.putExtra("budgett",estimatedBudget);
//                startActivity(intent);
                saveExpense();
            }
        });

//        setUpReferences();
//        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
//            @Override
//            public void onStateChanged(@NonNull View bottomSheet, int newState) {
//                switch (newState) {
//                    case BottomSheetBehavior.STATE_HIDDEN:
//                        break;
//
//                    case BottomSheetBehavior.STATE_DRAGGING:
//                        sheetBehavior.setHideable(false);
//                        break;
//                    case BottomSheetBehavior.STATE_SETTLING:
//                        sheetBehavior.setHideable(false);
//                        break;
//                }
//            }
//
//            @Override
//            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
//
//            }
//        });

//        budgetList.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(DashboardEvent.this,AddExpense.class);
//                intent.putExtra("eventIdd",eventIdT);
//            }
//        });
        databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("Event").child(eventIdT).child("Expense");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                expenses.clear();
//                for(DataSnapshot child: dataSnapshot.getChildren()) {
//                    ExpensePojo expenseClass = child.getValue(ExpensePojo.class);
//
//                    expenses.add(expenseClass);
//                    //finalEventListAdapter.notifyDataSetChanged();
//
//                }
//                Collections.reverse(expenses);
//                ExpenseAdapter expenseAdapter=new ExpenseAdapter(DashboardEvent.this, expenses);
//                listViewExpense.setAdapter(expenseAdapter);
//
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        addExp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String expenseDet=exapD.getText().toString();
//                String expenseAm=expamount.getText().toString();
//                if (TextUtils.isEmpty(expenseDet)) {
//                    exapD.setError("Enter your travel destination");
//                    exapD.requestFocus();
//                    return;
//
//                } else if (TextUtils.isEmpty(expenseAm)) {
//                    expamount.setError("Enter your estimated budget");
//                    expamount.requestFocus();
//                    return;
//
//                }
//                else {
//                    final ProgressDialog progressDialog = new ProgressDialog(DashboardEvent.this);
//                    progressDialog.setMessage("Please waiting...");
//                    progressDialog.show();
//                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("Event").child(eventIdT).child("Expense");
//                    String expenId=databaseReference.push().getKey();
//                    ExpensePojo eventPojo = new ExpensePojo(expenseDet, expenseAm,expenId);
//                    databaseReference.child(expenId).setValue(eventPojo).addOnCompleteListener(new OnCompleteListener<Void>() {
//                        @Override
//                        public void onComplete(@NonNull Task<Void> task) {
//
//                            if (task.isSuccessful()) {
//                                progressDialog.dismiss();
//                                Toast.makeText(DashboardEvent.this, "Thank you", Toast.LENGTH_SHORT).show();
//
//                            } else {
//                                Toast.makeText(DashboardEvent.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//
//                        }
//                    });
//                    exapD.setText("");
//                    expamount.setText("");
//
//
//
//
//                    Intent intent=new Intent(DashboardEvent.this,DashboardEvent.class);
//                    //intent.putExtra("eventIddd",eventIdT);
//                    //intent.putExtra("budgettt",estimatedBudget);
//                    startActivity(intent);
//                    // startActivity(new Intent(AddExpense.this,ShowExpense.class));
//
//
//
//                }
//            }
//        });


    }

    private void saveExpense() {
        String expenseDet=exapD.getText().toString();
        String expenseAm=expamount.getText().toString();
        if (TextUtils.isEmpty(expenseDet)) {
            exapD.setError("Enter your expense details destination");
            exapD.requestFocus();
            return;

        } else if (TextUtils.isEmpty(expenseAm)) {
            expamount.setError("Enter your expense amount budget");
            expamount.requestFocus();
            return;

        }
        else
        {
            if (amountCheck>0) {

                int totalExpenseWithCurrent = amountCheck + Integer.valueOf(expenseAm);
                if (totalExpenseWithCurrent<=estimatedBudgetInteger){


                    final ProgressDialog progressDialog = new ProgressDialog(DashboardEvent.this);
                    progressDialog.setMessage("Please waiting...");
                    progressDialog.show();
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("Event").child(eventIdT).child("Expense");
                    String expenId=databaseReference.push().getKey();
                    ExpensePojo expensePojo = new ExpensePojo(expenseDet, expenseAm,expenId);
                    databaseReference.child(expenId).setValue(expensePojo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(DashboardEvent.this, "Thank you", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(DashboardEvent.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    exapD.setText("");
                    expamount.setText("");

                }
                else {

                    Toast.makeText(DashboardEvent.this, "Budget Exceded!!", Toast.LENGTH_SHORT).show();
                }

            }
            else if(isFirst)
            {
                int totalExpenseWithCurrent = amountCheck + Integer.valueOf(expenseAm);
                if (totalExpenseWithCurrent<=estimatedBudgetInteger){


                    final ProgressDialog progressDialog = new ProgressDialog(DashboardEvent.this);
                    progressDialog.setMessage("Please waiting...");
                    progressDialog.show();
                    DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference().child("Users").child(userId).child("Event").child(eventIdT).child("Expense");
                    String expenId=databaseReference.push().getKey();
                    ExpensePojo expensePojo = new ExpensePojo(expenseDet, expenseAm,expenId);
                    databaseReference.child(expenId).setValue(expensePojo).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if (task.isSuccessful()) {
                                progressDialog.dismiss();
                                Toast.makeText(DashboardEvent.this, "Thank you", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(DashboardEvent.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                    exapD.setText("");
                    expamount.setText("");

                }
                else {

                    Toast.makeText(DashboardEvent.this, "Budget Exceded!!", Toast.LENGTH_SHORT).show();
                }

            }
            else {

                Toast.makeText(DashboardEvent.this, "Slow network connection! Try again", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        amount =0;
        amountCheck = 0;
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                expenses.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        ExpensePojo expenseClass = child.getValue(ExpensePojo.class);
                        amount = amount+ Integer.parseInt(expenseClass.getExpenseAmount());
                        expenses.add(expenseClass);
                        //finalEventListAdapter.notifyDataSetChanged();

                    }
                    amountCheck = amount;
                }
                else {
                    isFirst = true;
                    //Toast.makeText(getActivity().this, "first", Toast.LENGTH_SHORT).show();
                    Toast.makeText(DashboardEvent.this, "first entry", Toast.LENGTH_SHORT).show();
                }

                Collections.reverse(expenses);
                ExpenseAdapter expenseAdapter=new ExpenseAdapter(DashboardEvent.this, expenses);
                listViewExpense.setAdapter(expenseAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private void setUpReferences() {
//        LinearLayout layoutBottomSheet = findViewById(R.id.expense_bottom_sheet);
//        //btnBottomSheet = findViewById(R.id.btn_bottom_sheet);
//        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
//    }
//
//



}
