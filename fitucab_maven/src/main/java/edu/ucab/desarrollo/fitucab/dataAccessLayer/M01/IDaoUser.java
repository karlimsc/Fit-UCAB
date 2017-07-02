package edu.ucab.desarrollo.fitucab.dataAccessLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;



public interface IDaoUser extends IDao {
    String testEmail (String email) throws SQLException;
    Entity login(Entity e) throws SQLException;
}
