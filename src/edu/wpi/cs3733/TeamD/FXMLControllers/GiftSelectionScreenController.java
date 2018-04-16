package edu.wpi.cs3733.TeamD.FXMLControllers;

import com.jfoenix.controls.*;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import edu.wpi.cs3733.TeamD.Managers.GiftRequestManager;
import edu.wpi.cs3733.TeamD.TreeTableClasses.GiftRow;
import edu.wpi.cs3733.TeamD.TreeTableClasses.GiftTable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.text.DecimalFormat;
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
    private JFXTextField locationTextField;
    @FXML
    private Label errorLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        errorLabel.setVisible(false);

        GiftTable giftTable = new GiftTable(giftTreeTable);
        giftTable.load(GiftServiceRequest.getGRM().getGiftDirectory().getGifts());

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
        if(e.getSource() == orderGiftButton){

            String location = locationTextField.getText();
            if(location.equals("")){
                System.out.println("please select a delivery location");
                errorLabel.setVisible(true);
                return;
            }

            TreeItem<GiftRow> selectedItem = giftTreeTable.getSelectionModel().getSelectedItem();
            Gift g = selectedItem.getValue().getGift();

            GiftRequestManager GRM = GiftServiceRequest.getGRM();

            GRM.addGiftRequest(g, location);

            ((Stage)locationTextField.getScene().getWindow()).close();
        }
    }
}
