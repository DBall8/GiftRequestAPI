package edu.wpi.cs3733.TeamD.TreeTableClasses;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.text.DecimalFormat;

public class GiftRow extends RecursiveTreeObject<GiftRow> {

    private Gift g;
    public StringProperty name;
    public StringProperty cost;

    public GiftRow(Gift g){
        this.name = new SimpleStringProperty(g.getName());
        this.cost = new SimpleStringProperty(costFtoS(g.getCost()));
        this.g = g;
    }

    public Gift getGift(){
        return this.g;
    }

    private String costFtoS(float cost){
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        String str = "$" + df.format(cost);
        return str;
    }

}
