package edu.wpi.cs3733.TeamD.TreeTableClasses;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.wpi.cs3733.TeamD.Entities.GiftRequest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class GRRow extends RecursiveTreeObject<GRRow>{

    private static DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    private GiftRequest gr;
    public StringProperty giftName;
    public StringProperty recipient;
    public StringProperty location;
    public StringProperty date;
    public StringProperty time;
    public StringProperty assignee;

    public GRRow(GiftRequest gr){
        this.gr = gr;
        giftName = new SimpleStringProperty(gr.getGift().getName());
        recipient = new SimpleStringProperty(gr.getRecipient());
        location = new SimpleStringProperty(gr.getNodeID());
        assignee = new SimpleStringProperty(gr.getAssignee());
        date = new SimpleStringProperty(df.format(gr.getDate()));
        time = new SimpleStringProperty(gr.getTime().toString());
    }

    public GiftRequest getGR(){
        return this.gr;
    }
}
