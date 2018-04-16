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
import edu.wpi.cs3733.TeamD.TreeTableClasses.EmployeeRow;
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

public class AdminScreenController extends ScreenController implements Initializable{

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
    ObservableList<EmployeeRow> employees;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        giftTable = new GiftTable(giftTreeTable);
        giftTable.load(GiftServiceRequest.getGRM().getGiftDirectory().getGifts());

        JFXTreeTableColumn<EmployeeRow, String> idCol = new JFXTreeTableColumn<>("Employee ID");
        idCol.setPrefWidth(300);
        idCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<EmployeeRow, String>, ObservableValue<String>>() {

            /**
             * call
             * Used to populate the columns in the TreeTableView.  Would be the same for all of the following as well.
             *
             * @param param: Sets up a Column with a field
             * @return the TreeTableView
             */
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<EmployeeRow, String> param) {
                return param.getValue().getValue().employeeID;
            }
        });

        JFXTreeTableColumn<EmployeeRow, String> nameCol = new JFXTreeTableColumn<>("Name");
        nameCol.setPrefWidth(300);
        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<EmployeeRow, String>, ObservableValue<String>>() {

            /**
             * call
             * Used to populate the columns in the TreeTableView.  Would be the same for all of the following as well.
             *
             * @param param: Sets up a Column with a field
             * @return the TreeTableView
             */
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<EmployeeRow, String> param) {
                return param.getValue().getValue().name;
            }
        });

        employees = FXCollections.observableArrayList();

        GiftRequestManager GRM = GiftServiceRequest.getGRM();

        for (Employee e : GRM.getEmployeeList().getEmployees()) {
            employees.add(new EmployeeRow(e));
        }

        //Sets the columns in the TreeTableView
        final TreeItem<EmployeeRow> root = new RecursiveTreeItem<>(employees, RecursiveTreeObject::getChildren);
        personnelTreeTable.getColumns().setAll(nameCol);
        personnelTreeTable.setRoot(root);
        personnelTreeTable.setShowRoot(false);
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

}
