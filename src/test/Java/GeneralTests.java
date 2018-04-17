import edu.wpi.cs3733.TeamD.Entities.GiftRequest;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import org.junit.Test;

import java.util.Calendar;
import java.sql.Date;

public class GeneralTests {

    @Test
    public void creatingEntities(){
        Gift g = new Gift("G1","Plane", (float)12.99, false);
        GiftRequest gr = new GiftRequest(g, "node1");

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1);

        Date d = new Date(c.getTime().getTime());
        System.out.println(d);

    }
}
