package edu.ucab.desarrollo.fitucab.Test.M11_Test;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M11.DaoFood;
import org.junit.Test;

import static org.junit.Assert.assertNull;

/**
 * Created by JoseA2R on 3/7/17.
 */
public class M11_Test_DaoFood {

    public void Create(Entity e) {

    }

    @Test
    public void create(Entity e) throws AddException {
        DaoFood Dao = DaoFactory.iniciarDaoFood();
        assertNull(Dao.create(null));
    }

    @Test
    public void read(Entity e) {
        DaoFood Dao = DaoFactory.iniciarDaoFood();
        assertNull(Dao.read(null));
    }

    @Test
    public void update(Entity e) {

        DaoFood Dao = DaoFactory.iniciarDaoFood();
        assertNull(Dao.update(null));

    }

}
