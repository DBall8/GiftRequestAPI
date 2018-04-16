package edu.wpi.cs3733.TeamD.TreeTableClasses;

import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import edu.wpi.cs3733.TeamD.Managers.GiftDirectory;
import edu.wpi.cs3733.TeamD.Managers.GiftRequestManager;
import edu.wpi.cs3733.TeamD.ObserverPattern.Observer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.util.ArrayList;


public class GiftTable implements Observer{

    JFXTreeTableView<GiftRow> treeTable;
    JFXTreeTableColumn<GiftRow, String> nameCol;
    JFXTreeTableColumn<GiftRow, String> costCol;

    ObservableList<GiftRow> gifts;

    public GiftTable(JFXTreeTableView<GiftRow> treeTable) {

        this.treeTable = treeTable;

        nameCol = new JFXTreeTableColumn<>("Gift");
        nameCol.setPrefWidth(300);
        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<GiftRow, String>, ObservableValue<String>>() {

            /**
             * call
             * Used to populate the columns in the TreeTableView.  Would be the same for all of the following as well.
             *
             * @param param: Sets up a Column with a field
             * @return the TreeTableView
             */
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<GiftRow, String> param) {
                return param.getValue().getValue().name;
            }
        });

        costCol = new JFXTreeTableColumn<>("Cost");
        costCol.setPrefWidth(300);
        costCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<GiftRow, String>, ObservableValue<String>>() {

            /**
             * call
             * Used to populate the columns in the TreeTableView.  Would be the same for all of the following as well.
             *
             * @param param: Sets up a Column with a field
             * @return the TreeTableView
             */
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<GiftRow, String> param) {
                return param.getValue().getValue().cost;
            }
        });

        GiftServiceRequest.getGRM().getGiftDirectory().subscribe(this);

    }

    public void load(ArrayList<Gift> giftList){
        gifts = FXCollections.observableArrayList();

        for (Gift g : giftList) {
            gifts.add(new GiftRow(g));
        }

        //Sets the columns in the TreeTableView
        final TreeItem<GiftRow> root = new RecursiveTreeItem<>(gifts, RecursiveTreeObject::getChildren);
        treeTable.getColumns().setAll(nameCol, costCol);
        treeTable.setRoot(root);
        treeTable.setShowRoot(false);
    }

    @Override
    public void update() {
        GiftRequestManager GRM = GiftServiceRequest.getGRM();
        ArrayList<Gift> giftList = GRM.getGiftDirectory().getGifts();
        load(giftList);
    }
}
