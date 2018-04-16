package edu.wpi.cs3733.TeamD.Managers;

import edu.wpi.cs3733.TeamD.Database;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import edu.wpi.cs3733.TeamD.Entities.GiftRequest;

import java.util.HashMap;

public class GiftRequestManager {

    private HashMap<String, GiftRequest> giftRequests;
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

    public void addGiftRequest(Gift g, String nodeID){
        GiftRequest gr = new GiftRequest(g, nodeID);
        if(Database.insertGR(gr)){
            giftRequests.put(gr.getGrID(), gr);
        }
    }

}
