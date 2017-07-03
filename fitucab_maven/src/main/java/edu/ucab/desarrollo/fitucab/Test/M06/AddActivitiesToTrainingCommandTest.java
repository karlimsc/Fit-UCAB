package M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.AddActivitiesToTrainingCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class AddActivitiesToTrainingCommandTest {
    @Test

    public void test() {
        Entity prueba = EntityFactory.createTraining(1, "daniel");
        AddActivitiesToTrainingCommand c = CommandsFactory.instanciateAddActivitiesToTrainingCmd(prueba);
        boolean result = c.getResult();
        assertEquals(result,false);

    }




}