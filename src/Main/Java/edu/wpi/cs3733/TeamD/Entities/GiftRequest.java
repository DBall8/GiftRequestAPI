package edu.wpi.cs3733.TeamD.Entities;

import edu.wpi.cs3733.TeamD.Database;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

public class GiftRequest {
    String grID;
    Gift gift;
    String assignee;
    String status;
    String nodeID;
    Date date;
    Time time;

    // For creating brand new Gift Requests, because this generates a new timestamp
    public GiftRequest(Gift gift, String nodeID) {
        this.grID = "GR-" + System.currentTimeMillis();
        this.assignee = "";
        this.gift = gift;
        this.nodeID = nodeID;
        this.status = "Unresolved";
        this.date = new Date(Calendar.getInstance().getTime().getTime());
        this.time = new Time(Calendar.getInstance().getTime().getTime());

    }

    // For loading existing Gift Requests because this loads an existing timestamp
    public GiftRequest(String grID, Gift gift, String assignee, String status, String nodeID, Date date, Time time) {
        this.grID = grID;
        this.gift = gift;
        this.assignee = assignee;
        this.status = status;
        this.nodeID = nodeID;
        this.date = date;
        this.time = time;

    }

    public void assignDeliverer(String name){

        if(Database.getInstance().assignGR(grID, name)){
            this.assignee = name;
        }
    }

    public void resolve(){
        if(Database.getInstance().resolveGR(grID)){
            this.status = "Resolved";
        }
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

    public String getStatus() {
        return status;
    }

    public String getNodeID() {
        return nodeID;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    // FOR TESTING REPORTS ONLY
    public void setDate(Date d){
        this.date = d;
    }
}
