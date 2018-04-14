package edu.wpi.cs3733.TeamD.FXMLControllers;

import edu.wpi.cs3733.TeamD.Managers.GiftRequestManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

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


}
