package edu.ucab.desarrollo.fitucab.webService;

import javax.ws.rs.*;


import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.Registry;
import edu.ucab.desarrollo.fitucab.common.entities.Activity;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.*;

import javax.ws.rs.*;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;


//AGREGAR USUARIOS


@Path( "/M06_ServicesTraining" )
public class M06_ServicesTraining
{

    Gson gson = new Gson();

    //el tipo de instruccion HTTP
    @POST
    //el path de la funcion
    @Path( "/createTraining" )
    //formato de retorno
    @Produces( "application/json" )

    /**
     * Metodo utilizado a traves de web service para agregar un entrenamiento
     * @param trainingName
     * @param trainingActivities
     * @param userId
     * @return
     */

    // LinkedList<String> activities revisar esto OJO
    public String createTraining(@QueryParam( "trainingName" ) String name,
                                 @QueryParam( "trainingActivities" )  final LinkedList<String> activities,
                                 @QueryParam( "userId" ) int userId )
    {
        LinkedList<Entity> activitiesList = activityList(activities);
        Entity createTrainingObject = EntityFactory.createTraining(userId, name, activitiesList);
        CreateTrainingCommand cmd =
                CommandsFactory.instanciateCreateTrainingCmd( createTrainingObject);
        try
        {
            cmd.execute();
            Entity result = cmd.getResult();//nuevo
            return gson.toJson( result );//nuevo
        }
        catch ( Exception e )
        {
            return gson.toJson( false );
        }
    }


    @POST
    @Path( "/updateTraining" )
    @Produces( "application/json" )

    /**
     * Metodo utilizado a traves de webservice para cambiar los parametros de un entrenamiento existente en
     * la base de datos
     * @param idTraining
     * @param trainingName
     * @param traningPeriod
     * @param trainingCalories
     * @return
     */

    public String updateTraining( @QueryParam( "idTraining" ) int id,
            @QueryParam( "trainingName" ) String name,
            @QueryParam( "trainingPeriod" ) int period )
    {
        Entity updatedTrainingObject = EntityFactory.createTraining( id, name, period );
        UpdateTrainingCommand cmd = CommandsFactory.instanciateUpdateTrainingCmd( updatedTrainingObject );
        try
        {
            cmd.execute();
            return gson.toJson( true );
        }
        catch ( Exception e )
        {
            return gson.toJson( false );
        }

    }

    @GET
    @Path( "/displayTraining" )
    @Produces( "application/json" )
    /**
     * Metodo utilizado a traves de web service para visualizar los entrenamientos que posee el usuario
     * @param trainingId
     * @return
     */

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

    @GET
    @Path( "/deleteTraining" )
    @Produces( "application/json" )
    /**
     * Metodo utilizado a traves de web service para eliminar un entrenamiento
     * @param trainingId
     * @param trainingName
     * @return
     */

    public String deleteTraining( @QueryParam( "trainingId" ) int trainingId,
                                  @QueryParam( "trainingName" ) String trainingName )
    {

        Entity deleteTrainingObject = EntityFactory.createTraining(trainingId, trainingName);

        DeleteTrainingCommand cmd = CommandsFactory.instanciateDeleteTrainingCmd(deleteTrainingObject);


        try
        {
            cmd.execute();
            return gson.toJson( true );
        }
        catch ( Exception e )
        {
            return gson.toJson( false );
        }

    }

    @GET
    @Path( "/shareTraining" )
    @Produces( "application/json" )
    /**
     * Metodo utilizado a traves de web service para compartir un entrenamiento
     * @param trainingName
     * @param trainingActivities
     * @param userId
     * @return
     */
    public String shareTraining(@QueryParam( "trainingName" ) String name,
                                 @QueryParam( "trainingActivities" )  final LinkedList<String> activities,
                                 @QueryParam( "userId" ) int userId )
    {
        LinkedList<Entity> activitiesList = activityList(activities);
        Entity shareTrainingObject = EntityFactory.createTraining(userId, name, activitiesList);
        ShareTrainingCommand cmd =
                CommandsFactory.instanciateShareTrainingCmd(shareTrainingObject);
        try
        {
            cmd.execute();
            return gson.toJson( true );
        }
        catch ( Exception e )
        {
            return gson.toJson( false );
        }
    }


    private LinkedList<Entity> activityList (LinkedList<String> activities){
        LinkedList<Entity> activitiesList = new LinkedList<Entity>();
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

    /**
     * Servicio Web que retorna el entrenamiento a detalle
     * @param userId usuario login
     * @param trainingId entrenamiento elegido
     * @return entrenamiento
     */
    @POST
    @Path( "/getTrainingDetail" )
    @Produces( "application/json" )
    public String getTrainingDetail( @QueryParam( "userId" ) int userId,
                                     @QueryParam( "trainingId" ) int trainingId )
    {
        return null;
    }

    /**
     * Servicio Web que retorna la lista de entrenamientos del usuario
     * @param userId usuario login
     * @return entrenamiento
     */
    @POST
    @Path( "/getAllTraining" )
    @Produces( "application/json" )
    public String getAllTraining( @QueryParam( "userId" ) int userId )
    {
        return null;
    }

}
