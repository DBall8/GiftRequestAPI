import edu.wpi.cs3733.TeamD.Database;
import edu.wpi.cs3733.TeamD.Entities.Employee;
import edu.wpi.cs3733.TeamD.Entities.GR;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

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

    }

    @AfterClass
    public static void closedown(){
        Database.getInstance().dropTables();
        Database.getInstance().close();
    }
}
