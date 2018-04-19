package edu.wpi.cs3733.TeamD;

import javafx.application.Application;
import javafx.stage.Stage;

public class Launcher extends Application{

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        try{
            GiftServiceRequest gsr = new GiftServiceRequest();
            //gsr.initialize();
            gsr.run(0, 0, 1900, 1000, null, null, null);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
