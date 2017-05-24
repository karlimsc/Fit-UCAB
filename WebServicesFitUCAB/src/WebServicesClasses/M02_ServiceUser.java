package WebServicesClasses;

import Domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.Date;

/**
 * Clase del servicio web modulo 02
 */
@Path("/M02Users")
@Produces(MediaType.APPLICATION_JSON)
public class M02_ServiceUser {

    /**
     * Metodo para obtener un usuario
     * @param username Nombre de usuario que se va a obtener
     * @return Clase User en formato json
     * @see User
     */
    @GET
    @Path("/{username}")
    public User getUser( @PathParam("username") String username ){
        Date date = new Date(1L);
        return new User( 1, username, "password", "email", "sexo", "4241782944", date );
    }

    /**
     * Metodo para actualizar un usuario
     * @param username Nombre de usuario que se quiere modificar
     * @param user Datos del usuario modificado
     * @return Clase User en formato json
     * @see User
     */
    @PUT
    @Path("/{username}")
    @Consumes(MediaType.APPLICATION_JSON)
    public User updateUser( @PathParam("username") String username, User user ){
        user.setUser(username);
        return user;
    }

}
