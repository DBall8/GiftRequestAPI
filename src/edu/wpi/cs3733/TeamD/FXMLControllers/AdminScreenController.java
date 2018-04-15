package edu.wpi.cs3733.TeamD.FXMLControllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        GiftTable giftTable = new GiftTable(giftTreeTable);
        giftTable.load(GiftServiceRequest.getGRM().getGiftDirectory().getGifts());

        JFXTreeTableColumn<EmployeeRow, String> nameCol = new JFXTreeTableColumn<>("Employee");
        nameCol.setPrefWidth(600);
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

        ObservableList<EmployeeRow> employees = FXCollections.observableArrayList();

        GiftRequestManager GRM = GiftServiceRequest.getGRM();

        for (String e : GRM.getEmployeeList().getEmployees()) {
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
    }


}
