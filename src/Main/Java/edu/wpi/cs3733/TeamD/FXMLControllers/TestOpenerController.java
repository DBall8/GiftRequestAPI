package edu.wpi.cs3733.TeamD.FXMLControllers;

import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import edu.wpi.cs3733.TeamD.ServiceException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.List;

public class TestOpenerController {
    @FXML
    Button onlyButton;

    @FXML
    public void buttonAction(ActionEvent e){
        GiftServiceRequest gr = new GiftServiceRequest();
        try{
            List<String> locations = new ArrayList<String>();
            locations.add("Bathroom 1");locations.add("Endoscopy");locations.add("Garden Cafe");locations.add("Elevator 12");
            gr.importLocations(locations);
            //gr.disableAdmin();
            gr.run(0,0,1900,1000,"css/default.css",null,null);
        }
        catch(ServiceException se){
            System.out.println("Could not work");
            se.printStackTrace();
        }
    }
}
