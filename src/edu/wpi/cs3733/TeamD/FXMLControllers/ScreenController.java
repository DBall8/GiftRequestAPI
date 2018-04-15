package edu.wpi.cs3733.TeamD.FXMLControllers;

import edu.wpi.cs3733.TeamD.Managers.GiftRequestManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public abstract class ScreenController {

    protected void switchScreen(Stage stage, String fxmlFile){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(fxmlFile));

        try{
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    protected void popupScreen(String fxml, Window ownerWindow, String title) throws IOException{
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource(fxml));
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL); //prevents main window from receiving input
        stage.initOwner(ownerWindow); //sets the main window as this screens owner
        stage.setResizable(false);
        stage.showAndWait();

    }


}
