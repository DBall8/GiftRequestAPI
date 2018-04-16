package edu.wpi.cs3733.TeamD.TreeTableClasses;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.wpi.cs3733.TeamD.Entities.GiftRequest;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class GRRow extends RecursiveTreeObject<GRRow>{

    private GiftRequest gr;
    public StringProperty giftName;
    public StringProperty location;
    public StringProperty date;
    public StringProperty time;
    public StringProperty assignee;

    public GRRow(GiftRequest gr){
        this.gr = gr;
        giftName = new SimpleStringProperty(gr.getGift().getName());
        location = new SimpleStringProperty(gr.getNodeID());
        assignee = new SimpleStringProperty(gr.getAssignee());
        date = new SimpleStringProperty(gr.getDate().toString());
        time = new SimpleStringProperty(gr.getTime().toString());
    }

    public GiftRequest getGR(){
        return this.gr;
    }
}
