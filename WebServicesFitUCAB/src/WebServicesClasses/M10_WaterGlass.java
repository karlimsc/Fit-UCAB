package WebServicesClasses;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * Clase para el manejo de la hidratacion
 */
@Path("/M10_WaterGlass")
public class M10_WaterGlass
{

    Gson gson = new Gson();
    String a[] = {"hola","chao"};



    @GET
    @Path("/a")
    @Produces("application/json")
    public String prueba()
    {
    return "hola";
    }



}
