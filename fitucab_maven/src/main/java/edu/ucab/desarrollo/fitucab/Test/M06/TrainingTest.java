package edu.ucab.desarrollo.fitucab.Test.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Activity;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Training;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;


class TrainingTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTrainingName() {

        Training prueba = EntityFactory.createTraining();
        prueba.setTrainingName("daniel");
        assertEquals(prueba.getTrainingName(),"daniel");
    }

    @Test
    void setTrainingName() {

        Training prueba = EntityFactory.createTraining();
        prueba.setTrainingName("daniel");
        assertEquals(prueba.getTrainingName(),"daniel");

    }

    @Test
    void getTrainingPeriod() {
        Training prueba = EntityFactory.createTraining();
        prueba.setTrainingPeriod(1);
        assertEquals(prueba.getTrainingPeriod(),1);
    }

    @Test
    void setTrainingPeriod() {
        Training prueba = EntityFactory.createTraining();
        prueba.setTrainingPeriod(2);
        assertEquals(prueba.getTrainingPeriod(),2);
        assertNotNull(prueba);
    }

    @Test
    void get_activitylist() {
        Activity a = EntityFactory.createActivity();
        a.set_name("prueba");
        Training prueba = EntityFactory.createTraining();
        ArrayList<Entity> activitiesList = new ArrayList<Entity>();
        activitiesList.add(a);
        prueba.set_activitylist(activitiesList);
        Activity resultado = (Activity) prueba.get_activitylist().get(0);
        assertEquals(resultado.get_name(),"prueba");

    }

    @Test
    void set_activitylist() {
        Activity a = EntityFactory.createActivity();
        a.set_name("prueba");
        Training prueba = EntityFactory.createTraining();
        ArrayList<Entity> activitiesList = new ArrayList<Entity>();
        activitiesList.add(a);
        prueba.set_activitylist(activitiesList);
        Activity resultado = (Activity) prueba.get_activitylist().get(0);
        assertEquals(resultado.get_name(),"prueba");
    }

    @Test
    void get_userId() {
        Training prueba = EntityFactory.createTraining();
        prueba.set_userId(1);
        assertEquals(prueba.get_userId(),1);

    }

    @Test
    void set_userId() {
        Training prueba = EntityFactory.createTraining();;
        prueba.set_userId(1);
        assertEquals(prueba.get_userId(),1);

    }

}