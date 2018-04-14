import edu.wpi.cs3733.TeamD.Entities.GR;
import edu.wpi.cs3733.TeamD.Entities.Gift;
import org.junit.Test;

public class GeneralTests {

    @Test
    public void creatingEntities(){
        Gift g = new Gift("Plane", (float)12.99, false);
        GR gr = new GR("gr1", g, "Tim");
    }
}
