package edu.wpi.cs3733.TeamD.FXMLControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableView;
import edu.wpi.cs3733.TeamD.Entities.Employee;
import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import edu.wpi.cs3733.TeamD.ObserverPattern.Observer;
import edu.wpi.cs3733.TeamD.TreeTableClasses.GRRow;
import edu.wpi.cs3733.TeamD.TreeTableClasses.GRTable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class GiftServiceRequestController extends ScreenController implements Initializable, Observer {

    @FXML
    private JFXButton orderGiftButton;
    @FXML
    private JFXButton adminOptionsButton;
    @FXML
    private JFXButton assignButton;
    @FXML
    private JFXButton resolveButton;
    @FXML
    private ChoiceBox<String> assignChoiceBox;

    @FXML
    private JFXButton deleteUnassingedButton;
    @FXML
    private JFXButton deletePendingButton;

    @FXML
    private JFXTreeTableView<GRRow> unassignedTreeTable;
    @FXML
    private JFXTreeTableView<GRRow> pendingTreeTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        adminOptionsButton.setManaged(false);
        if(GiftServiceRequest.getAdmin()){
            adminOptionsButton.setManaged(true);
            adminOptionsButton.setVisible(true);
            adminOptionsButton.setDisable(false);
        }

        new GRTable(unassignedTreeTable, false);
        new GRTable(pendingTreeTable, true);

        GiftServiceRequest.getGRM().getEmployeeList().subscribe(this);
        update();
    }

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
        else if(e.getSource() == assignButton){
            if(assignChoiceBox.getSelectionModel().isEmpty()){
                System.out.println("Please select an employee to assign.");
                return;
            }

            if(unassignedTreeTable.getSelectionModel().isEmpty()){
                System.out.println("Please select a delivery to assign");
                return;
            }

            TreeItem<GRRow> selectedItem = unassignedTreeTable.getSelectionModel().getSelectedItem();
            String grID = selectedItem.getValue().getGR().getGrID();
            String assignee = assignChoiceBox.getSelectionModel().getSelectedItem().toString();

            GiftServiceRequest.getGRM().assignGR(grID, assignee);
        }
        else if(e.getSource() == resolveButton){
            if(pendingTreeTable.getSelectionModel().isEmpty()){
                System.out.println("Please select a delivery to assign");
                return;
            }

            TreeItem<GRRow> selectedItem = pendingTreeTable.getSelectionModel().getSelectedItem();
            String grID = selectedItem.getValue().getGR().getGrID();
            GiftServiceRequest.getGRM().resolveGR(grID);
        }
        else if(e.getSource() == deleteUnassingedButton){
            if(unassignedTreeTable.getSelectionModel().isEmpty()){
                System.out.println("Please select a delivery to delete");
                return;
            }

            TreeItem<GRRow> selectedItem = unassignedTreeTable.getSelectionModel().getSelectedItem();
            String grID = selectedItem.getValue().getGR().getGrID();
            GiftServiceRequest.getGRM().deleteGR(grID);
        }
        else if(e.getSource() == deletePendingButton){
            if(pendingTreeTable.getSelectionModel().isEmpty()){
                System.out.println("Please select a delivery to delete");
                return;
            }

            TreeItem<GRRow> selectedItem = pendingTreeTable.getSelectionModel().getSelectedItem();
            String grID = selectedItem.getValue().getGR().getGrID();
            GiftServiceRequest.getGRM().deleteGR(grID);
        }
    }


    @Override
    public void update() {
        List<String> employeeNames = new ArrayList<>();
        for(Employee e: GiftServiceRequest.getGRM().getEmployeeList().getEmployees()){
            employeeNames.add(e.getName());
        }

        assignChoiceBox.setItems(FXCollections.observableArrayList(employeeNames));
    }
}
