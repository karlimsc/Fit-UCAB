package edu.ucab.desarrollo.fitucab.webService;

import edu.ucab.desarrollo.fitucab.exception.M09Exception;

import javax.ws.rs.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase M09ServicesGamification que maneja el modulo de gamificacion.
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
@Path("/M09_ServicesGamification")
public class M09_ServicesGamification {

    private static Logger logger = Logger.getLogger("M09_ServicesGamification.class");

    @GET
    @Path("/obtenerretos")
    @Produces("application/json")
    public String getChallenges(@QueryParam("id") int _id){
        try {
            return "getChallenges";
        }
        catch (WebApplicationException e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.log(Level.ALL, "ChallengesWebAppExceltion!",error);
        }
        catch (Exception e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.log(Level.ALL, "ChallengesExceltion!",error);
        }
        finally {
            return null;
        }
    }

    @GET
    @Path("/obtenerlogrados")
    @Produces("application/json")
    public String getCantidad(@QueryParam("id") int _id) {
        try {
            return "getCantidad";
        }
        catch (WebApplicationException e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.log(Level.ALL, "CantidadWebAppExceltion!",error);
        }
        catch (Exception e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.log(Level.ALL, "CantidadExceltion!",error);
        }
        finally {
            return null;
        }
    }

    @GET
    @Path("/obtenernivel")
    @Produces("application/json")
    public String getQuantity(@QueryParam("id") int _id) {
        try {
            return "getQuantity";
        }
        catch (WebApplicationException e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.log(Level.ALL, "QuantityWebAppExceltion!",error);
        }
        catch (Exception e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.log(Level.ALL, "QuantityExceltion!",error);
        }
        finally {
            return null;
        }
    }

    @GET
    @Path("/obtenerverificarnivel")
    @Produces("application/json")
    public String getLevelUp(@QueryParam("_plus") int _plus, @QueryParam("id") int _id) {
        try {
            return "getLevelUp";
        }
        catch (WebApplicationException e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.log(Level.ALL, "LevelUpWebAppExceltion!",error);
        }
        catch (Exception e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.log(Level.ALL, "LevelUpExceltion!",error);
        }
        finally {
            return null;
        }
    }
}