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
     * Metodo para actualizar contenido del home.
     * Buscar cantidad de vasos consumidos y calorias consumidas.
     * @return Clase Home en formato json
     * @see Home
     */
    @GET
    public Home getHome() {
        /*
         Sin entrega
         Buscar los datos
         Salida Home
         */
        return new Home(4, 5);
    }

}
