package edu.wpi.cs3733.TeamD.FXMLControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.wpi.cs3733.TeamD.Entities.Employee;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import edu.wpi.cs3733.TeamD.Managers.GiftRequestManager;
import edu.wpi.cs3733.TeamD.ObserverPattern.Observer;
import edu.wpi.cs3733.TeamD.TreeTableClasses.EmployeeRow;
import edu.wpi.cs3733.TeamD.TreeTableClasses.EmployeeTable;
import edu.wpi.cs3733.TeamD.TreeTableClasses.GiftRow;
import edu.wpi.cs3733.TeamD.TreeTableClasses.GiftTable;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminScreenController extends ScreenController implements Initializable, Observer{

    @FXML
    private JFXButton backButton;
    @FXML
    private JFXTreeTableView<EmployeeRow> personnelTreeTable;
    @FXML
    private JFXTreeTableView<GiftRow> giftTreeTable;

    @FXML
    private JFXButton addGiftButton;
    @FXML
    private JFXButton deleteGiftButton;
    @FXML
    private TextField giftNameField;
    @FXML
    private TextField giftCostField;

    GiftTable giftTable;
    EmployeeTable employeeTable;
    ObservableList<EmployeeRow> employees;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        giftTable = new GiftTable(giftTreeTable);

        employeeTable = new EmployeeTable(personnelTreeTable);
    }

    @FXML
    private void buttonAction(ActionEvent e){
        if(e.getSource() == backButton){
            switchScreen((Stage)backButton.getScene().getWindow(), "GiftRequestScreen.fxml");
        }
        else if(e.getSource() == addGiftButton){
            String giftName = giftNameField.getText();
            String giftCost = giftCostField.getText();

            if(giftName.equals("") || giftCost.equals("")){
                System.out.println("Please provide a name and a cost for the gift.");
                return;
            }

            float cost;
            if((cost = moneyStringToFloat(giftCost)) < 0){
                System.out.println("Invalid money amount.");
                return;
            }

            GiftRequestManager GRM = GiftServiceRequest.getGRM();
            Gift g = GRM.getGiftDirectory().addGift(giftName, cost, false);

            giftNameField.setText("");
            giftCostField.setText("");

        }
        else if(e.getSource() == deleteGiftButton){
            TreeItem<GiftRow> selectedItem = giftTreeTable.getSelectionModel().getSelectedItem();
            Gift g = selectedItem.getValue().getGift();
            GiftServiceRequest.getGRM().getGiftDirectory().deleteGift(g.getGiftID());
        }
    }

    private float moneyStringToFloat(String money){
        money = money.replace("$", "");
        try{
            float cost = Float.parseFloat(money);
            return cost;
        }
        catch(NumberFormatException e){
            System.out.println("Not a valid dollar value");
            return -1;
        }
    }

    @Override
    public void update() {

    }
}
