package M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.ChangeTrainingNameCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.CreateTrainingCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Barbara Fernadez on 7/2/2017.
 */
class CreateTrainingCommandTest {
    @Test
    public void test(){


        Entity prueba = EntityFactory.createTraining(1, "daniel");
        CreateTrainingCommand c = CommandsFactory.instanciateCreateTrainingCmd(prueba);
        Entity result = c.getResult();
        assertNull(result);
    }

}