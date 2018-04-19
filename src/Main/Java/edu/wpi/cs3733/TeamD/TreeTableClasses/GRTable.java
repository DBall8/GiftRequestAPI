package edu.wpi.cs3733.TeamD.TreeTableClasses;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.wpi.cs3733.TeamD.Entities.GiftRequest;
import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import edu.wpi.cs3733.TeamD.Managers.GiftRequestManager;
import edu.wpi.cs3733.TeamD.ObserverPattern.Observer;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.util.List;

public class GRTable implements Observer {

    private boolean assigned;

    JFXTreeTableView<GRRow> treeTable;
    JFXTreeTableColumn<GRRow, String> giftCol;
    JFXTreeTableColumn<GRRow, String> recipientCol;
    JFXTreeTableColumn<GRRow, String> locationCol;
    JFXTreeTableColumn<GRRow, String> assigneeCol;
    JFXTreeTableColumn<GRRow, String> timeCol;
    JFXTreeTableColumn<GRRow, String> dateCol;

    ObservableList<GRRow> giftRequests;

    public GRTable(JFXTreeTableView<GRRow> treeTable, boolean assigned){

        this.assigned = assigned;
        this.treeTable = treeTable;

        double tableWidth = GiftServiceRequest.getWindowWidth()*0.45;
        int numCols = assigned? 5: 4;

        giftCol = new JFXTreeTableColumn<>("Gift");
        giftCol.setPrefWidth(tableWidth /numCols);
        giftCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<GRRow, String>, ObservableValue<String>>() {

            /**
             * call
             * Used to populate the columns in the TreeTableView.  Would be the same for all of the following as well.
             *
             * @param param: Sets up a Column with a field
             * @return the TreeTableView
             */
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<GRRow, String> param) {
                return param.getValue().getValue().giftName;
            }
        });

        locationCol = new JFXTreeTableColumn<>("Location");
        locationCol.setPrefWidth(tableWidth/numCols);
        locationCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<GRRow, String>, ObservableValue<String>>() {

            /**
             * call
             * Used to populate the columns in the TreeTableView.  Would be the same for all of the following as well.
             *
             * @param param: Sets up a Column with a field
             * @return the TreeTableView
             */
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<GRRow, String> param) {
                return param.getValue().getValue().location;
            }
        });

        recipientCol = new JFXTreeTableColumn<>("Recipient's name");
        recipientCol.setPrefWidth(tableWidth/numCols);
        recipientCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<GRRow, String>, ObservableValue<String>>() {

            /**
             * call
             * Used to populate the columns in the TreeTableView.  Would be the same for all of the following as well.
             *
             * @param param: Sets up a Column with a field
             * @return the TreeTableView
             */
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<GRRow, String> param) {
                return param.getValue().getValue().recipient;
            }
        });

        assigneeCol = new JFXTreeTableColumn<>("Assignee");
        assigneeCol.setPrefWidth(tableWidth/numCols);
        assigneeCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<GRRow, String>, ObservableValue<String>>() {

            /**
             * call
             * Used to populate the columns in the TreeTableView.  Would be the same for all of the following as well.
             *
             * @param param: Sets up a Column with a field
             * @return the TreeTableView
             */
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<GRRow, String> param) {
                return param.getValue().getValue().assignee;
            }
        });

        timeCol = new JFXTreeTableColumn<>("Time ordered");
        timeCol.setPrefWidth(tableWidth/numCols);
        timeCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<GRRow, String>, ObservableValue<String>>() {

            /**
             * call
             * Used to populate the columns in the TreeTableView.  Would be the same for all of the following as well.
             *
             * @param param: Sets up a Column with a field
             * @return the TreeTableView
             */
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<GRRow, String> param) {
                return param.getValue().getValue().time;
            }
        });

        dateCol = new JFXTreeTableColumn<>("Date");
        dateCol.setPrefWidth(tableWidth/numCols);
        dateCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<GRRow, String>, ObservableValue<String>>() {

            /**
             * call
             * Used to populate the columns in the TreeTableView.  Would be the same for all of the following as well.
             *
             * @param param: Sets up a Column with a field
             * @return the TreeTableView
             */
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<GRRow, String> param) {
                return param.getValue().getValue().date;
            }
        });

        GiftServiceRequest.getGRM().subscribe(this);
        update();

    }

    @Override
    public void update() {
        giftRequests = FXCollections.observableArrayList();

        GiftRequestManager GRM = GiftServiceRequest.getGRM();

        List<GiftRequest> requests = assigned? GRM.getPendingGiftRequests(): GRM.getUnassignedGiftRequests();

        for (GiftRequest giftRequest : requests) {
            giftRequests.add(new GRRow(giftRequest));
        }

        //Sets the columns in the TreeTableView
        final TreeItem<GRRow> root = new RecursiveTreeItem<>(giftRequests, RecursiveTreeObject::getChildren);
        if(assigned){
            treeTable.getColumns().setAll(giftCol, recipientCol, locationCol, assigneeCol, timeCol, dateCol);
        }
        else{
            treeTable.getColumns().setAll(giftCol, recipientCol, locationCol, timeCol, dateCol);
        }

        treeTable.setRoot(root);
        treeTable.setShowRoot(false);
    }
}
