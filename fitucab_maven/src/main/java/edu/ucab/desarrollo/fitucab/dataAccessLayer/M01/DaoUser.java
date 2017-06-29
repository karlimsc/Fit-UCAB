package edu.ucab.desarrollo.fitucab.dataAccessLayer.M01;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Registry;
import edu.ucab.desarrollo.fitucab.common.entities.User;
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

    /**
     * Metodo que es llamado a traves del web service para agregar a la base de datos los parametros recibidos
     *
     * @return
     */

    public void Create(Entity e) throws Exception {

         _conn = new Sql();
        _bdCon = _conn.getConn();
        _sc = new Security();

        User _user = (User)e;

        String password= _sc.encryptPassword(_user.getPassword());
        CallableStatement cstmt;

        try {
            cstmt = _bdCon.prepareCall("{ call M01_REGISTRAR(?,?,?,?,?,?,?,?)}");
            cstmt.setString(1, _user.getUser());
            cstmt.setString(2, _user.getEmail());
            cstmt.setString(3, password);
            cstmt.setString(4, _user.getPhone());
            cstmt.setString(5, _user.getSex());
            cstmt.setInt(   6, _user.getHeight());
            cstmt.setDate(  8, _user.getBirthdate());
            cstmt.setInt(   8, _user.getWeight());
            cstmt.setDate(  8, _user.getBirthdate());
            cstmt.execute();
            System.out.printf("ENTREEEEEEEEEEE");

        }
        catch (SQLException ex) {
            Logger.getLogger(DaoUser.class.getName()).log(Level.SEVERE, null, ex);
        }



       /* String insertUserQuery = " SELECT * FROM M01_REGISTRAR('" + username + "','" + password + "','" + email + "','" + sex + "'" +
                ",'" + phone + "','" + birthdate + "','" + weight + "','" + height + "')";
        */
     /*   Entity createUserObject = EntityFactory.createUser(username,password,email, sex,phone, birthdate,weight,height);
        CreateUserCommand cmd = CommandsFactory.instanciateCreateUserCmd(createUserObject);


        try {

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(insertUserQuery);
            User user = null;
            Registry registry = null;
            Boolean valida = false;

            int id = 0;

            while (rs.next()) {

                valida=true;
                id = rs.getInt("m01_registrar");
                registry = new Registry(Float.parseFloat(weight), Float.parseFloat(height));

            }

            if(valida == true){
                user = new User(id, username, password, email, sex, phone, registry);
                user.set_status(Integer.toString(RESULT_CODE_OK));
            return gson.toJson(user);
            }
            else{

                userFail.set_status(Integer.toString(RESULT_CODE_FAIL));
                return gson.toJson(userFail);
            }
        }
        catch (SQLException e) {
            userFail.set_status(e.getSQLState());
            return gson.toJson(userFail);
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
*/
    }
}