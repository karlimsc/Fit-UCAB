package M06;

import edu.ucab.desarrollo.fitucab.common.entities.Activity;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Barbara Fernadez on 7/2/2017.
 */
class DaoTrainingTest {
    @Test
    void create() {
        Activity act = EntityFactory.createActivity(1, "Caminar", 2);
        ArrayList<Entity> activities = new ArrayList<Entity>();
        activities.add(act);
        Entity training = EntityFactory.createTraining(1, "PruebaUnitaria", activities);
        DaoTraining dao = DaoFactory.instanceDaoTraining( training );
        try {
            Entity result = dao.create(training);
            assertEquals(1, result.get_id());
        }catch (Exception ex){
            assertTrue(ex instanceof AddException);
        }
    }

    @Test
    void read() {
        DaoTraining dao = DaoFactory.instanceDaoTraining(null);
        assertNull(dao.read(null));
    }

    @Test
    void update() {
        DaoTraining dao = DaoFactory.instanceDaoTraining(null);
        assertNull(dao.update(null));
    }

    @Test
    void delete() {
        Activity act = EntityFactory.createActivity(1, "Caminar", 2);
        ArrayList<Entity> activities = new ArrayList<Entity>();
        activities.add(act);
        Entity training = EntityFactory.createTraining(1, "PruebaUnitaria", activities);
        DaoTraining dao = DaoFactory.instanceDaoTraining( training );
        try {
            Entity result = dao.create(training);
            Boolean boolresult = dao.delete(result);
            assertEquals(true, boolresult);
        }catch (Exception ex){
            assertTrue(ex instanceof DeleteException);
        }
    }

    @Test
    void listAll() throws ListAllException {

    }

    @Test
    void trainingDetail() {
    }

    @Test
    void activateTraining() {
        DaoTraining dao = DaoFactory.instanceDaoTraining(null);
        assertNull(dao.activateTraining(null));
    }

    @Test
    void shareTraining() {
        Activity act = EntityFactory.createActivity(1, "Caminar", 2);
        ArrayList<Entity> activities = new ArrayList<Entity>();
        activities.add(act);
        Entity training = EntityFactory.createTraining(1, "PruebaUnitaria", activities);
        DaoTraining dao = DaoFactory.instanceDaoTraining( training );
        try {
            Boolean boolresult = dao.shareTraining(training);
            assertEquals(true, boolresult);
        }catch (Exception ex){
            assertTrue(ex instanceof ShareException);
        }
    }

    @Test
    void listTrainingsShared() {
        DaoTraining dao = DaoFactory.instanceDaoTraining(null);
        assertNull(dao.listTrainingsShared(null));
    }

    @Test
    void modifyName() {
        Activity act = EntityFactory.createActivity(1, "Caminar", 2);
        ArrayList<Entity> activities = new ArrayList<Entity>();
        activities.add(act);
        Entity training = EntityFactory.createTraining(1, "PruebaUnitaria", activities);
        DaoTraining dao = DaoFactory.instanceDaoTraining( training );
        try {
            Entity result = dao.create(training);
            Boolean boolresult = dao.modifyName(result);
            assertEquals(true, boolresult);
            dao.delete(result);
        }catch (Exception ex){
            assertTrue(ex instanceof UpdateException);
        }
    }

    @Test
    void addActivities() {
        Activity act = EntityFactory.createActivity(1, "Caminar", 2);
        ArrayList<Entity> activities = new ArrayList<Entity>();
        activities.add(act);
        Entity training = EntityFactory.createTraining(1, "PruebaUnitaria", activities);
        DaoTraining dao = DaoFactory.instanceDaoTraining( training );
        try {
            activities.clear();
            Entity result = dao.create(training);
            Activity _act = EntityFactory.createActivity(2, "Trotar", 1);
            activities.add(_act);
            Entity updateTest = EntityFactory.createTraining(1, activities, "PruebaUnitaria");
            Boolean boolresult = dao.addActivities(updateTest);
            assertEquals(true, boolresult);
            dao.delete(result);
        }catch (Exception ex){
            assertTrue(ex instanceof DeleteException);
        }
    }

    @Test
    void removeActivities() {
        Activity act = EntityFactory.createActivity(1, "Caminar", 2);
        ArrayList<Entity> activities = new ArrayList<Entity>();
        activities.add(act);
        Entity training = EntityFactory.createTraining(1, "PruebaUnitaria", activities);
        DaoTraining dao = DaoFactory.instanceDaoTraining( training );
        try {
            Entity result = dao.create(training);
            Boolean boolresult = dao.removeActivities(result);
            assertEquals(true, boolresult);
            dao.delete(result);
        }catch (Exception ex){
            assertTrue(ex instanceof DeleteException);
        }
    }

}