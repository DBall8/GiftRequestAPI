package edu.wpi.cs3733.TeamD.Managers;

import edu.wpi.cs3733.TeamD.Database;

import javax.xml.crypto.Data;
import java.util.List;

public class EmployeeList {

    private List<String> employees;

    public EmployeeList(){
        employees = Database.getInstance().loadEmployees();
    }

    public String getEmployee(int i){
        return employees.get(i);
    }

    public void addEmployee(String name){
        if(Database.getInstance().insertEmployee(name)){
            employees.add(name);
        }
    }

    public List<String> getEmployees(){
        return employees;
    }

    public boolean containsEmployee(String name){
        return employees.contains(name);
    }

}
