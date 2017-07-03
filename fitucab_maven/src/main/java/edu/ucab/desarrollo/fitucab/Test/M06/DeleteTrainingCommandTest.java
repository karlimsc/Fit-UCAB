package M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.ChangeTrainingNameCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.DeleteTrainingCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by  erwe on 7/2/2017.
 */
class DeleteTrainingCommandTest {

    @Test
    public void test(){

        Entity prueba = EntityFactory.createTraining(1, "daniel");
        DeleteTrainingCommand c = CommandsFactory.instanciateDeleteTrainingCmd(prueba);
        boolean result = c.getResult();
        assertEquals(result,false);
    }

}