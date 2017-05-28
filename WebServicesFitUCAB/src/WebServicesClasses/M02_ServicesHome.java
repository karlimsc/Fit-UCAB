package WebServicesClasses;

import Domain.Home;
import Domain.Query;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

/**
 * Clase del servicio web modulo 02
 */
@Path("/M02Homes")
@Produces(MediaType.APPLICATION_JSON)
public class M02_ServicesHome {

    private Response _response;
    private Query _service;

    /**
     * Metodo para actualizar contenido del home.
     * Buscar cantidad de vasos consumidos y calorias consumidas.
     * @return Clase Home en formato json
     * @see Home
     */
    @GET
    public Response getHome() {
        _service = new Query();
        Home home = _service.getHome();
        if ( home == null ){

        }
        _response = Response.status( Response.Status.ACCEPTED ).entity( home ).build();
        return _response;
    }

}
