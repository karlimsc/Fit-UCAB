package M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Barbara Fernadez on 7/2/2017.
 */
class CommandsFactoryTest {
    @Test
    void instanciateCreateTrainingCmd() {

        Entity prueba = EntityFactory.createTraining(1, "daniel");
        CreateTrainingCommand c = CommandsFactory.instanciateCreateTrainingCmd(prueba);
        Entity result = c.getResult();
        assertNull(result);


    }

    @Test
    void instanciateDeleteTrainingCmd() {
        Entity prueba = EntityFactory.createTraining(1, "daniel");
        DeleteTrainingCommand c = CommandsFactory.instanciateDeleteTrainingCmd(prueba);
        boolean result = c.getResult();
        assertEquals(result,false);
    }

    @Test
    void instanciateShareTrainingCmd() {
        Entity prueba = EntityFactory.createTraining(1, "daniel");
        ShareTrainingCommand c = CommandsFactory.instanciateShareTrainingCmd(prueba);
        boolean result = c.getResult();
        assertEquals(result,false);
    }

    @Test
    void instanciateAddActivitiesToTrainingCmd() {
        Entity prueba = EntityFactory.createTraining(1, "daniel");
        AddActivitiesToTrainingCommand c = CommandsFactory.instanciateAddActivitiesToTrainingCmd(prueba);
        boolean result = c.getResult();
        assertEquals(result,false);
    }

    @Test
    void instanciateRemoveActivitiesFromTrainingCmd() {
        Entity prueba = EntityFactory.createTraining(1, "daniel");
        RemoveActivitiesFromTrainingCommand c = CommandsFactory.instanciateRemoveActivitiesFromTrainingCmd(prueba);
        boolean result = c.getResult();
        assertEquals(result,false);
    }

    @Test
    void instanciateChangeTrainingNameCmd() {

        Entity prueba = EntityFactory.createTraining(1, "daniel");
        ChangeTrainingNameCommand c = CommandsFactory.instanciateChangeTrainingNameCmd(prueba);
        boolean result = c.getResult();
        assertEquals(result,false);
    }

    @Test
    void instanciateGetAllTrainingCmd() {
    }

    @Test
    void instanciateGetTrainingDetailCmd() {
    }

}