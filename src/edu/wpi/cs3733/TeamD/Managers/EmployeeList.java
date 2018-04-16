package edu.wpi.cs3733.TeamD.Managers;

import edu.wpi.cs3733.TeamD.Database;
import edu.wpi.cs3733.TeamD.Entities.Employee;

import javax.xml.crypto.Data;
import java.util.List;

public class EmployeeList {

    private List<Employee> employees;

    public EmployeeList(){
        employees = Database.getInstance().loadEmployees();
    }

    public Employee addEmployee(String employeeID, String name){
        Employee e = new Employee(employeeID, name);
        if(Database.getInstance().insertEmployee(e)){
            employees.add(e);
            return e;
        }
        return null;
    }

    public List<Employee> getEmployees(){
        return employees;
    }

    public boolean containsEmployee(String name){

        for(Employee e: employees) {
            if (e.getName().equals(name)) {
                return true;
            }
        }
        return false;

    }

}
