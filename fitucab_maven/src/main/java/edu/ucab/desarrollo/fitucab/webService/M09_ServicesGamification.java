package edu.ucab.desarrollo.fitucab.webService;

import edu.ucab.desarrollo.fitucab.common.entities.Challenge;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.FillChartCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.LevelUpCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.ScoreCommand;
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

    /**
     * Metodo que retorna los retos logrados por un usuario
     * @param id Id del usuario
     * @return Lista con todos los retos logrados
     * @throws NoSuchMethodException
     * @throws WebApplicationException
     * @throws Exception
     * @see Challenge
     * @see List
     */
    @GET
    @Path("/getChallenges/{userId}")
    public List<Challenge> getChallenges(@PathParam("userId") int id) throws NoSuchMethodException {
        try {
            Dao dao = DaoFactory.instanceDaoGaming();
            Command cmd = CommandsFactory.instanciateAchieveChallengeCmd(id, dao);
            cmd.execute();
            List<Challenge> challenges = (List<Challenge>)(List<?>) AchieveChallengeCommand.getChallenges();
            return challenges;
        }
        catch (WebApplicationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
        return null;
    }

    /**
     * Metodo que retorna la cantidad de retos logrados y no logrados para llenar la grafica en android
     * @param id Id del usuario.
     * @return Clase challenge con los retos logrados y no logrados.
     * @throws NoSuchMethodException
     * @throws WebApplicationException
     * @throws Exception
     * @see Challenge
     */
    @GET
    @Path("/getAchievements/{userId}")
    public Challenge getAchievements(@PathParam("userId") int id) throws NoSuchMethodException {
        try {
            Dao dao = DaoFactory.instanceDaoGaming();
            Command cmd = CommandsFactory.instanciateFillChartCmd(id, dao);
            cmd.execute();
            return (Challenge) FillChartCommand.getChallenge();
        }
        catch (WebApplicationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
        return null;
    }

    /**
     * Metodo que pone tu record en un nivel.
     * @param id Id del usuario.
     * @return Clase challenge con el nivel alcanzado.
     * @throws NoSuchMethodException
     * @throws WebApplicationException
     * @throws Exception
     * @see Challenge
     */
    @GET
    @Path("/getScores/{userId}")
    public Challenge getScores(@PathParam("userId") int id) throws NoSuchMethodException {
        try {
            Dao dao = DaoFactory.instanceDaoGaming();
            Command cmd = CommandsFactory.instanciateScoreCmd(id, dao);
            cmd.execute();
            return (Challenge) ScoreCommand.getChallenge();
        }
        catch (WebApplicationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
        return null;
    }

    /**
     * Metodo que pone dice el nivel que se aumento.
     * @param id Id del usuario.
     * @return Clase challenge con el nivel alcanzado.
     * @throws NoSuchMethodException
     * @throws WebApplicationException
     * @throws Exception
     * @see Challenge
     */
    @GET
    @Path("/checkLevels/{userId}")
    public Challenge getLevelUp(@PathParam("userId") int id) throws NoSuchMethodException {
        try {
            Dao dao = DaoFactory.instanceDaoGaming();
            Command cmd = CommandsFactory.instanciateLevelUpCmd(id, dao);
            cmd.execute();
            return (Challenge) LevelUpCommand.getChallenge();
        }
        catch (WebApplicationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
        return null;
    }
}