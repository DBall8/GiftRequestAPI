package edu.wpi.cs3733.TeamD.Managers;

import edu.wpi.cs3733.TeamD.Database;
import edu.wpi.cs3733.TeamD.Entities.Employee;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import edu.wpi.cs3733.TeamD.ObserverPattern.ObservableSubject;

import javax.xml.crypto.Data;
import java.util.*;

public class EmployeeList extends ObservableSubject{

    private HashMap<String, Employee> employees;

    public EmployeeList(){
        employees = Database.getInstance().loadEmployees();
    }

    public Employee addEmployee(String employeeID, String name){
        Employee e = new Employee(employeeID, name);
        if(Database.getInstance().insertEmployee(e)){
            employees.put(employeeID, e);

            notifyObservers();
            return e;
        }
        return null;
    }

    public void deleteEmployee(String employeeID){
        if(Database.removeEmployee(employeeID)){
            employees.remove(employeeID);
            notifyObservers();
        }
    }

    public List<Employee> getEmployees(){

        ArrayList<Employee> employeeList = new ArrayList<>();
        Iterator it = employees.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            employeeList.add((Employee)pair.getValue());
        }

        return employeeList;
    }

    public boolean containsEmployee(String name){

        for(Employee e: getEmployees()) {
            if (e.getName().equals(name)) {
                return true;
            }
        }
        return false;

    }

}
