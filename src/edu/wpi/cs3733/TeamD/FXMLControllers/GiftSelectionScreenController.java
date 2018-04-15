package edu.wpi.cs3733.TeamD.FXMLControllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import edu.wpi.cs3733.TeamD.Entities.GiftRequest;
import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import edu.wpi.cs3733.TeamD.Managers.GiftRequestManager;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class GiftSelectionScreenController extends ScreenController implements Initializable{

    @FXML
    private JFXTextField filterTextField;
    @FXML
    private JFXCheckBox foodCheckBox;
    @FXML
    private JFXTreeTableView<GiftRow> giftTreeTable;
    @FXML
    private JFXButton orderGiftButton;
    @FXML
    private JFXTextField locationText;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        JFXTreeTableColumn<GiftRow, String> nameCol = new JFXTreeTableColumn<>("Gift");
        nameCol.setPrefWidth(300);
        nameCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<GiftRow, String>, ObservableValue<String>>() {

            /** call
             *  Used to populate the columns in the TreeTableView.  Would be the same for all of the following as well.
             * @param param: Sets up a Column with a field
             * @return the TreeTableView
             */
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<GiftRow, String> param) {
                return param.getValue().getValue().name;
            }
        });

        JFXTreeTableColumn<GiftRow, String> costCol = new JFXTreeTableColumn<>("Cost");
        costCol.setPrefWidth(300);
        costCol.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<GiftRow, String>, ObservableValue<String>>() {

            /** call
             *  Used to populate the columns in the TreeTableView.  Would be the same for all of the following as well.
             * @param param: Sets up a Column with a field
             * @return the TreeTableView
             */
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<GiftRow, String> param) {
                return param.getValue().getValue().cost;
            }
        });

        ObservableList<GiftRow> gifts = FXCollections.observableArrayList();

        GiftRequestManager GRM = GiftServiceRequest.getGRM();

        for(Gift g: GRM.getGiftDirectory().getGifts()){
            gifts.add(new GiftRow(g.getName(), g.getCost()));
        }

        //Sets the columns in the TreeTableView
        final TreeItem<GiftRow> root = new RecursiveTreeItem<>(gifts, RecursiveTreeObject::getChildren);
        giftTreeTable.getColumns().setAll(nameCol, costCol);
        giftTreeTable.setRoot(root);
        giftTreeTable.setShowRoot(false);

        //Used to filter searches in the TreeTableView.
        filterTextField.textProperty().addListener(new ChangeListener<String>() {

            /** changed
             *  Generated when addListener is called above.  Applies the filtering in the TreeTableView in real time.
             * @param observable: viewable list of items in TreeTable (ability to see display)
             * @param oldValue: string in table
             * @param newValue: input string for filtering
             */
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                //Applies the filtering of a TreeTableView, and generates the test function.
                giftTreeTable.setPredicate(new Predicate<TreeItem<GiftRow>>() {

                    /** test
                     * Checks to see if the input string typed in the search bar is in the table.
                     * @param requestsRowTreeItem: TreeItem populated with the created ServiceRequests.
                     * @return true or false based on if the entered substring is in the table.
                     */
                    @Override
                    public boolean test(TreeItem<GiftRow> requestsRowTreeItem) {
                        Boolean flag = requestsRowTreeItem.getValue().name.getValue().toLowerCase().contains(newValue.toLowerCase()) ||
                                requestsRowTreeItem.getValue().cost.getValue().toLowerCase().contains(newValue.toLowerCase());
                        return flag;
                    }
                });
            }
        });


    }

    @FXML
    private void buttonAction(ActionEvent e){

    }

    public class GiftRow extends RecursiveTreeObject<GiftRow> {
        StringProperty name;
        StringProperty cost;

        public GiftRow(String name, float cost){
            this.name = new SimpleStringProperty(name);
            this.cost = new SimpleStringProperty(costFtoS(cost));
        }

        private String costFtoS(float cost){
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            String str = "$" + df.format(cost);
            return str;
        }

    }
}
