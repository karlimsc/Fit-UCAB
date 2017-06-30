package edu.ucab.desarrollo.fitucab.webService;

import edu.ucab.desarrollo.fitucab.common.entities.Challenge;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.FillChartCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.LevelUpCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.ScoreCommand;
import edu.ucab.desarrollo.fitucab.common.Exceptions.MessageException;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;;
import java.util.List;
import java.util.logging.Level;

/**
 * Clase M09ServicesGamification que maneja el modulo de gamificacion.
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
@Path("/M09_ServicesGamifications")
@Produces(MediaType.APPLICATION_JSON)
public class M09_ServicesGamification {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(M09_ServicesGamification.class);

    @GET
    @Path("/getChallenges/{userId}")
    public List<Challenge> getChallenges(@PathParam("userId") int id) throws NoSuchMethodException {
        try {
            AchieveChallengeCommand cmd = CommandsFactory.instanciateAchieveChallengeCmd(id);
            cmd.execute();
            List<Challenge> challenges = (List<Challenge>)(List<?>) cmd.getChallenges();
            return challenges;
        }
        catch (WebApplicationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
        }
        return null;
    }

    @GET
    @Path("/getAchievements/{userId}")
    public Challenge getAchievements(@PathParam("userId") int id) throws NoSuchMethodException {
        try {
            FillChartCommand cmd = CommandsFactory.instanciateFillChartCmd(id);
            cmd.execute();
            return (Challenge) cmd.getChallenge();
        }
        catch (WebApplicationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Error: ", error.toString());
            logger.error("Error: ", error.toString());
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Error: ", error.toString());
            logger.error("Error: ", error.toString());
        }
        return null;
    }

    @GET
    @Path("/getScores/{userId}")
    public Challenge getScores(@PathParam("userId") int id) throws NoSuchMethodException {
        try {
            ScoreCommand cmd = CommandsFactory.instanciateScoreCmd(id);
            cmd.execute();
            return (Challenge) cmd.getChallenge();
        }
        catch (WebApplicationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Error: ", error.toString());
            logger.error("Error: ", error.toString());
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Error: ", error.toString());
            logger.error("Error: ", error.toString());
        }
        return null;
    }

    @GET
    @Path("/checkLevels/{userId}")
    public Challenge getLevelUp(@PathParam("userId") int id) throws NoSuchMethodException {
        try {
            LevelUpCommand cmd = CommandsFactory.instanciateLevelUpCmd(id);
            cmd.execute();
            return (Challenge) cmd.getChallenge();
        }
        catch (WebApplicationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Error: ", error.toString());
            logger.error("Error: ", error.toString());
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Error: ", error.toString());
            logger.error("Error: ", error.toString());
        }
        return null;
    }
}