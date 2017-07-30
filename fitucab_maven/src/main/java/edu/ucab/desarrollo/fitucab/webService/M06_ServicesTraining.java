package edu.ucab.desarrollo.fitucab.webService;

import javax.ws.rs.*;
import com.google.gson.Gson;

import edu.ucab.desarrollo.fitucab.common.*;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;

import edu.ucab.desarrollo.fitucab.common.exceptions.*;

import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.*;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import java.security.InvalidParameterException;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


//AGREGAR USUARIOS


@Path( "/M06_ServicesTraining" )
public class M06_ServicesTraining
{

    private static Logger logger = LoggerFactory.getLogger( M06_ServicesTraining.class );


    //final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);

    Gson gson = new Gson();


    /**
     * Web service para crear un entrenamiento
     * @param name
     * @param userId
     * @return
     */
    @GET
    @Path( "/createTraining" )
    @Produces( "application/json" )
    public String createTraining(@QueryParam( "trainingName" ) String name,
                                 @QueryParam( "userId" ) int userId )
    {

        Entity training = null,
        commandResult = null;
        Command command = null;
        String response = null;

        try
        {
            training = EntityFactory.createTraining( name, userId );
            command = CommandsFactory.instanciateCreateTrainingCmd( training );
            command.execute();

            commandResult = ((CreateTrainingCommand) command).getResult();
            commandResult.set_errorCode(Registry.RESULT_CODE_OK);
            response = gson.toJson( commandResult );

        }
        catch ( AddException e )
        {
            commandResult.set_errorCode( e.ERROR_CODE );
            commandResult.set_errorMsg( e.ERROR_MSG );
            response = gson.toJson( commandResult );

            logger.error( "Metodo: {} {}", "createTraining", e.toString() );
        }
        catch( Exception e )
        {
            commandResult.set_errorCode( Registry.RESULT_CODE_FAIL );
            commandResult.set_errorMsg( Registry.RESULT_CODE_FAIL_MSG );
            response = gson.toJson( commandResult );

            logger.error( "Metodo: {} {}", "createTraining", e.toString() );
        }

        return response;
    }

    /**
     * web service para visualizar los entrenamientos que posee el usuario
     * @param trainingId
     * @return
     */
    @GET
    @Path( "/displayTraining" )
    @Produces( "application/json" )
    public String getTraining( @QueryParam( "userId" ) int userId,
            @QueryParam( "trainingId" ) int trainingId )
    {

        //    String query = "SELECT * from M06_obtenerEntrenamientos("+ userId +")";
        CheckTrainingCommand cmd = CommandsFactory.instanciateCheckTrainingCmd( trainingId, userId );


        try
        {
            cmd.execute();
            Entity returnedTraining = cmd.returnedTraining;
            return gson.toJson( true );
        }
        catch ( Exception e )
        {
            return gson.toJson( false );
        }
    }

