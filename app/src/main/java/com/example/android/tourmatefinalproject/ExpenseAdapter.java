package com.example.android.tourmatefinalproject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpenseAdapter extends ArrayAdapter<ExpensePojo> {

    public ExpenseAdapter(Activity context, ArrayList<ExpensePojo> expensePojoList) {
        super(context, 0, expensePojoList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View listViewItem = convertView;
        if (listViewItem == null) {
            listViewItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.expense_list_item, parent, false);

        }
        ExpensePojo expensePojo = getItem(position);




        //EventPojo eventPojo=eventPojoList.get(position);
        //Collections.reverse();

        TextView textVieweDetails=(TextView)listViewItem.findViewById(R.id.eDetails);
        TextView textVieweAmount=(TextView)listViewItem.findViewById(R.id.eAmount);
        textVieweDetails.setText(expensePojo.getExpenseDetails());
        textVieweAmount.setText(expensePojo.getExpenseAmount());

        return listViewItem;
    }
}
