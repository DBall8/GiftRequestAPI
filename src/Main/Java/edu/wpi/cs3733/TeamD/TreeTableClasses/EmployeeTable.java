package edu.wpi.cs3733.TeamD.TreeTableClasses;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.wpi.cs3733.TeamD.Entities.Employee;
import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import edu.wpi.cs3733.TeamD.Managers.GiftRequestManager;
import edu.wpi.cs3733.TeamD.ObserverPattern.Observer;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

public class EmployeeTable implements Observer{

    private JFXTreeTableView<EmployeeRow> treeTable;

    private JFXTreeTableColumn<EmployeeRow, String> idCol;
    private JFXTreeTableColumn<EmployeeRow, String> nameCol;

    private ObservableList<EmployeeRow> employees;

    public EmployeeTable(JFXTreeTableView<EmployeeRow> personnelTreeTable){

        this.treeTable = personnelTreeTable;

        idCol = new JFXTreeTableColumn<>("Employee ID");
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

        nameCol = new JFXTreeTableColumn<>("Name");
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

        GiftServiceRequest.getGRM().getEmployeeList().subscribe(this);
        update();
    }

    @Override
    public void update() {
        employees = FXCollections.observableArrayList();

        GiftRequestManager GRM = GiftServiceRequest.getGRM();

        for (Employee e : GRM.getEmployeeList().getEmployees()) {
            employees.add(new EmployeeRow(e));
        }

        //Sets the columns in the TreeTableView
        final TreeItem<EmployeeRow> root = new RecursiveTreeItem<>(employees, RecursiveTreeObject::getChildren);
        treeTable.getColumns().setAll(idCol, nameCol);
        treeTable.setRoot(root);
        treeTable.setShowRoot(false);
    }
}
