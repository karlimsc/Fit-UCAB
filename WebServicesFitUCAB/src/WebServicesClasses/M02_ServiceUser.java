package WebServicesClasses;

import Domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;

/**
 * Created by david on 5/23/17.
 */

@Path("/M02Users")
@Produces(MediaType.APPLICATION_JSON)
public class M02_ServiceUser {

    @GET
    public String test(){
        return "Hello World";
    }

    @GET
    @Path("/{username}")
    public User getUser(@PathParam("username") String username){
        Date date = new Date(1L);
        return new User( 1, username, "password", "email", "sexo", "4241782944", date );
    }

    @PUT
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public User updateUser(@PathParam("username") String username){
        Date date = new Date(1L);
        return new User( 1, username, "password", "email", "sexo", "4241782944", date );
    }

}
