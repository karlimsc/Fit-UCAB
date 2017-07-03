package M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.AddActivitiesToTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.ChangeTrainingNameCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class ChangeTrainingNameCommandTest {

    @Test
    public void test(){
        Entity prueba = EntityFactory.createTraining(1, "daniel");
        ChangeTrainingNameCommand c = CommandsFactory.instanciateChangeTrainingNameCmd(prueba);
        boolean result = c.getResult();
        assertEquals(result,false);


    }

}