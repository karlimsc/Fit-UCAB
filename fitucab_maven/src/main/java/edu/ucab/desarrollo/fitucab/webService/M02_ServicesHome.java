package edu.ucab.desarrollo.fitucab.webService;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Home;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.exceptions.StatusMessage;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Clase del servicio web modulo 02
 */
@Path("/M02Homes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class M02_ServicesHome {

    private StatusMessage _message;
    private Response _response;
    private Entity _home;


    /**
     * Metodo para actualizar contenido del _home.
     * Buscar cantidad de vasos consumidos y calorias consumidas.
     * @return Clase Home en formato json
     * @see Home
     */
    @GET
    @Path("/{userId}")
    public Response getHome(@PathParam("userId") int _id) {
        Command _command = CommandsFactory.instanciateHomeCmd(_id);
        try {
            _command.execute();
            _home = _command.Return();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ListAllException e) {
            e.printStackTrace();
        } catch (ListByIdException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if ( _home == null ){
            _message = new StatusMessage( 0, "Home no actualizado" );
            _response = Response.status( Response.Status.NOT_FOUND ).entity( _message ).build();
            throw new WebApplicationException( _response );
        }
        _response = Response.status( Response.Status.ACCEPTED ).entity(_home).build();
        return _response;
    }


    /**  public Home getHome( int id ) {
     try {
     _sql = new Sql();
     User user = getUser(id);
     ResultSet rsW = _sql.sqlConn("SELECT countg FROM m10_getwaterglass("+user.getId()+"," +
     "'"+user.getBirthdate()+"')");
     ResultSet rsC = _sql.sqlConn("SELECT calorias FROM m11_get_calorias_dia('"+user.getUser()+"')");
     while (rsW.next()){
     _home.setTotalWater( rsW.getInt("countg") );
     System.out.println("Agua: "+ _home.getTotalWater());
     }
     while (rsC.next()){
     _home.setTotalCalories( rsC.getInt("calorias") );
     System.out.println("Calorias: "+ _home.getTotalCalories());
     }
     _sql.closeConnection(_sql.getConn());
     System.out.println("Calorias: "+ _home.getTotalCalories());
     System.out.println("Agua: "+ _home.getTotalWater());
     return new Home( _home.getTotalCalories(), _home.getTotalWater() );
     } catch (SQLException e) {
     e.printStackTrace();
     return null;
     }
     catch (Exception e){
     e.printStackTrace();
     return null;
     }
     }**/

}
