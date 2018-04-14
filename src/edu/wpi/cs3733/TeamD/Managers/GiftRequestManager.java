package edu.wpi.cs3733.TeamD.Managers;

import edu.wpi.cs3733.TeamD.Database;
import edu.wpi.cs3733.TeamD.Entities.GR;

import java.util.HashMap;

public class GiftRequestManager {

    private HashMap<String, GR> giftRequests;
    private GiftDirectory giftDirectory;
    private EmployeeList employeeList;

    public GiftRequestManager(){
        giftDirectory = new GiftDirectory();
        employeeList = new EmployeeList();
        giftRequests = Database.getInstance().loadGRs(giftDirectory);
    }

    public GiftDirectory getGiftDirectory() {
        return giftDirectory;
    }

    public EmployeeList getEmployeeList() {
        return employeeList;
    }
}
