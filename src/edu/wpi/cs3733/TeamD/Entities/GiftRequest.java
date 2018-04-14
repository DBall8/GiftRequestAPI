package edu.wpi.cs3733.TeamD.Entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class GiftRequest {
    String grID;
    Gift gift;
    String assignee;
    Date date;
    Time time;

    // For creating brand new Gift Requests, because this generates a new timestamp
    public GiftRequest(String grID, Gift gift, String assignee) {
        this.grID = grID;
        this.gift = gift;
        this.assignee = assignee;
        this.date = new Date(Calendar.getInstance().getTime().getTime());
        this.time = new Time(Calendar.getInstance().getTime().getTime());
    }

    // For loading existing Gift Requests because this loads an existing timestamp
    public GiftRequest(String grID, Gift gift, String assignee, Date date, Time time) {
        this.grID = grID;
        this.gift = gift;
        this.assignee = assignee;
        this.date = date;
        this.time = time;
    }

    public String getGrID() {
        return grID;
    }

    public Gift getGift() {
        return gift;
    }

    public String getAssignee() {
        return assignee;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }
}
