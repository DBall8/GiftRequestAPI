package edu.wpi.cs3733.TeamD.Entities;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class GR {
    String grID;
    Gift gift;
    String assignee;
    Date date;
    Time time;

    public GR(String grID, Gift gift, String assignee) {
        this.grID = grID;
        this.gift = gift;
        this.assignee = assignee;
        this.date = new Date(Calendar.getInstance().getTime().getTime());
        this.time = new Time(Calendar.getInstance().getTime().getTime());
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
