package WebServicesClasses;

import Domain.User;
import com.google.gson.Gson;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;

/**
 * Clase del servicio web modulo 02
 */
@Path("/M02Users")
@Produces(MediaType.APPLICATION_JSON)
public class M02_ServiceUser {

    private Gson gson = new Gson();

    @GET
    @Path("/{username}")
    public User getUser( @PathParam("username") String username ){
        Date date = new Date(1L);
        return new User( 1, username, "password", "email", "sexo", "4241782944", date );
        /*User user = new User( 1, username, "password", "email", "sexo", "4241782944", date );
        return gson.toJson(user);*/
    }

    @PUT
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public User updateUser( @PathParam("username") String username, User user ){
        user.setUser(username);
        return user;
    }

}
