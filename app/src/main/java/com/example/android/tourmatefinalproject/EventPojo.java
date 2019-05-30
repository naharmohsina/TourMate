package com.example.android.tourmatefinalproject;

public class EventPojo {
    private String travelDestination;
    private String travelEstimatedBudget;
    private String travelStartingDate;
    private String travelEndingDate;
    private String id;


    public EventPojo(String travelDestination, String travelEstimatedBudget, String travelStartingDate, String travelEndingDate) {
        this.travelDestination = travelDestination;
        this.travelEstimatedBudget = travelEstimatedBudget;
        this.travelStartingDate = travelStartingDate;
        this.travelEndingDate = travelEndingDate;
    }

    public EventPojo(String travelDestination, String travelEstimatedBudget, String travelStartingDate, String travelEndingDate, String id) {
        this.travelDestination = travelDestination;
        this.travelEstimatedBudget = travelEstimatedBudget;
        this.travelStartingDate = travelStartingDate;
        this.travelEndingDate = travelEndingDate;
        this.id = id;
    }

    public EventPojo() {

    }

    public String getTravelDestination() {
        return travelDestination;
    }

    public String getTravelEstimatedBudget() {
        return travelEstimatedBudget;
    }

    public String getTravelStartingDate() {
        return travelStartingDate;
    }

    public String getTravelEndingDate() {
        return travelEndingDate;
    }

    public String getId() {
        return id;
    }
}

