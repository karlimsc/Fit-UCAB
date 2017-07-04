package edu.ucab.desarrollo.fitucab.webService;

/**
 * Created by elberg on 28/06/17.
 */

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CheckUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CreateUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.RecoverPasswordCommand;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Clase de Servicios Web del Modulo 01
 */
@Path("/M01_ServicesUser")
public class M01_ServicesUser {
    private int RESULT_USER_FAIL=400;
    private static String DEFAULT_ENCODING1="UTF-8";

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(M01_ServicesUser.class);

    Gson gson = new Gson();

    public M01_ServicesUser() {}

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
            User result = (User) CheckUserCommand.getUserLogin();
            return gson.toJson(result);
        }
        catch (NullPointerException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.print("NULL POINTER");
            logger.error("Error: ", error);
            return e.getMessage();
        }
        catch (SQLException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.print("SQL");
            logger.error("Error: ", error);
            return e.getSQLState();
        }
        catch(Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.print("OTRA");
            logger.error("Error: ", error);
            return e.getMessage();
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
        System.out.print("DEBUG: " + username);

        User userFail = new User();

        //PARSEO de las Fechas.
        java.sql.Date sqlDate = null;
        Date initDate = new SimpleDateFormat("dd/MM/yyyy").parse(birthdate);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String parsedDate = formatter.format(initDate);




        try {
            Date date = formatter.parse(parsedDate);
            sqlDate = new java.sql.Date(date.getTime());

        }
        catch (java.text.ParseException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error);
            System.out.print("DEBUG: en la fecha"+error);
        }


        System.out.print("DEBUG: " + sqlDate.toString());

        try
        {
            Entity createUserObject = EntityFactory.createUser(0,username, password, email,
                    sex, phone, sqlDate, weight, height);

            User _returnUser = (User) createUserObject;

            Command _command = CommandsFactory.instanciateCreateUserCmd(createUserObject);

            CreateUserCommand cmd = (CreateUserCommand) _command;

            cmd.execute();

            //Obtiene el usuario registrado con su estatus
            User rUser = (User) cmd.getUserRegistry();

            _returnUser.setId(rUser.getId());

            if (cmd.get_response()) {
                return gson.toJson(rUser);
            }
            else{
                return gson.toJson(userFail);
            }

        }catch (NullPointerException e){

            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.print("NULL POINTER");
            logger.error("Error: ", error);
            userFail.set_status(Integer.toString(RESULT_USER_FAIL));
            return gson.toJson(userFail);
        }
        catch ( Exception e )
        { MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error);
            userFail.set_status(Integer.toString(RESULT_USER_FAIL));
            return gson.toJson(userFail);
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
            return _response;

        }catch (NullPointerException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            System.out.print("NULL POINTER");
            logger.error("Error: ", error);
            User userFail = new User();
            userFail.set_status(Integer.toString(RESULT_USER_FAIL));
            return gson.toJson(userFail);
        }
        catch ( Exception e )
        { MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error);
            User userFail = new User();
            userFail.set_status(Integer.toString(RESULT_USER_FAIL));
            return gson.toJson(userFail);
        }


    }

}
