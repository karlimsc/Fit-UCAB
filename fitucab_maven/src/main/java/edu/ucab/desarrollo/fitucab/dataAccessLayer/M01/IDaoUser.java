package edu.ucab.desarrollo.fitucab.dataAccessLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M01.LoginUserException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M01.RecoveryPassException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.GetUserException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;

/**
 * Created by karo on 28/06/17.
 */
public interface IDaoUser extends IDao {

    /**
     * Metodo para la recuperacion de cuenta
     * @param email
     * @return
     * @throws SQLException
     */

    String testEmail (String email) throws RecoveryPassException,SQLException;

    /**
     * Metodo para realizar el login
     * @param e
     * @return
     * @throws SQLException
     * @throws LoginUserException
     */
    Entity login(Entity e) throws SQLException, LoginUserException;

    public boolean update() throws CreateHomeException, SQLException;
    public void UpdateName(String name) throws BdConnectException, SQLException;
    public void UpdateEmail(String email) throws SQLException;
    public void UpdatePhone(String phone) throws SQLException;
    /**
     * Metodo que implementa el DaoUser que recibe un _id
     * @param _id
     */
    public Entity read(int _id) throws GetUserException, SQLException;

}
