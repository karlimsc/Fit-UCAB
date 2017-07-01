package edu.ucab.desarrollo.fitucab.webService;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.*;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * Clase del servicio web modulo 02
 */
@Path("/M02Homes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class M02_ServicesHome {

    private StatusMessage _message;
    private Response _response;
    private Entity home;


    /**
     * Metodo para actualizar contenido del home.
     * Buscar cantidad de vasos consumidos y calorias consumidas.
     * @return Clase Home en formato json
     * @see Home
     */
    @GET
    @Path("/{userId}")
    public Response getHome(@PathParam("userId") int id) {
        Command command = CommandsFactory.instanciateHomeCmd(id);
        try {
            command.execute();
            home = command.Return();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (ListAllException e) {
            e.printStackTrace();
        } catch (ListByIdException e) {
            e.printStackTrace();
        }

        if ( home == null ){
            _message = new StatusMessage( 0, "Home no actualizado" );
            _response = Response.status( Response.Status.NOT_FOUND ).entity( _message ).build();
            throw new WebApplicationException( _response );
        }
        _response = Response.status( Response.Status.ACCEPTED ).entity( home ).build();
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
     _home.setTotalAgua( rsW.getInt("countg") );
     System.out.println("Agua: "+ _home.getTotalAgua());
     }
     while (rsC.next()){
     _home.setTotalCaloria( rsC.getInt("calorias") );
     System.out.println("Calorias: "+ _home.getTotalCaloria());
     }
     _sql.closeConnection(_sql.getConn());
     System.out.println("Calorias: "+ _home.getTotalCaloria());
     System.out.println("Agua: "+ _home.getTotalAgua());
     return new Home( _home.getTotalCaloria(), _home.getTotalAgua() );
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
