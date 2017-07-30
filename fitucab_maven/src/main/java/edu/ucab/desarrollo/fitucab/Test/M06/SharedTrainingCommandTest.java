package edu.ucab.desarrollo.fitucab.Test.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.SharedTrainingCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Barbara Fernadez on 7/2/2017.
 */
class SharedTrainingCommandTest
{
    @Test
    public void test(){
    Entity prueba = EntityFactory.createTraining(1, "daniel");
    SharedTrainingCommand c = CommandsFactory.instanciateSharedTrainingCmd(prueba);
    Entity result = c.get_result();
    assertEquals(result,false);
    }

}