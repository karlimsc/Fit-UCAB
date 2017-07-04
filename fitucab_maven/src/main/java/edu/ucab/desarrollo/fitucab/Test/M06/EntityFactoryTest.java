package M06;

import edu.ucab.desarrollo.fitucab.common.entities.Activity;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Training;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Barbara Fernadez on 7/1/2017.
 */
class EntityFactoryTest {
    @Test
    void createTraining() {
       Entity e = EntityFactory.createTraining(1);
        Training c = (Training) e;
        //el contructor setea a id de entity no iduser
        assertEquals(c.get_userId(),0);
    }

    @Test
    void createTraining1() {
        Entity e =EntityFactory.createTraining(1,"daniel");
        Training c = (Training) e;
        assertEquals(c.getTrainingName(),"daniel");

    }

    @Test
    void createTraining2() {
        Entity e = EntityFactory.createTraining(1,"daniel",2);
        Training c = (Training) e;
        assertEquals(c.getTrainingPeriod(),2);
    }

    @Test
    void createTraining3() {

        Activity a = new Activity();
        a.set_name("daniel");
        ArrayList<Entity> prueba = new ArrayList<Entity>();
        prueba.add(a);
        Entity e = EntityFactory.createTraining(1,prueba,"daniel");
        Training c = (Training) e;
        assertEquals(c.get_activitylist(),prueba);
    }

}