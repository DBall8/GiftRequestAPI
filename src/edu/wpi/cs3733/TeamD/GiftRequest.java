package edu.wpi.cs3733.TeamD;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GiftRequest {


    public GiftRequest(){
        Database.getInstance().initDatabase();
    }

    public void run(int xcoord, int ycoord, int windowWidth, int windowLength, String cssPath, String destNodeID, String originNodeID) throws ServiceException{

        try{
            Stage stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GiftRequestScreen.fxml"));
            stage.setTitle("edu.wpi.cs3733.TeamD.Entitiescs3733.TeamD.Gift Request");
            stage.setScene(new Scene(root, windowWidth, windowLength));
            stage.show();
        }
        catch(IOException e){
            System.out.println("Could not load edu.wpi.cs3733.TeamD.Gift3.TeamD.Entities.Gift Request screen.");
            e.printStackTrace();
            throw new ServiceException("Couldnt load main screen.");
        }

    }
}
