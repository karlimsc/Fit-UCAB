package edu.ucab.desarrollo.fitucab.webService;


import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.StatusMessage;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    private Boolean _update;


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
        } catch (Exception e) {
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
    /**
     * Metodo que actualiza al usuario
     * @param id
     * @param username
     * @param phone
     * @param email
     * @return
     */
    @GET
    @Path("/userId")
    public Response updateUser(@QueryParam("id") String id,
                               @QueryParam("username") String username,
                               @QueryParam("email") String email,
                               @QueryParam("phone") String phone) throws ListByIdException, NoSuchMethodException, ListAllException {
        int _id = Integer.parseInt(id);
        Command comm = CommandsFactory.instanciateUpdateUserCmd(_id,username,phone,email);
        try {
            comm.execute();
            _update =comm.ReturnUpdate();


        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ListByIdException e) {
            e.printStackTrace();
        } catch (ListAllException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (_update == false ){
            _message = new StatusMessage( 0, "Usuario no actualizado" );
            _response = Response.status( Response.Status.NOT_MODIFIED ).entity( _message ).build();
            throw new WebApplicationException(_response);
        }
        _message = new StatusMessage( 1, "Usuario actualizado" );
        _response = Response.status( Response.Status.ACCEPTED ).entity( _message ).build();
        return _response;
    }


}