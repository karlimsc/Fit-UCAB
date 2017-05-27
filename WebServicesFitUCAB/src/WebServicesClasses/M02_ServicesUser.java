package WebServicesClasses;

import Domain.StatusMessage;
import Domain.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase del servicio web modulo 02
 */
@Path("/M02Users")
@Produces(MediaType.APPLICATION_JSON)
public class M02_ServicesUser {

    private StatusMessage _message;
    private Response _response;

    /**
     * Metodo para obtener un usuario.
     * Buscar el usuario con esa id.
     * @param id Id del usuario que se va a obtener
     * @return Clase User en formato json
     * @see User
     */
    @GET
    @Path("/{userId}")
    public Response getUser( @PathParam("userId") int id ){
        try {
            /*
            TODO implementar el searchUser() en la base de datos
            Buscar en la base de datos el usuario con esa id
            Entrada id
            Buscar usuario()
            Salida User o null
            */
            User user = new User( id );
            if ( user.getId() == 0 ) {
                _message = new StatusMessage( 0, "Usuario no encontrado" );
                _response = Response.status( Response.Status.NOT_FOUND ).entity( _message ).build();
                throw new WebApplicationException( _response );
            }
            _message = new StatusMessage( 1, "Usuario encontrado" );
            _response = Response.status( Response.Status.ACCEPTED ).entity( _message ).build();
            return _response;
        }
        catch (Exception e){
            _message = new StatusMessage( -1, e.getMessage() );
            _response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( _message ).build();
            return _response;
        }
    }

    /**
     * Metodo para actualizar un usuario.
     * Actualizar usuario con esa id.
     * @param id Id del usuario que se quiere modificar
     * @param user Datos del usuario modificado
     * @return Clase User en formato json
     * @see User
     */
    @PUT
    @Path("/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser( @PathParam("userId") int id, User user ){
        try {
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
            _message = new StatusMessage( 1, "Usuario actualizado" );
            _response = Response.status( Response.Status.ACCEPTED ).entity( _message ).build();
            return _response;
        }
        catch (Exception e){
            _message = new StatusMessage( -1, e.getMessage() );
            _response = Response.status( Response.Status.INTERNAL_SERVER_ERROR ).entity( _message ).build();
            return _response;
        }
    }

}