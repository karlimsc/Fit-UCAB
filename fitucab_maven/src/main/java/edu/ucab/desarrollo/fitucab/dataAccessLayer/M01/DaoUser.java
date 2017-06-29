package edu.ucab.desarrollo.fitucab.dataAccessLayer.M01;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Registry;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Security;
import edu.ucab.desarrollo.fitucab.webService.Sql;
import jdk.nashorn.internal.runtime.regexp.JoniRegExp;
import org.postgresql.core.SqlCommandType;
import sun.security.smartcardio.SunPCSC;

import javax.ws.rs.QueryParam;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import static edu.ucab.desarrollo.fitucab.webService.Sql.getConInstance;

/**
 * Created by karo on 24/06/17.
 */
public class DaoUser  extends Dao implements IDaoUser {

    private Sql        _conn;
    private Connection _bdCon;
    private Statement  _st;
    //Encargado de encriptar la contraseña
    private Security   _sc;
    Entity _user;
    Gson gson = new Gson();

    public DaoUser(Entity _user) {
    this._user= _user;
    }



    public Entity read(Entity e) {
        return null;
    }

    public Entity update(Entity e) {
        return null;
    }

    /**
     * Metodo que es llamado a traves del web service para agregar a la base de datos los parametros recibidos
     *
     * @return
     */

    public Entity create(Entity e) throws Exception {

         _conn = new Sql();
        _bdCon = _conn.getConn();
        _sc = new Security();

        User _user = (User)e;

        String password= _sc.encryptPassword(_user.getPassword());
        CallableStatement cstmt;


        try {
            cstmt = _bdCon.prepareCall("{ call M01_REGISTRAR(?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, _user.getUser());
            cstmt.setString(2, password);
            cstmt.setString(3, _user.getEmail());
            cstmt.setString(4, _user.getSex());
            cstmt.setString(5, _user.getPhone());
            cstmt.setDate(6, _user.getBirthdate());
            cstmt.setInt(7, _user.getWeight());
            cstmt.setInt(8, _user.getHeight());
            cstmt.execute();
            System.out.printf("ENTREEEEEEEEEEE");



        }
        catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;  //POR LOS MOMENTOS RETORNARA NULL
    }
}