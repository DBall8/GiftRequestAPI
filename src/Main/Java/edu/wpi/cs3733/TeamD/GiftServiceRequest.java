package edu.wpi.cs3733.TeamD;

import edu.wpi.cs3733.TeamD.Managers.GiftRequestManager;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class GiftServiceRequest {

    private Stage stage;

    private static GiftRequestManager GRM;

    private static int windowWidth;
    private static int windowLength;

    private static boolean adminEnabled;
    private static String destNode;

    private static List<String> locations;

    private static boolean isInitialized = false;

    public GiftServiceRequest(){}

    public static void initialize(){
        if(!isInitialized){
            isInitialized = true;
            Database.getInstance().initDatabase();
        }

        GRM = new GiftRequestManager();
        destNode = "";
        adminEnabled = true;
        locations = new ArrayList<>();
    }


    public static GiftRequestManager getGRM(){
        return GRM;
    }

    public static void disableAdmin(){
        GiftServiceRequest.adminEnabled = false;
    }

    public void run(int xcoord, int ycoord, int windowWidth, int windowLength, String cssPath, String destNodeID, String originNodeID) throws ServiceException{

        initialize();

        this.windowWidth = windowWidth;
        this.windowLength = windowLength;
        if(destNodeID != null){
            destNode = destNodeID;
        }

        try{
            stage = new Stage();
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GiftRequestScreen.fxml"));

            Scene scene = new Scene(root, windowWidth, windowLength);
            if(cssPath != null){
                scene.getStylesheets().add(cssPath);
            }

            stage.widthProperty().addListener((obs, oldVal, newVal) -> {
                GiftServiceRequest.windowWidth = newVal.intValue();
            });

            stage.heightProperty().addListener((obs, oldVal, newVal) -> {
                GiftServiceRequest.windowLength = newVal.intValue();
            });

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

    public void importLocations(List<String> locations){
        GiftServiceRequest.locations = locations;
    }

    public static List<String> getLocations(){
        return locations;
    }

    public static boolean getAdmin(){
        return adminEnabled;
    }

    public static String getDestNode(){
        return destNode;
    }

    public static int getWindowWidth(){
        return windowWidth;
    }

    public static int getWindowLength(){
        return windowLength;
    }

    public void resetDatabase(){
        Database.getInstance().dropTables();
        Database.getInstance().createTables();
    }



}
