package edu.wpi.cs3733.TeamD;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GiftServiceRequest {


    public GiftServiceRequest(){
        Database.getInstance().initDatabase();
    }

    public void run(int xcoord, int ycoord, int windowWidth, int windowLength, String cssPath, String destNodeID, String originNodeID) throws ServiceException{

        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GiftRequestScreen.fxml"));

            Scene scene = new Scene(root, windowWidth, windowLength);
            scene.getStylesheets().add(cssPath);

            stage.setTitle("Gift Request");
            stage.setX(xcoord);
            stage.setY(ycoord);
            stage.setScene(scene);
            stage.show();
        }
        catch(IOException e){
            System.out.println("Could not load Gift Request screen.");
            e.printStackTrace();
            throw new ServiceException("Couldnt load main screen.");
        }

    }
}
