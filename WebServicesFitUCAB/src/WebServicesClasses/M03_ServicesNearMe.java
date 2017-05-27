package WebServicesClasses;

import Domain.Sql;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Created by andres on 13/05/17.
 */
@Path("/nearMe")
public class M03_ServicesNearMe {

    Gson gson = new Gson();
    Sql base = new Sql();
    @GET
    @Produces("application/json")
    public String nearMe(@QueryParam("id") String id){



    return gson.toJson("s");
    }




}
