package edu.wpi.cs3733.TeamD.TreeTableClasses;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class EmployeeRow extends RecursiveTreeObject<EmployeeRow>{

    public StringProperty name;

    public EmployeeRow(String name){
        this.name = new SimpleStringProperty(name);
    }

}