    /**
     * web service para eliminar un entrenamiento
     * @param trainingId
     * @param trainingName
     * @return
     */
    @GET
    @Path( "/deleteTraining" )
    @Produces( "application/json" )
    public String deleteTraining( @QueryParam( "trainingId" ) int trainingId,
                                  @QueryParam( "trainingName" ) String trainingName )
    {

        Entity deleteTrainingObject = EntityFactory.createTraining(trainingId, trainingName);
        deleteTrainingObject.set_id(trainingId);
        DeleteTrainingCommand cmd = CommandsFactory.instanciateDeleteTrainingCmd(deleteTrainingObject);


        try
        {
            cmd.execute();
            Entity ok = EntityFactory.createEntity();
            ok.set_errorMsg("OK");
            ok.set_errorCode(200);
            return gson.toJson( ok );
        }
        catch ( DeleteException e )
        {
            MessageException error_ = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error_.toString());
            logger.error(error_.toString());
            Entity error = EntityFactory.createEntity();
            error.set_errorMsg(e.ERROR_MSG);
            error.set_errorCode(e.ERROR_CODE);
            return gson.toJson( error );
        }

    }

    /**
     * Web Service para compartir un entrenamiento
     * @param name
     * @param userId
     * @return
     */
    @GET
    @Path( "/sharedTraining" )
    @Produces( "application/json" )
    public String sharedTraining(@QueryParam( "trainingName" ) String name,
                                 @QueryParam( "userId" ) int userId )
    {
        Entity training = null,
                commandResult = null;
        Command command = null;
        String response = null;

        try
        {
            training = EntityFactory.createTraining(userId, name);
            command = CommandsFactory.instanciateSharedTrainingCmd(training);
            command.execute();

            commandResult = ((SharedTrainingCommand) command).get_result();
            commandResult.set_errorCode(Registry.RESULT_CODE_OK);
            response = gson.toJson( commandResult );

        }
        catch ( AddException e )
        {
            commandResult.set_errorCode( e.ERROR_CODE );
            commandResult.set_errorMsg( e.ERROR_MSG );
            response = gson.toJson( commandResult );

            logger.error( "Metodo: {} {}", "sharedTraining", e.toString() );
        }
        catch( Exception e )
        {
            commandResult.set_errorCode( Registry.RESULT_CODE_FAIL );
            commandResult.set_errorMsg( Registry.RESULT_CODE_FAIL_MSG );
            response = gson.toJson( commandResult );

            logger.error( "Metodo: {} {}", "sharedTraining", e.toString() );
        }

        return response;
    }

    /**
     * Servicio Web que retorna el entrenamiento a detalle
     * @param trainingId entrenamiento elegido
     * @return entrenamiento
     */
    @POST
    @Path( "/getTrainingDetail" )
    @Produces( "application/json" )
    public String getTrainingDetail(@QueryParam( "trainingId" ) int trainingId )
    {

        Entity training = null, commandResult = null;
        Command command = null;
        String response = null;

        try
        {
            if ( trainingId > 0 )
            {
                training = EntityFactory.createTraining( trainingId );
            }
            else
            {
                throw new InvalidParameterException( Registry.ERROR_PARAM_WS );

            }

            command = CommandsFactory.instanciateGetTrainingDetailCmd( training );
            command.execute();

            commandResult =  ( ( GetTrainingDetailCommand ) command ).get_output();
            commandResult.set_errorCode( Registry.RESULT_CODE_OK );
            response = gson.toJson( commandResult );
        }
        catch ( ListByIdException e )
        {
            commandResult.set_errorCode( e.ERROR_CODE );
            commandResult.set_errorMsg( e.ERROR_MSG );
            response = gson.toJson( commandResult );

            logger.error( "Metodo: {} {}", "getTrainingDetail", e.toString() );
        }
        catch( Exception e )
        {
            commandResult.set_errorCode( Registry.RESULT_CODE_FAIL );
            commandResult.set_errorMsg( Registry.RESULT_CODE_FAIL_MSG );
            response = gson.toJson( commandResult );

            logger.error( "Metodo: {} {}", "getTrainingDetail", e.toString() );
        }

        return response;

    }

    /**
     * Servicio Web para mostrar todos los entrenamientos
     * @param userId
     * @return lista de entrenamientos
     */
    @POST
    @Path( "/getAllTraining" )
    @Produces( "application/json" )
    public String getAllTraining( @QueryParam( "userId" ) int userId )
    {
        Entity training, cmdResult= null;
        List<Entity> commandResult = null;
        Command command;
        String response = null;

        try
        {
            if ( userId > 0 )
            {
                training = EntityFactory.createTraining( userId );
            }
            else
            {
                throw new InvalidParameterException( Registry.ERROR_PARAM_WS );
            }

            command = CommandsFactory.instanciateGetAllTrainingCmd( training );
            command.execute();

            commandResult =  ( ( GetAllTrainingCommand ) command ).get_output();
            //cmdResult.set_errorCode( Registry.RESULT_CODE_OK );
            response = gson.toJson( commandResult );
        }
        catch ( ListAllException e )
        {
            cmdResult.set_errorCode( e.ERROR_CODE );
            cmdResult.set_errorMsg( e.ERROR_MSG );
            commandResult.add(cmdResult);

            response = gson.toJson( commandResult );

            logger.error( "Metodo: {} {}", "getAllTraining", e.toString() );
        }
        catch ( Exception e )
        {
            cmdResult.set_errorCode( Registry.RESULT_CODE_FAIL );
            cmdResult.set_errorMsg( Registry.RESULT_CODE_FAIL_MSG );
            commandResult.add(cmdResult);
            response = gson.toJson( commandResult );

            logger.error( "Metodo: {} {}", "getAllTraining", e.toString() );
        }

        return response;
    }

    @GET
    @Path("/activateTraining")
    @Produces ("application/json")
    public String activateTraining(@QueryParam("userId") int userId,
                                   @QueryParam("trainingId") int trainingId)
    {

        Entity training = null, user=null;
        Entity commandResult = null;
        Command command = null;
        String response = null;

        try
        {
            training = EntityFactory.createTraining(trainingId) ;
            user= EntityFactory.createUser(userId);

            command = CommandsFactory.instanciateActiveTrainingCmd(training,user);
            command.execute();

            commandResult =  ( (ActivateTrainingCommand) command ).get_output();
            commandResult.set_errorCode( Registry.RESULT_CODE_OK );
            response = gson.toJson( commandResult );
        }
        catch ( ActiveTrainingException e )
        {
            commandResult.set_errorCode( e.ERROR_CODE );
            commandResult.set_errorMsg( e.ERROR_MSG );
            response = gson.toJson( commandResult );

            logger.error( "Metodo: {} {}", "activateTraining", e.toString() );
        }
        catch( Exception e )
        {
            commandResult.set_errorCode( Registry.RESULT_CODE_FAIL );
            commandResult.set_errorMsg( Registry.RESULT_CODE_FAIL_MSG );
            response = gson.toJson( commandResult );

            logger.error( "Metodo: {} {}", "activateTraining", e.toString() );
        }

        return response;

    }

    /**
     * Metodo que devuelve una lista de actividades para asignarle al entrenamiento
     * @param activities
     * @return Arraylist de actividades
     */
    private ArrayList<Entity> activityList (ArrayList<String> activities){
        ArrayList<Entity> activitiesList = new ArrayList<Entity>();
        for(int i = 0; i <= activities.size() - 1; i++){
            Entity act = EntityFactory.createActivity();
            if (activities.get(i).equals("Caminar")){
                act = EntityFactory.createActivity(1, activities.get(i), 2);
            }
            else if (activities.get(i).equals("Trotar")){
                act = EntityFactory.createActivity(2, activities.get(i), 1);
            }
            else if (activities.get(i).equals("Bicicleta")){
                act = EntityFactory.createActivity(3, activities.get(i), 2);
            }
            else if (activities.get(i).equals("Natacion")){
                act = EntityFactory.createActivity(4, activities.get(i), 2);
            }
            else if (activities.get(i).equals("Yoga")){
                act = EntityFactory.createActivity(5, activities.get(i), 3);
            }
            else if (activities.get(i).equals("Estiramientos")){
                act = EntityFactory.createActivity(6, activities.get(i), 1);
            }
            else if (activities.get(i).equals("Eliptica")){
                act = EntityFactory.createActivity(7, activities.get(i), 1);
            }
            else if (activities.get(i).equals("Escaleras")){
                act = EntityFactory.createActivity(8, activities.get(i), 1);
            }
            else if (activities.get(i).equals("Bailar")){
                act = EntityFactory.createActivity(9, activities.get(i), 2);
            }
            else if (activities.get(i).equals("Aerobic")){
                act = EntityFactory.createActivity(10, activities.get(i), 2);
            }
            else if (activities.get(i).equals("Remo")){
                act = EntityFactory.createActivity(11, activities.get(i), 2);
            }
            else if (activities.get(i).equals("Basketball")){
                act = EntityFactory.createActivity(12, activities.get(i), 2);
            }
            else if (activities.get(i).equals("Futbol")){
                act = EntityFactory.createActivity(13, activities.get(i), 2);
            }
            else if (activities.get(i).equals("Tenis")){
                act = EntityFactory.createActivity(14, activities.get(i), 2);
            }
            else if (activities.get(i).equals("Voleibol")){
                act = EntityFactory.createActivity(15, activities.get(i), 2);
            }
            activitiesList.add(act);
        }
        return activitiesList;
    }
}
