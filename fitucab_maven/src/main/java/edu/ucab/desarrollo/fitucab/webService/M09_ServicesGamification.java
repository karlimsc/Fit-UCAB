package edu.ucab.desarrollo.fitucab.webService;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;;
import java.util.List;

/**
 * Clase M09ServicesGamification que maneja el modulo de gamificacion.
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
@Path("/M09_ServicesGamification")
@Produces(MediaType.APPLICATION_JSON)
public class M09_ServicesGamification {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(M09_ServicesGamification.class);

    @GET
    @Path("/obtenerretos")
    public List<Entity> getChallenges() throws NoSuchMethodException {
        try {
            AchieveChallengeCommand acc = CommandsFactory.instanciateAchieveChallengeCmd();
            acc.execute();
            List<Entity> challenges = acc.getChallenges();
            return challenges;
        }
        catch (WebApplicationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error);
            logger.error("Error: ", error);
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error);
            logger.error("Error: ", error);
        }
        return null;
    }

    @GET
    @Path("/obtenerlogrados")
    public String getCantidad() throws NoSuchMethodException {
        try {
            //Llamada a la fabrica de comandos.
        }
        catch (WebApplicationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Error: ", error);
            logger.error("Error: ", error);
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Error: ", error);
            logger.error("Error: ", error);
        }
        return null;
    }

    @GET
    @Path("/obtenernivel")
    public String getQuantity() throws NoSuchMethodException {
        try {
            //Llamada a la fabrica de comandos.
        }
        catch (WebApplicationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Error: ", error);
            logger.error("Error: ", error);
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Error: ", error);
            logger.error("Error: ", error);
        }
        return null;
    }

    @GET
    @Path("/obtenerverificarnivel")
    public String getLevelUp() throws NoSuchMethodException {
        try {
            //Llamada a la fabrica de comandos.
        }
        catch (WebApplicationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Error: ", error);
            logger.error("Error: ", error);
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Error: ", error);
            logger.error("Error: ", error);
        }
        return null;
    }
}