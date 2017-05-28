package WebServicesClasses;

import Domain.M02Query;
import Domain.StatusMessage;
import Domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.Date;

/**
 * Clase del servicio web modulo 02
 */
@Path("/M02Users")
@Produces(MediaType.APPLICATION_JSON)
public class M02_ServicesUser {

    private StatusMessage _message;
    private Response _response;
    private M02Query _service;

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
        Date date = new Date(1L);
        User user = new User(id,"user","pass","mail","sex","phone", date);
        user.setPassword("");
        user.setWeight(5);
        user.setHeight(6);
        /*_service = new M02Query();
        User user = _service.getUser( id );
        if ( user == null ) {
            _message = new StatusMessage( 0, "Usuario no encontrado" );
            _response = Response.status( Response.Status.NOT_FOUND ).entity( _message ).build();
            throw new WebApplicationException( _response );
        }*/
        _response = Response.status( Response.Status.ACCEPTED ).entity( user ).build();
        return _response;
    }

    /**
     * Metodo para actualizar un usuario.
     * Actualizar usuario con esa id.
     * @param id Id del usuario que se quiere modificar
     * @param user Datos del usuario modificado
     * @return Clase User en formato json
     * @throws WebApplicationException
     * @see User
     */
    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser( @PathParam("userId") int id, User user ){
        user.setId(id);
        /*
        * TODO implementar updateUser en la BD
        * Actualizar usuario con esa id
        * Entrada User
        * Actualizar usuario
        * Salida True o False
        * */
        if ( user == null ){ // TODO Cambiar condicion
            _message = new StatusMessage( 0, "Usuario no actualizado" );
            _response = Response.status( Response.Status.NOT_MODIFIED ).entity( _message ).build();
            throw new WebApplicationException(_response);
        }
        _response = Response.status( Response.Status.ACCEPTED ).entity( user ).build();
        return _response;
    }

}