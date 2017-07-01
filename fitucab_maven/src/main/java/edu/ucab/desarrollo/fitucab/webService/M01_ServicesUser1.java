package edu.ucab.desarrollo.fitucab.webService;

/**
 * Created by elberg on 28/06/17.
 */

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Security;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CheckUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CreateUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.RecoverPasswordCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Clase de Servicios Web del Modulo 01
 */
@Path("/M01_ServicesUser1")
public class M01_ServicesUser1 {
    Entity _response;

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(M01_ServicesUser1.class);

    Gson gson = new Gson();

    public M01_ServicesUser1() {}

    /**
     * Metodo que es llamado a traves del web service para consultar un usuario
     * existente en la base de datos
     * @return el usuario con los datos que trae la consulta
     */
    @GET
    @Path("/login_user")
    @Produces("application/json")
    public String getUser(@QueryParam("username") String username,
                          @QueryParam("password") String password)
    {


        try {
            Entity userObject = EntityFactory.createUser(username,password);
            Command _command = CommandsFactory.instanciateCheckUserCmd(userObject);
            CheckUserCommand cmd = (CheckUserCommand) _command;
            cmd.execute();
            //User result = (User) CheckUserCommand.getUserLogin();
            return gson.toJson(CheckUserCommand.getUserLogin());
        }
        catch (Exception e){
            return null ;
        }

    }


    /**
     * Metodo para registrar al usuario
     * @param username
     * @param password
     * @param email
     * @param sex
     * @param phone
     * @param birthdate
     * @param weight
     * @param height
     * @return
     */

    @GET
    @Path("/insertRegistry")
    @Produces("application/json")
    public String insertUser(@QueryParam("username") String username,
                             @QueryParam("password") String password,
                             @QueryParam("email") String email,
                             @QueryParam("sex") String sex,
                             @QueryParam("phone") String phone,
                             @QueryParam("birthdate") String birthdate,
                             @QueryParam("weight") int weight,
                             @QueryParam("height") int height) throws NullPointerException,
                                                                      java.text.ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        java.sql.Date sqlDate = null;

        try {

            date = sdf.parse(birthdate);
            sqlDate = new java.sql.Date(date.getTime());

        }
        catch (java.text.ParseException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error);
        }

        try
        {
            Entity createUserObject = EntityFactory.createUser(0,username, password, email,
                    sex, phone, sqlDate, weight, height);

            User _returnUser = (User) createUserObject;

            Command _command = CommandsFactory.instanciateCreateUserCmd(createUserObject);
            CreateUserCommand cmd = (CreateUserCommand) _command;
            cmd.execute();

            //Obtiene el usuario registrado
            User rUser = (User) cmd.getUserRegistry();
            _returnUser.setId(rUser.getId());
            if (cmd.get_response()) {
                logger.debug("Debug: ","Boolean de CommandCreateUser TRUE");
                System.out.print("Debug Boolean de CommandCreateUser TRUE");
                System.out.print("Debug Boolean de CommandCreateUser TRUE " + _returnUser.getUser());
                //return gson.toJson(_returnUser);
                return gson.toJson(rUser);
            }
            else
                return gson.toJson(null);

        }catch (NullPointerException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.print("NULL POINTER");
            logger.error("Error: ", error);
            return gson.toJson(null);
        }
        catch ( Exception e )
        { MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error);
            return gson.toJson( null );
        }

    }

    /***
     * Metodo que con el email recuperas tu usuario y contraseña
     * @param email
     * @return
     */
    @GET
    @Path("/userView")
    @Produces("application/json")
    public String userOnly(@QueryParam("email") String email)
    {
        //Duda sobre si es necesario crear el objeto recupUserPswObject

        Command _command = CommandsFactory.instanciateRecoverPasswordCmd(email);
        RecoverPasswordCommand cmd = (RecoverPasswordCommand) _command;

        try
        {
            cmd.execute();
            return gson.toJson( true );
        }
        catch ( Exception e )
        {
            return gson.toJson( false );
        }
    }

    @GET
    @Path("/restorePassword")
    @Produces("application/json")
    public String testEmail (@QueryParam("email") String email){

        try
        {
            Command _command = CommandsFactory.instanciateRecoverPasswordCmd(email);
            RecoverPasswordCommand cmd = (RecoverPasswordCommand) _command;
            System.out.print("Debug: email " + email);
            cmd.execute();
            String _response = cmd.get_response();
            return gson.toJson(_response);

        }catch (NullPointerException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.print("NULL POINTER");
            logger.error("Error: ", error);
            return gson.toJson(null);
        }
        catch ( Exception e )
        { MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error);
            return gson.toJson( null );
        }


    }

}
