package edu.wpi.cs3733.TeamD.FXMLControllers;

import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import edu.wpi.cs3733.TeamD.ServiceException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TestOpenerController {
    @FXML
    Button onlyButton;

    @FXML
    public void buttonAction(ActionEvent e){
        GiftServiceRequest gr = new GiftServiceRequest();
        try{
            gr.run(0,0,1900,1000,"default.css",null,null);
        }
        catch(ServiceException se){
            System.out.println("Could not work");
            se.printStackTrace();
        }
    }
}
