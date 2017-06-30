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
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.CheckTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.CreateTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.UpdateTrainingCommand;

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
     * Metodo utilizado a traves de web service para agregar los parametros a la base de datos
     * @param trainingName
     * @param trainingPeriod
     * @param trainingCalories
     * @return
     */

    // LinkedList<String> activities revisar esto OJO
    public String createTraining(@QueryParam( "trainingName" ) String name,
                                 @QueryParam( "trainingActivities" )  final LinkedList<String> activities,
                                 @QueryParam( "userId" ) int userId )
    {

        Entity createTrainingObject = EntityFactory.createTraining(userId, name, activities);
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
     * @param userId
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

}

