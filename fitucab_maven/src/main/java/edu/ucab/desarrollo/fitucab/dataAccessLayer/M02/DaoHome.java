package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.*;

import java.sql.*;

/**
 * Created by root on 29/06/17.
 */
public class DaoHome extends Dao implements IDaoHome {
    private Home _home;
    Entity _usuario;
    int totalAgua, totalCalorias;

    public DaoHome(Entity usuario){
        this._usuario = usuario;
    }


    @Override
    public Entity read(Entity user){


        try {
            Connection conn = getBdConnect();
            ResultSet rsW = sql("SELECT countg FROM m10_getwaterglass("+_usuario.get_id()+"," +
                    "'"+((User)_usuario).getBirthdate()+"')");
            while (rsW.next()){
                totalAgua = rsW.getInt("countg");
            }
            buscarCalorias(((User)_usuario).getUser());
            _home = EntityFactory.createHome(totalAgua, totalCalorias);
            return _home;
        } catch (BdConnectException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void buscarCalorias(String usuario){

        ResultSet rsC = null;
        try {
            rsC = sql("SELECT calorias FROM m11_get_calorias_dia('"+usuario+"')");
            while (rsC.next()){
                totalCalorias = rsC.getInt("calorias");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Entity create(Entity e) throws AddException {
        return null;
    }



    public Entity update(Entity e) {
        return null;
    }
}
