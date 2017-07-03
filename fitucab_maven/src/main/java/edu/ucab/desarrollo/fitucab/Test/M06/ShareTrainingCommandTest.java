package M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.RemoveActivitiesFromTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.ShareTrainingCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Barbara Fernadez on 7/2/2017.
 */
class ShareTrainingCommandTest {
    @Test
    public void test(){
    Entity prueba = EntityFactory.createTraining(1, "daniel");
    ShareTrainingCommand c = CommandsFactory.instanciateShareTrainingCmd(prueba);
    boolean result = c.getResult();
    assertEquals(result,false);
    }

}