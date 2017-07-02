package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.GetUserException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.*;

import java.sql.*;
import java.util.Date;

/**
 * Clase DAO para el manejo de la Entidad User.
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 1.0
 */
public class DaoUser extends Dao implements IDaoUser {

    int _id;
    Entity _user;
    Date birthdate;

    /**
     * Metodo constructor que recibe un _id
     * @param _id
     */
    public DaoUser(int _id) {
        this._id = _id;
    }

    /**
     * Metodo que consulta el perfil de usuario por un query de un Stored Procedure
     * @param _id _id que identifica al Usuario a buscar en la BD
     * @throws GetUserException Exepcion personalizada del M02
     * @return _user entidad User
     */
    @Override
    public Entity read(int _id) throws GetUserException{
        try {
            getBdConnect();
            ResultSet _result = sql("select * from m02_consultarperfilid("+ _id +")");

            while(_result.next()){
                String _usuario = _result.getString( "usuario" );
                String _email = _result.getString( "email" );
                String _sex = _result.getString( "sex" );
                String _phone = _result.getString( "phone" );
                Date _birthdate = _result.getDate( "birthdate" );
                int  _height = _result.getInt( "height" );
                int _weight = _result.getInt("weight");
                _user = EntityFactory.createUser( _id ,_usuario, _email, _sex, _phone, _birthdate, _height, _weight);
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
     * @param _id _id que identifica al Usuario a buscar en la BD
     * @return birthdate
     */
    public Date Fecha(int _id) {

        try {
            getBdConnect();
            ResultSet result = sql("select * from m02_consultarperfilid(" + _id + ")");
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
     * @return null
     * @throws AddException
     */
    public Entity create(Entity e) throws AddException {
        return null;
    }

    /**
     * Metodo que crea una entidad
     * @param e
     * @return null
     */
    public Entity read(Entity e) {
        return null;
    }

    /**
     * Metodo que actualiza una entidad
     * @param e
     * @return null
     */
    public Entity update(Entity e) {
        return null;
    }

}
