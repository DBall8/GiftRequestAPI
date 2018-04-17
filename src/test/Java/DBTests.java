import edu.wpi.cs3733.TeamD.Database;
import edu.wpi.cs3733.TeamD.Entities.Employee;
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

        Employee employee = new Employee("e1", "Fred");
        Gift g = new Gift("G2","Snake", (float)19.99, false);
        GiftRequest gr = new GiftRequest(g, "node1");

        Database.getInstance().insertEmployee(employee);
        Database.getInstance().insertGift(g);
        Database.getInstance().insertGR(gr);

        GiftRequestManager grm = new GiftRequestManager();
        GiftDirectory gd = grm.getGiftDirectory();
        EmployeeList el = grm.getEmployeeList();

        assertEquals("Snake", gd.getGift("G2").getName());
        assertEquals((float)19.99, gd.getGift("G2").getCost());


        Gift plane = gd.addGift("Toy plane", (float)5.00, true);
        Employee nate = el.addEmployee("N1", "Nate");

        grm = new GiftRequestManager();
        gd = grm.getGiftDirectory();
        el = grm.getEmployeeList();

        assertEquals("Toy plane", gd.getGift(plane.getGiftID()).getName());
        assertEquals((float)5.00, gd.getGift(plane.getGiftID()).getCost());

        assertEquals(true, el.containsEmployee("Nate"));

        grm.getGiftRequestsFromDate(3);

    }

    @AfterClass
    public static void closedown(){
        Database.getInstance().dropTables();
        Database.getInstance().close();
    }
}
