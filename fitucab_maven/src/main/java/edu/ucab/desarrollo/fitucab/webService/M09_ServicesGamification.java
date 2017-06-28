package edu.ucab.desarrollo.fitucab.webService;

import edu.ucab.desarrollo.fitucab.common.entities.Challenge;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import edu.ucab.desarrollo.fitucab.exception.M09Exception;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;;import java.util.ArrayList;
import java.util.List;

/**
 * Clase M09ServicesGamification que maneja el modulo de gamificacion.
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
@Path("/M09_ServicesGamification")
@Produces("application/json")
public class M09_ServicesGamification {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(M09_ServicesGamification.class);

    @GET
    @Path("/obtenerretos")
    public List<Entity> getChallenges(@QueryParam("id") int _id){
        try {
            List<Entity> challenges = EntityFactory.getChallenges();
            AchieveChallengeCommand acc = CommandsFactory.instanciateAchieveChallengeCmd(challenges);
            Entity active = EntityFactory.createActive(acc);
            active.exec();
            return acc.getChallenges();
        }
        catch (WebApplicationException e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.debug("Debug: ", error);
            logger.error("Error: ", error);
        }
        catch (Exception e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.debug("Debug: ", error);
            logger.error("Error: ", error);
        }
        return null;
    }

    @GET
    @Path("/obtenerlogrados")
    public String getCantidad(@QueryParam("id") int _id) {
        try {
            //Llamada a la fabrica de comandos.
        }
        catch (WebApplicationException e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.debug("Error: ", error);
            logger.error("Error: ", error);
        }
        catch (Exception e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.debug("Error: ", error);
            logger.error("Error: ", error);
        }
        return null;
    }

    @GET
    @Path("/obtenernivel")
    public String getQuantity(@QueryParam("id") int _id) {
        try {
            //Llamada a la fabrica de comandos.
        }
        catch (WebApplicationException e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.debug("Error: ", error);
            logger.error("Error: ", error);
        }
        catch (Exception e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.debug("Error: ", error);
            logger.error("Error: ", error);
        }
        return null;
    }

    @GET
    @Path("/obtenerverificarnivel")
    public String getLevelUp(@QueryParam("_plus") int _plus, @QueryParam("id") int _id) {
        try {
            //Llamada a la fabrica de comandos.
        }
        catch (WebApplicationException e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.debug("Error: ", error);
            logger.error("Error: ", error);
        }
        catch (Exception e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.debug("Error: ", error);
            logger.error("Error: ", error);
        }
        return null;
    }
}