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
public class M02_ServicesUser {

    /**
     * Metodo para obtener un usuario
     * @param id Id del usuario que se va a obtener
     * @return Clase User en formato json
     * @see User
     */
    @GET
    @Path("/{userId}")
    public User getUser( @PathParam("userId") int id ){
        Date date = new Date(1L);
        return new User( id, "username", "password", "email", "sexo", "4241782944", date );
    }

    /**
     * Metodo para actualizar un usuario
     * @param id Id del usuario que se quiere modificar
     * @param user Datos del usuario modificado
     * @return Clase User en formato json
     * @see User
     */
    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public User updateUser( @PathParam("userId") int id, User user ){
        user.setId(id);
        return user;
    }

}
