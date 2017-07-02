package M06;

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

/**
 * Created by Barbara Fernadez on 7/1/2017.
 */
class TrainingTest {
    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTrainingName() {

        Training prueba = new Training();
        prueba.setTrainingName("daniel");
        assertEquals(prueba.getTrainingName(),"daniel");
    }

    @Test
    void setTrainingName() {

        Training prueba = new Training();
        prueba.setTrainingName("daniel");
        assertEquals(prueba.getTrainingName(),"daniel");

    }

    @Test
    void getTrainingPeriod() {
        Training prueba = new Training();
        prueba.setTrainingPeriod(1);
        assertEquals(prueba.getTrainingPeriod(),1);
    }

    @Test
    void setTrainingPeriod() {
        Training prueba = new Training();
        prueba.setTrainingPeriod(2);
        assertEquals(prueba.getTrainingPeriod(),2);
        assertNotNull(prueba);
    }

    @Test
    void get_activitylist() {
        Activity a = new Activity();
        a.set_name("prueba");
        Training prueba = new Training();
        ArrayList<Entity> activitiesList = new ArrayList<Entity>();
        activitiesList.add(a);
        prueba.set_activitylist(activitiesList);
        Activity resultado = (Activity) prueba.get_activitylist().get(0);
        assertEquals(resultado.get_name(),"prueba");

    }

    @Test
    void set_activitylist() {
        Activity a = new Activity();
        a.set_name("prueba");
        Training prueba = new Training();
        ArrayList<Entity> activitiesList = new ArrayList<Entity>();
        activitiesList.add(a);
        prueba.set_activitylist(activitiesList);
        Activity resultado = (Activity) prueba.get_activitylist().get(0);
        assertEquals(resultado.get_name(),"prueba");
    }

    @Test
    void get_userId() {
        Training prueba = new Training();
        prueba.set_userId(1);
        assertEquals(prueba.get_userId(),1);

    }

    @Test
    void set_userId() {
        Training prueba = new Training();
        prueba.set_userId(1);
        assertEquals(prueba.get_userId(),1);

    }

}