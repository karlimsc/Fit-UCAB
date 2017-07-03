package M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.DeleteTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.RemoveActivitiesFromTrainingCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by erwe on 7/2/2017.
 */
class RemoveActivitiesFromTrainingCommandTest {
    @Test
    public void test(){


        Entity prueba = EntityFactory.createTraining(1, "daniel");
        RemoveActivitiesFromTrainingCommand c = CommandsFactory.instanciateRemoveActivitiesFromTrainingCmd(prueba);
        boolean result = c.getResult();
        assertEquals(result,false);
    }

}