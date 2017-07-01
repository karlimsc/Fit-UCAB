package edu.ucab.desarrollo.fitucab.dataAccessLayer.M01;

import com.google.gson.Gson;

import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Security;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import edu.ucab.desarrollo.fitucab.webService.Sql;
import org.slf4j.LoggerFactory;
import java.sql.*;




public class DaoUser  extends Dao implements IDaoUser {
    //Conexion con la base de datos
    private Sql        _conn;
    private Connection _bdCon;
    private Statement  _st;

    //Encargado de encriptar la contraseña
    private Security   _sc;

    //String de conexion funciones
    String _sqlInicioSesion="{?=call M01_INICIARSESION(?,?)}";
    String _sqlLastUser="{?=call M01_LASTUSER(?,?,?,?,?,?,?,?)}";

    //String de conexion procedimientos
    String _sqlRegistrarUsuario="{ call M01_REGISTRAR(?,?,?,?,?,?,?,?)}";



    Entity _user;

    Gson gson = new Gson();

    String _userLogin, _password;

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);



    public DaoUser(Entity _user)  {
         this._user= _user;
        try {
            _bdCon = Dao.getBdConnect();
        } catch (BdConnectException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
    }

    public DaoUser()  {
        try {
            _bdCon = Dao.getBdConnect();
        } catch (BdConnectException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
    }


    /**
     * Devuelve el usuario que este registrado
     * @param e
     * @return
     */

    public Entity read(Entity e) {

        _sc = new Security();

        CallableStatement cstmt;

        User _user = (User) e;

        String password =_sc.encryptPassword(_user.getPassword());

        try{
            cstmt = _bdCon.prepareCall(_sqlInicioSesion.toString());

            //1er signo de interrogacion el parametro de salida
            cstmt.registerOutParameter(1, Types.INTEGER);

            //2do y 3er signo de interrogacion parametros de entrada
            cstmt.setString(2, _user.getUser());
            cstmt.setString(3, password);

            cstmt.execute();

            int id = cstmt.getInt(1);
            System.out.printf(String.valueOf(id));

            _user.setId(id);
            return _user;
        }
        catch (Exception ex){

        return null;

        }
    }


    /**
     * Metodo que es llamado a traves del web service para agregar a la base de datos
     * los parametros recibidos
     * @return
     */

    @Override
    public Entity create(Entity e) throws Exception {

        _sc = new Security();

        User _user = (User)e;

        String password= _sc.encryptPassword(_user.getPassword());
        CallableStatement cstmt,cs;


        try {
            cstmt = _bdCon.prepareCall(_sqlRegistrarUsuario.toString());
            cstmt.setString(1, _user.getUser());
            cstmt.setString(2, password);
            cstmt.setString(3, _user.getEmail());
            cstmt.setString(4, _user.getSex());
            cstmt.setString(5, _user.getPhone());
            cstmt.setDate(6, _user.getBirthdate());
            cstmt.setInt(7, _user.getWeight());
            cstmt.setInt(8, _user.getHeight());
            cstmt.execute();

            //Metodo que busca el ultimo usuario registrado y toma la id de este
            cs = _bdCon.prepareCall(_sqlLastUser.toString());
            cs.registerOutParameter(1, Types.INTEGER);
            cs.execute();

            int id = cs.getInt(1);
            System.out.printf(String.valueOf(id));
            _user.setId(id);

            return _user;
        }
        catch (SQLException ex) {
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());

            //Retorna null por el error
            return null;
        }
        catch (Exception ex){
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
            return null;
        }
        finally {
            _bdCon.close();
        }

    }


    public Entity update(Entity e) {
        return null;
    }



}