package edu.ucab.desarrollo.fitucab.webService;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M03.*;
import org.slf4j.LoggerFactory;
import javax.ws.rs.*;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;

/**

 * Clase M03_ServicesNearMe que maneja el modulo 3 (ubicación).
 * @author Daniel Da Silva, Luis Martinez, Anderson Gomez
 * @version 2.0

 */

@Path("/nearMe")

public class M03_ServicesNearMe {
    Gson _gson = new Gson();
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(M03_ServicesNearMe.class);

    @GET
    @Produces("application/json")
    public String nearMe(@QueryParam("id") String id,@QueryParam("longitud") String longitud,@QueryParam("latitud") String latitud,@QueryParam("rango") String rango){

        try
        {
            NearMeCommand cmd = CommandsFactory.instatiateNearMeCmd(id, longitud, latitud, rango);
            cmd.execute();
            return cmd.returned;
        }
        catch ( Exception e )
        {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
        }

        return null;
    }

    @PUT
    @Path("/setLocation")
    @Produces("application/json")
    public String setLocation(@QueryParam("id") String id,@QueryParam("longitud") String longitud,@QueryParam("latitud") String latitud) {
        try{
            Dao dao = DaoFactory.instanceDaoNearMe();
            Command cmd = CommandsFactory.instanciateLocationCmd(dao, id, longitud, latitud);
            cmd.execute();
        }
        catch(WebApplicationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());

        }
        catch(Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
        }

        return null;
    }


}


