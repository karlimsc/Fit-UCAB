package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.*;

import java.sql.*;

/**
 * Created by root on 29/06/17.
 */
public class DaoHome extends Dao implements IDaoHome {
    private Home _home;
    Entity _usuario;
    int totalAgua, totalCalorias;

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
    public Entity read(Entity user) throws CreateHomeException  {


        try {
            getBdConnect();
            ResultSet rsW = sql("SELECT countg FROM m10_getwaterglass("+_usuario.get_id()+"," +
                    "'"+((User)_usuario).getBirthdate()+"')");
            while (rsW.next()){
                totalAgua = rsW.getInt("countg");
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


        ResultSet rsC = null;
        try {

            getBdConnect();
            rsC = sql("SELECT calorias FROM m11_get_calorias_dia('"+usuario+"')");
            while (rsC.next()){
                totalCalorias = rsC.getInt("calorias");
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
}
