package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Home;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Clase DAO Home para el manejo del comando DAO de la entidad Home
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 3.0
 */
public class DaoHome extends Dao implements IDaoHome {

    private Home _home;
    Entity _usuario;
    int _totalAgua, _totalCalorias;
    private Connection _conn;
    private CreateHomeException _error;
    private static org.slf4j.Logger _logger = LoggerFactory.getLogger(DaoUser.class);
    Date _date = new Date();
    String _modifiedDate = new SimpleDateFormat("yyyy/MM/dd").format(_date);

    /**
     * Metodo constructor de clase DaoHome
     * @param _usuario
     */
    public DaoHome(Entity _usuario){
        this._usuario = _usuario;
    }

    /**
     * Metodo sobrecargado que lee una endidad y devuelve otra.
     * Metodo que realiza un query a un storeprocedure de busque de vasos
     * @param _usuario
     * @return _home entidad con datos de agua y calorias
     */
    @Override
    public Entity read(Entity _usuario) throws CreateHomeException {


        try {
            _conn = Dao.getBdConnect();
            Statement st = _conn.createStatement();
            ResultSet _result = st.executeQuery("SELECT countg FROM m10_getwaterglass("+_usuario.get_id()+
                    ",'"+_modifiedDate+"')");
            while (_result.next()){
                _totalAgua = _result.getInt("countg");
            }
            buscarCalorias(((User)_usuario).getUser());
            _home = EntityFactory.createHome(_totalCalorias, _totalAgua);
            return _home;
        } catch (BdConnectException e) {
            _error = new CreateHomeException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
            _logger.debug("Debug: ", _error.toString());
            _logger.error("Error: ", _error.toString());
            throw _error;
        } catch (SQLException e) {
            _error = new CreateHomeException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
            _logger.debug("Debug: ", _error.toString());
            _logger.error("Error: ", _error.toString());
            throw _error;
        } catch (Exception e){
            _error = new CreateHomeException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
            _logger.debug("Debug: ", _error.toString());
            _logger.error("Error: ", _error.toString());
            throw _error;
        }finally {
            Dao.closeConnection();
        }

    }


    /**
     * Metodo que busca las calorias por un query con un storeprocedures
     * @param _usuario
     */
    public void buscarCalorias(String _usuario) throws CreateHomeException {


        try {

            _conn = Dao.getBdConnect();
            Statement st = _conn.createStatement();
            ResultSet _result = st.executeQuery("SELECT calorias FROM m11_get_calorias_dia('"+_usuario+"')");
            while (_result.next()){
                _totalCalorias = _result.getInt("calorias");
            }
        } catch (BdConnectException e) {
            _error = new CreateHomeException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
            _logger.debug("Debug: ", _error.toString());
            _logger.error("Error: ", _error.toString());
            throw _error;
        } catch (SQLException e) {
            _error = new CreateHomeException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
            _logger.debug("Debug: ", _error.toString());
            _logger.error("Error: ", _error.toString());
            throw _error;
        }catch (Exception e){
            _error = new CreateHomeException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
            _logger.debug("Debug: ", _error.toString());
            _logger.error("Error: ", _error.toString());
            throw _error;
        }finally {
            Dao.closeConnection();
        }
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
     * Metodo que actualiza una entidad
     * @param e
     * @return
     */
    public Entity update(Entity e) {
        return null;
    }


}
