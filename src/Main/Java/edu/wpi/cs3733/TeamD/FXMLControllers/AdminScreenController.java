package edu.wpi.cs3733.TeamD.FXMLControllers;

import com.jfoenix.controls.*;
import edu.wpi.cs3733.TeamD.Entities.Employee;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import edu.wpi.cs3733.TeamD.Managers.GiftRequestManager;
import edu.wpi.cs3733.TeamD.ObserverPattern.Observer;
import edu.wpi.cs3733.TeamD.Reports.EmployeeDeliveryReport;
import edu.wpi.cs3733.TeamD.Reports.GiftFrequencyReport;
import edu.wpi.cs3733.TeamD.Reports.GiftsOverTimeReport;
import edu.wpi.cs3733.TeamD.Reports.PieChartReport;
import edu.wpi.cs3733.TeamD.TreeTableClasses.EmployeeRow;
import edu.wpi.cs3733.TeamD.TreeTableClasses.EmployeeTable;
import edu.wpi.cs3733.TeamD.TreeTableClasses.GiftRow;
import edu.wpi.cs3733.TeamD.TreeTableClasses.GiftTable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;

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

    @FXML
    private JFXButton addPersonnelButton;
    @FXML
    private JFXButton deletePersonnelButton;
    @FXML
    private TextField personnelNameField;
    @FXML
    private TextField employeeIDField;

    @FXML
    private JFXTextField dayTextField;
    @FXML
    private JFXCheckBox isFoodCheckBox;

    @FXML
    private Label warningLabel;

    @FXML
    private BarChart giftFrequencyChart;
    @FXML
    private LineChart delivariesOverTimeChart;
    @FXML
    private LineChart delivariesByEmployeesChart;
    @FXML
    private PieChart pieChart;

    @FXML
    private JFXTabPane tabbedPane;


    GiftTable giftTable;
    EmployeeTable employeeTable;
    ObservableList<EmployeeRow> employees;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        giftTable = new GiftTable(giftTreeTable);

        employeeTable = new EmployeeTable(personnelTreeTable);

        GiftFrequencyReport gfr = new GiftFrequencyReport(giftFrequencyChart);
        gfr.generateReport(7);

        GiftsOverTimeReport gotr = new GiftsOverTimeReport(delivariesOverTimeChart);
        gotr.generateReport(7);

        PieChartReport pcr = new PieChartReport(pieChart);
        pcr.generateReport(7);

        EmployeeDeliveryReport edc = new EmployeeDeliveryReport(delivariesByEmployeesChart);
        edc.generateReport(7);

        dayTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if(oldValue.equals("")){
                dayTextField.setText("0");
                return;
            }
            try{
                int days = Integer.parseInt(newValue);
                gfr.generateReport(days);
                gotr.generateReport(days);
                pcr.generateReport(days);
                edc.generateReport(days);
                dayTextField.setText(Integer.toString(days));
            } catch(NumberFormatException e){
               dayTextField.setText(oldValue);
            }
        });

        tabbedPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        warningLabel.setVisible(false);
                    }
                }
        );
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
                warningLabel.setText("Please provide a name and a cost for the gift.");
                warningLabel.setVisible(true);
                return;
            }

            float cost;
            if((cost = moneyStringToFloat(giftCost)) < 0){
                warningLabel.setText("Invalid money amount.");
                warningLabel.setVisible(true);
                return;
            }

            GiftRequestManager GRM = GiftServiceRequest.getGRM();
            Gift g = GRM.getGiftDirectory().addGift(giftName, cost, isFoodCheckBox.isSelected());

            giftNameField.setText("");
            giftCostField.setText("");
            warningLabel.setVisible(false);

        }
        else if(e.getSource() == deleteGiftButton){
            if(giftTreeTable.getSelectionModel().isEmpty()){
                warningLabel.setText("Please select a gift to delete.");
                warningLabel.setVisible(true);
                return;
            }
            TreeItem<GiftRow> selectedItem = giftTreeTable.getSelectionModel().getSelectedItem();
            Gift g = selectedItem.getValue().getGift();
            GiftServiceRequest.getGRM().getGiftDirectory().deleteGift(g.getGiftID());
        }
        else if(e.getSource() == addPersonnelButton){
            String employeeID = employeeIDField.getText();
            String employeeName = personnelNameField.getText();

            if(employeeID.equals("") || employeeName.equals("")){
                warningLabel.setText("Please provide an ID and a name for the employee.");
                warningLabel.setVisible(true);
                return;
            }

            GiftRequestManager GRM = GiftServiceRequest.getGRM();
            GRM.getEmployeeList().addEmployee(employeeID, employeeName);

            employeeIDField.setText("");
            personnelNameField.setText("");
            warningLabel.setVisible(false);
        }
        else if(e.getSource() == deletePersonnelButton){
            if(personnelTreeTable.getSelectionModel().isEmpty()){
                warningLabel.setText("Please select an employee to delete.");
                warningLabel.setVisible(true);
                return;
            }
            TreeItem<EmployeeRow> selectedItem = personnelTreeTable.getSelectionModel().getSelectedItem();
            Employee employee = selectedItem.getValue().getEmployee();
            GiftServiceRequest.getGRM().getEmployeeList().deleteEmployee(employee.getEmployeeID());
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
