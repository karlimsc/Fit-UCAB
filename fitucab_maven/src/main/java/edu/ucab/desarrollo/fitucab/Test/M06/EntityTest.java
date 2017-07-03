package edu.ucab.desarrollo.fitucab.Test.M06;

import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;

import junit.framework.Assert;
import org.junit.jupiter.api.Test;

public class EntityTest {

    @Test
    public void prueba (){

        int pruebanumero = 1;

        edu.ucab.desarrollo.fitucab.common.entities.Entity prueba = EntityFactory.createTraining(pruebanumero);
        Assert.assertNotNull("no vacio",prueba);

    }
}
