package edu.ucab.desarrollo.fitucab.dataAccessLayer.M01;

import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;

/**
 * Created by karo on 28/06/17.
 */
public interface IDaoUser extends IDao {
    String testEmail (String email) throws SQLException;

}
