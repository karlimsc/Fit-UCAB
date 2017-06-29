package edu.ucab.desarrollo.fitucab.webService;

/**
 * Created by elberg on 28/06/17.
 */

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CreateUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.CheckTrainingCommand;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.Connection;

import static edu.ucab.desarrollo.fitucab.webService.Sql.getConInstance;

/**
 * Clase de Servicios Web del Modulo 01
 */
@Path("/M01_ServicesUser")
public class M01_ServicesUser1 {
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
        return null ;
    }

/**
 * Metodo que es llamado a traves del web service para agregar a la base de datos los parametros recibidos
 *
 *
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
                             @QueryParam("birthdate") java.sql.Date birthdate,
                             @QueryParam("weight") int weight,
                             @QueryParam("height") int height) {


        Entity _user = EntityFactory.createUser(username,password,email,sex,phone,birthdate,weight,height);

        CreateUserCommand cmd  = CommandsFactory.instanciateCreateUserCmd(_user);


        try{
            cmd.execute();
            return gson.toJson(true);
        }
        catch (Exception e){
            return gson.toJson(false);
        }
    }

}
