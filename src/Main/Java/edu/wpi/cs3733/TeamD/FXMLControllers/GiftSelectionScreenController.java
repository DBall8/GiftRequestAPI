package edu.wpi.cs3733.TeamD.FXMLControllers;

import com.jfoenix.controls.*;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import edu.wpi.cs3733.TeamD.GiftServiceRequest;
import edu.wpi.cs3733.TeamD.Managers.GiftRequestManager;
import edu.wpi.cs3733.TeamD.TreeTableClasses.GiftRow;
import edu.wpi.cs3733.TeamD.TreeTableClasses.GiftTable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.util.List;
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
    private JFXButton cancelButton;
    @FXML
    private JFXTextField locationTextField;
    @FXML
    private JFXTextField recipientTextField;
    @FXML
    private Label errorLabel;

    GiftTable giftTable;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        errorLabel.setVisible(false);

        giftTable = new GiftTable(giftTreeTable);

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

        List<String> locations = GiftServiceRequest.getLocations();
        if(locations.size() > 0){
            TextFields.bindAutoCompletion(locationTextField, locations);
        }

        if(!GiftServiceRequest.getDestNode().equals("")){
            locationTextField.setText(GiftServiceRequest.getDestNode());
        }
    }

    @FXML
    private void buttonAction(ActionEvent e){
        if(e.getSource() == orderGiftButton){

            String location = locationTextField.getText();
            String recipient = recipientTextField.getText();
            if(location.equals("") || recipient.equals("")){
                System.out.println("please select a delivery location and a recipient");
                errorLabel.setVisible(true);
                return;
            }

            TreeItem<GiftRow> selectedItem = giftTreeTable.getSelectionModel().getSelectedItem();
            Gift g = selectedItem.getValue().getGift();

            GiftRequestManager GRM = GiftServiceRequest.getGRM();

            GRM.addGiftRequest(g, location, recipient);

            ((Stage)locationTextField.getScene().getWindow()).close();
        }
        else if(e.getSource() == cancelButton){
            ((Stage)cancelButton.getScene().getWindow()).close();
        }
    }

    @FXML
    private void checkBoxAction(ActionEvent e){
        giftTable.showFood = foodCheckBox.isSelected();
        giftTable.update();
    }
}
