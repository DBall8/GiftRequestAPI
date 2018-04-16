package edu.wpi.cs3733.TeamD.Managers;

import edu.wpi.cs3733.TeamD.Database;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import edu.wpi.cs3733.TeamD.Entities.GiftRequest;
import edu.wpi.cs3733.TeamD.ObserverPattern.ObservableSubject;

import java.util.*;
import java.sql.Date;


public class GiftRequestManager extends ObservableSubject{

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

    public GiftRequest addGiftRequest(Gift g, String nodeID){
        GiftRequest gr = new GiftRequest(g, nodeID);
        if(Database.insertGR(gr)){
            giftRequests.put(gr.getGrID(), gr);
            notifyObservers();
            return gr;
        }
        return null;
    }

    public void assignGR(String grID, String employee){
        giftRequests.get(grID).assignDeliverer(employee);
        notifyObservers();
    }

    public void resolveGR(String grID){
        giftRequests.get(grID).resolve();
        notifyObservers();
    }

    public List<GiftRequest> getAllGiftRequests(){
        ArrayList<GiftRequest> grList = new ArrayList<>();
        Iterator it = giftRequests.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            GiftRequest gr = (GiftRequest) pair.getValue();
            grList.add(gr);
        }

        return grList;
    }

    public List<GiftRequest> getUnassignedGiftRequests(){
        ArrayList<GiftRequest> grList = new ArrayList<>();
        Iterator it = giftRequests.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            GiftRequest gr = (GiftRequest) pair.getValue();
            if(!gr.getStatus().equals("Resolved") && gr.getAssignee().equals("")) {
                grList.add(gr);
            }
        }

        return grList;
    }

    public List<GiftRequest> getPendingGiftRequests(){
        ArrayList<GiftRequest> grList = new ArrayList<>();
        Iterator it = giftRequests.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry pair = (Map.Entry)it.next();
            GiftRequest gr = (GiftRequest) pair.getValue();
            if(!gr.getStatus().equals("Resolved") && !gr.getAssignee().equals("")) {
                grList.add(gr);
            }
        }

        return grList;
    }


    public void addGRWDATE(Gift g, String nodeID, Date date){
        GiftRequest gr = new GiftRequest(g, nodeID);
        gr.setDate(date);
        if(Database.insertGR(gr)){
            giftRequests.put(gr.getGrID(), gr);
            notifyObservers();
        }
    }
}
