package com.example.android.tourmatefinalproject;

public class ExpensePojo {
    private String expenseDetails;
    private String expenseAmount;
    private String expenseId;

    public ExpensePojo(String expenseDetails, String expenseAmount) {
        this.expenseDetails = expenseDetails;
        this.expenseAmount = expenseAmount;
    }

    public ExpensePojo() {
    }


    public ExpensePojo(String expenseDetails, String expenseAmount, String expenseId) {
        this.expenseDetails = expenseDetails;
        this.expenseAmount = expenseAmount;
        this.expenseId = expenseId;
    }

    public String getExpenseDetails() {
        return expenseDetails;
    }

    public String getExpenseAmount() {
        return expenseAmount;
    }
}
