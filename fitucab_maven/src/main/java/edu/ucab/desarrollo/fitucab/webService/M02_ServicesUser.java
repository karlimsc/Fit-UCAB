package edu.ucab.desarrollo.fitucab.webService;


import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * Clase del servicio web del M02 que maneja la Entidad User
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 5.0
 */
@Path("/M02Users")
@Produces(MediaType.APPLICATION_JSON)
public class M02_ServicesUser {

    private StatusMessage _message;
    private Response _response;
    private Entity _user;


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
        Command _comm = CommandsFactory.instanciateUserCmd(id);
        try {
            _comm.execute();
            _user = _comm.Return();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ListByIdException e) {
            e.printStackTrace();
        } catch (ListAllException e) {
            e.printStackTrace();
        }

        if ( _user.get_id() <= 0 ) {
            _message = new StatusMessage( 0, "Usuario no encontrado" );
            _response = Response.status( Response.Status.NOT_FOUND ).entity( _message ).build();
            throw new WebApplicationException( _response );
        }
        _response = Response.status( Response.Status.ACCEPTED ).entity( _user ).build();
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
        /*
        user.setId(id);
        if ( _service.updateUser( user ) == false ){
            _message = new StatusMessage( 0, "Usuario no actualizado" );
            _response = Response.status( Response.Status.NOT_MODIFIED ).entity( _message ).build();
            throw new WebApplicationException(_response);
        }
        _message = new StatusMessage( 1, "Usuario actualizado" );
        _response = Response.status( Response.Status.ACCEPTED ).entity( _message ).build();
        return _response;
        */
        return null;
    }


}