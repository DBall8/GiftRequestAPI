import edu.wpi.cs3733.TeamD.Database;
import edu.wpi.cs3733.TeamD.Entities.GR;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import edu.wpi.cs3733.TeamD.Managers.EmployeeList;
import edu.wpi.cs3733.TeamD.Managers.GiftDirectory;
import edu.wpi.cs3733.TeamD.Managers.GiftRequestManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class DBTests {

    @BeforeClass
    public static void startup(){
        Database.getInstance().initDatabase();
    }

    @Test
    public void dbtests(){

        String employee = "Fred";
        Gift g = new Gift("Snake", (float)19.99, false);
        GR gr = new GR("gr1", g, employee);

        Database.getInstance().insertEmployee(employee);
        Database.getInstance().insertGift(g);
        Database.getInstance().insertGR(gr);

        GiftRequestManager grm = new GiftRequestManager();
        GiftDirectory gd = grm.getGiftDirectory();
        EmployeeList el = grm.getEmployeeList();

        assertEquals("Snake", gd.getGift("Snake").getName());
        assertEquals((float)19.99, gd.getGift("Snake").getCost());

        assertEquals("Fred", el.getEmployee(0));

    }

    @AfterClass
    public static void closedown(){
        Database.getInstance().dropTables();
        Database.getInstance().close();
    }
}
