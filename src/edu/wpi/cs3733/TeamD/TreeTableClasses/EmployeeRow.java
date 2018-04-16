package edu.wpi.cs3733.TeamD.TreeTableClasses;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.wpi.cs3733.TeamD.Entities.Employee;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class EmployeeRow extends RecursiveTreeObject<EmployeeRow>{

    public StringProperty employeeID;
    public StringProperty name;

    public EmployeeRow(Employee e){
        this.employeeID = new SimpleStringProperty(e.getEmployeeID());
        this.name = new SimpleStringProperty(e.getName());
    }

}
