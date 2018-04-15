package edu.wpi.cs3733.TeamD.FXMLControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.Stage;

import java.io.IOException;

public class GiftServiceRequestController extends ScreenController{

    @FXML
    private JFXTreeTableView unassignedGifts;

    @FXML
    private JFXButton orderGiftButton;
    @FXML
    private JFXButton adminOptionsButton;

    @FXML
    private void buttonAction(ActionEvent e){
        if(e.getSource() == orderGiftButton){
            try{
                popupScreen("GiftSelectorScreen.fxml", (Stage)orderGiftButton.getScene().getWindow(), "Place an Order");
            } catch(IOException ioe){
                System.out.println("Unable to load Gift order screen.");
                ioe.printStackTrace();
            }

        }
        else if(e.getSource() == adminOptionsButton){
            switchScreen((Stage)adminOptionsButton.getScene().getWindow(), "AdminScreen.fxml");
        }
    }
}
