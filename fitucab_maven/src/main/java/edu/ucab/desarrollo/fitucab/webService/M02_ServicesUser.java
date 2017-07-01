package edu.ucab.desarrollo.fitucab.webService;


import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * Clase que realiza las consultas
 */
@Path("/M02Users")
@Produces(MediaType.APPLICATION_JSON)
public class M02_ServicesUser {

    private StatusMessage _message;
    private Response _response;
    private Entity user;


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
        Command comm = CommandsFactory.instanciateUserCmd(id);
        try {
            comm.execute();
            user = comm.Return();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ListByIdException e) {
            e.printStackTrace();
        } catch (ListAllException e) {
            e.printStackTrace();
        }

        if ( user.get_id() <= 0 ) {
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