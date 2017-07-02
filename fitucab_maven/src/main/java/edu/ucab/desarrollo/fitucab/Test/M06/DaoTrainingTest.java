package M06;

import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Barbara Fernadez on 7/2/2017.
 */
class DaoTrainingTest {
    @Test
    void create() {

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
    }

    @Test
    void listTrainingsShared() {
        DaoTraining dao = DaoFactory.instanceDaoTraining(null);
        assertNull(dao.listTrainingsShared(null));
    }

    @Test
    void modifyName() {
    }

    @Test
    void addActivities() {
    }

    @Test
    void removeActivities() {
    }

}