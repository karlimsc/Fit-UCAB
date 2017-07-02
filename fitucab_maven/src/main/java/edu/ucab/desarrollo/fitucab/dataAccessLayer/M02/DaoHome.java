package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Home;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by root on 29/06/17.
 */
public class DaoHome extends Dao implements IDaoHome {
    private Home _home;
    Entity _usuario;
    int totalAgua, totalCalorias;
    private Connection _conn;

    /**
     * Metodo constructor de clase DaoHome
     * @param usuario
     */
    public DaoHome(Entity usuario){
        this._usuario = usuario;
    }

    /**
     * Metodo sobrecargado que lee una endidad y devuelve otra.
     * Metodo que realiza un query a un storeprocedure de busque de vasos
     * @param user
     * @return
     */
    @Override
    public Entity read(Entity user) throws CreateHomeException {


        try {
            _conn = Dao.getBdConnect();
            Statement st = _conn.createStatement();
            ResultSet _result = st.executeQuery("SELECT countg FROM m10_getwaterglass("+_usuario.get_id()+"," +
                    "'"+((User)_usuario).getBirthdate()+"')");
            while (_result.next()){
                totalAgua = _result.getInt("countg");
            }
            buscarCalorias(((User)_usuario).getUser());
            _home = EntityFactory.createHome(totalAgua, totalCalorias);
            return _home;
        } catch (BdConnectException e) {
            throw new CreateHomeException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
        } catch (SQLException e) {
            throw new CreateHomeException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
        } catch (Exception e){
            throw new CreateHomeException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
        }
    }


    /**
     * Metodo que busca las calorias por un query con un storeprocedures
     * @param usuario
     */
    public void buscarCalorias(String usuario) throws CreateHomeException {


        try {

            _conn = Dao.getBdConnect();
            Statement st = _conn.createStatement();
            ResultSet _result = st.executeQuery("SELECT calorias FROM m11_get_calorias_dia('"+usuario+"')");
            while (_result.next()){
                totalCalorias = _result.getInt("calorias");
            }
        } catch (BdConnectException e) {
            throw new CreateHomeException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
        } catch (SQLException e) {
            throw new CreateHomeException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
        }catch (Exception e){
            throw new CreateHomeException(e, DaoHome.class.getSimpleName(),BdConnectException.class.toString());
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

    @Override
    public void Create(Entity e) {

    }
}
