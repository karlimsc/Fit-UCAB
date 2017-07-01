package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.GetUserException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.*;

import java.sql.*;
import java.util.Date;

/**
 * Created by root on 29/06/17.
 */
public class DaoUser extends Dao implements IDaoUser {

    int id;
    Entity _user;
    Date birthdate;

    /**
     * Metodo constructor que recibe un id
     * @param id
     */
    public DaoUser(int id) {
        this.id = id;
    }

    /**
     * Metodo que consulta el perfil por un query de un storeprocedures
     * @param id
     * @return
     */
    @Override
    public Entity read(int id) throws GetUserException{
        try {
            Connection conn = getBdConnect();
            ResultSet result = sql("select * from m02_consultarperfilid("+ id +")");

            while(result.next()){
                String usuario = result.getString( "usuario" );
                String email = result.getString( "email" );
                String sex = result.getString( "sex" );
                String phone = result.getString( "phone" );
                Date birthdate = result.getDate( "birthdate" );
                int  height = result.getInt( "height" );
                int weight = result.getInt("weight");
                _user = EntityFactory.createUser( id ,usuario, email, sex, phone, birthdate, height, weight);
            }
            return _user;
        } catch (NullPointerException e) {
            throw new GetUserException(e, DaoUser.class.getSimpleName(),BdConnectException.class.toString());
        } catch (SQLException e) {
            throw new GetUserException(e, DaoUser.class.getSimpleName(),BdConnectException.class.toString());
        }
        catch (Exception e) {
            throw new GetUserException(e, DaoUser.class.getSimpleName(),BdConnectException.class.toString());
        }
    }

    /**
     * Metodo que devuelve un dato de cumpleaños a traves de un query que devuelve una fecha
     * @param id
     * @return
     */
    public Date Fecha(int id) {

        try {
            Connection conn = getBdConnect();
            ResultSet result = sql("select * from m02_consultarperfilid(" + id + ")");
            while (result.next()) {
                birthdate = result.getDate("birthdate");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return birthdate;
    }

    /**
     * Metodo que crea una entidad
     * @param e
     * @return
     * @throws AddException
     */
    public Entity create(Entity e) throws AddException {
        return null;
    }

    /**
     * Metodo que crea una entidad
     * @param e
     * @return
     */
    public Entity read(Entity e) {
        return null;
    }

    /**
     * Metodo que actualiza una entidad
     * @param e
     * @return
     */
    public Entity update(Entity e) {
        return null;
    }

}
