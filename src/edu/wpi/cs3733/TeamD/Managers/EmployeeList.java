package edu.wpi.cs3733.TeamD.Managers;

import edu.wpi.cs3733.TeamD.Database;

import java.util.List;

public class EmployeeList {

    private List<String> employees;

    public EmployeeList(){
        employees = Database.getInstance().loadEmployees();
    }

    public String getEmployee(int i){
        return employees.get(i);
    }

}
