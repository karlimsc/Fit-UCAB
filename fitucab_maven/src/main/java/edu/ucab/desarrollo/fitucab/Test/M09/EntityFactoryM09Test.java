package edu.ucab.desarrollo.fitucab.Test.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Challenge;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M09.DaoGaming;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

/**
 * Created by JuanMendez on 3/7/2017.
 */
public class EntityFactoryM09Test {

    @Test
    public void CreateChallengeConstructTestPUE() {
        Entity entidad = EntityFactory.createChallenge();
        entidad.set_id(1);
        assertEquals(1,entidad.get_id());
    }

    @Test(expected = AssertionError.class)
    public void CreateChallengeConstructTestPUFE() {
        Entity entidad = EntityFactory.createChallenge();
        assertNull(entidad);
    }

    @Test
    public void CreateChallengeScoreTestPUE() {
        Entity entidad = EntityFactory.createChallenge(10);
        assertNotNull(entidad);
    }

    @Test(expected = AssertionError.class)
    public void CreateChallengeScoreTestPUFE() {
        Entity entidad = EntityFactory.createChallenge(10);
        assertNull(entidad);
    }

    @Test
    public void CreateChallengeAchieveTestPUE() {
        Entity entidad = EntityFactory.createChallenge(5,5);
        assertNotNull(entidad);
    }

    @Test(expected = AssertionError.class)
    public void CreateChallengeAchieveTestPUFE() {
        Entity entidad = EntityFactory.createChallenge(5,5);
        assertNull(entidad);
    }

    @Test
    public void CreateChallengeConstructorTestPUE() {
        Entity entidad = EntityFactory.createChallenge(1,"Prueba de Nombre de Reto","Prueba de Descripcion de Reto",1);
        assertNotNull(entidad);
    }

    @Test(expected = AssertionError.class)
    public void CreateChallengeConstructorTestPUFE() {
        Entity entidad = EntityFactory.createChallenge(1,"Prueba de Nombre de Reto","Prueba de Descripcion de Reto",1);
        assertNull(entidad);
    }

    @Test
    public void InstanceDaoGamingTestPUE() {
        Dao dao = DaoFactory.instanceDaoGaming();
        assertNotNull(dao);
    }

    @Test(expected = AssertionError.class)
    public void InstanceDaoGamingTestPUFE() {
        Dao dao = DaoFactory.instanceDaoGaming();
        assertNull(dao);
    }

}
