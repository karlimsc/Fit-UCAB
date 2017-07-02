package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.GetUserException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    private Connection _conn;
    private GetUserException _error;
    private static org.slf4j.Logger _logger = LoggerFactory.getLogger(DaoUser.class);

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
    public Entity read(int _id) throws GetUserException {
        try {
            _conn = Dao.getBdConnect();
            Statement st = _conn.createStatement();
            ResultSet _result = st.executeQuery("select * from m02_consultarperfilid("+ _id +")");

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
            _error = new GetUserException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
            _logger.debug("Debug: ", _error.toString());
            _logger.error("Error: ", _error.toString());
            throw _error;
        } catch (SQLException e) {
            _error = new GetUserException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
            _logger.debug("Debug: ", _error.toString());
            _logger.error("Error: ", _error.toString());
            throw _error;
        }
        catch (Exception e) {
            _error = new GetUserException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
            _logger.debug("Debug: ", _error.toString());
            _logger.error("Error: ", _error.toString());
            throw _error;
        }finally {
            Dao.closeConnection();
        }
    }

    /**
     * Metodo que devuelve un dato de cumpleaños a traves de un query que devuelve una fecha
     * @param _id _id que identifica al Usuario a buscar en la BD
     * @return birthdate
     */
    public Date Fecha(int _id) {

        try {
            _conn = Dao.getBdConnect();
            Statement st = _conn.createStatement();
            ResultSet _result = st.executeQuery("select * from m02_consultarperfilid(" + _id + ")");
            while (_result.next()) {
                birthdate = _result.getDate("birthdate");
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            Dao.closeConnection();
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

    @Override
    public void Create(Entity e) {

    }
}
