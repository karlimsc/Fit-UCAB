package WebServicesClasses;

import Domain.Home;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Clase del servicio web modulo 02
 */
@Path("/M02Homes")
@Produces(MediaType.APPLICATION_JSON)
public class M02_ServicesHome {

    /**
     * Metodo para actualizar contenido del home
     * @return Clase Home en formato json
     * @see Home
     */
    @GET
    public Home getHome(){
        return new Home( 4, 5 );
    }

}
