import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class TestOpenerController {
    @FXML
    Button onlyButton;

    @FXML
    public void buttonAction(ActionEvent e){
        GiftRequest gr = new GiftRequest();
        try{
            gr.run(0,0,1900,1000,null,null,null);
        }
        catch(ServiceException se){
            System.out.println("Could not work");
            se.printStackTrace();
        }
    }
}
