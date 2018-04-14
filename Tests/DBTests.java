import edu.wpi.cs3733.TeamD.Database;
import edu.wpi.cs3733.TeamD.Entities.GiftRequest;
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
        GiftRequest gr = new GiftRequest("gr1", g, employee, "node1");

        Database.getInstance().insertEmployee(employee);
        Database.getInstance().insertGift(g);
        Database.getInstance().insertGR(gr);

        GiftRequestManager grm = new GiftRequestManager();
        GiftDirectory gd = grm.getGiftDirectory();
        EmployeeList el = grm.getEmployeeList();

        assertEquals("Snake", gd.getGift("Snake").getName());
        assertEquals((float)19.99, gd.getGift("Snake").getCost());

        assertEquals("Fred", el.getEmployee(0));

        gd.addGift("Toy plane", (float)5.00, true);
        el.addEmployee("Nate");

        Database.getInstance().close();
        Database.getInstance().initDatabase();

        grm = new GiftRequestManager();
        gd = grm.getGiftDirectory();
        el = grm.getEmployeeList();

        assertEquals("Toy plane", gd.getGift("Toy plane").getName());
        assertEquals((float)5.00, gd.getGift("Toy plane").getCost());

        assertEquals(true, el.containsEmployee("Nate"));



    }

    @AfterClass
    public static void closedown(){
        Database.getInstance().dropTables();
        Database.getInstance().close();
    }
}
