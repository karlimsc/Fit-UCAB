package WebServicesClasses;

import Domain.User;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;

/**
 * Created by david on 5/23/17.
 */

/**
 * Clase del servicio web modulo 02
 */
@Path("/M02Users")
@Produces(MediaType.APPLICATION_JSON)
public class M02_ServiceUser {

    private Gson gson = new Gson();

    @GET
    @Path("/{username}")
    public String getUser(@PathParam("username") String username){
        Date date = new Date(1L);
        User user = new User( 1, username, "password", "email", "sexo", "4241782944", date );
        return gson.toJson(user);
    }

    @PUT
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateUser(@PathParam("username") String username){
        Date date = new Date(1L);
        User user = new User( 1, username, "password", "email", "sexo", "4241782944", date );
        return gson.toJson(user);
    }

}
