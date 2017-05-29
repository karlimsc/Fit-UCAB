package WebServicesClasses;

import Domain.Query;
import Exceptions.StatusMessage;
import Domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;

/**
 * Clase que realiza las consultas
 */
@Path("/M02Users")
@Produces(MediaType.APPLICATION_JSON)
public class M02_ServicesUser {

    private StatusMessage _message;
    private Response _response;
    private Query _service;

    /**
     * Metodo para obtener un usuario.
     * Buscar el usuario con esa id.
     * @param id Id del usuario que se va a obtener
     * @return Clase User en formato json
     * @throws WebApplicationException
     * @see User
     */
    @GET
    @Path("/{userId}")
    public Response getUser( @PathParam("userId") int id ){
        _service = new Query();
        User user = _service.getUser( id );
        if ( user.getId() <= 0 ) {
            _message = new StatusMessage( 0, "Usuario no encontrado" );
            _response = Response.status( Response.Status.NOT_FOUND ).entity( _message ).build();
            throw new WebApplicationException( _response );
        }
        _response = Response.status( Response.Status.ACCEPTED ).entity( user ).build();
        return _response;
    }

    /**
     * Metodo para actualizar un usuario.
     * Actualizar usuario con esa id.
     * @param id Id del usuario que se quiere modificar
     * @param email
     * @param sex
     * @param phone
     * @param birthdate
     * @return Clase User en formato json
     * @throws WebApplicationException
     * @see User
     */
    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser( @PathParam("userId") int id,
                                @QueryParam("email") String email,
                                @QueryParam("sex") String sex,
                                @QueryParam("phone") String phone,
                                @QueryParam("birthdate") String birthdate ){
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        user.setSex(sex);
        user.setPhone(phone);
        user.setBirthdate(Date.valueOf(birthdate));
        _service = new Query();
        if ( _service.updateUser( user ) == false ){
            _message = new StatusMessage( 0, "Usuario no actualizado" );
            _response = Response.status( Response.Status.NOT_MODIFIED ).entity( _message ).build();
            throw new WebApplicationException(_response);
        }
        _message = new StatusMessage( 1, "Usuario actualizado" );
        _response = Response.status( Response.Status.ACCEPTED ).entity( _message ).build();
        return _response;
    }

}