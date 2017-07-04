package edu.ucab.desarrollo.fitucab.webService;


import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Diet;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Sql;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.*;

import javax.ws.rs.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * Created by jaorr on 22/05/17.
 */

@Path("/M11_Diet")
public class M11_ServicesDiet {

    private Connection conn = Sql.getConInstance();
    private Gson gson = new Gson();
    private Diet respuesta;
    private ArrayList<Diet> jsonArray;

    /**
     * Funcion que recibe como parametros la date y el nombre del usuario
     * para hacer la consulta de las calorias consumidas por el usuario durante
     * esa date.
     * @param date Fecha del dia en que se quiere obtener las calorias consumidas. Debe ser en formato yyyy-mm-dd
     * @param username Indica el nombre del usuario
     * @return Devuelve las calorias consumidas en formato json
     */
    @GET
    @Path("/getCalorieByDate")
    @Produces("application/json")
    public String getCalorieByDate(@QueryParam("date") String date ,
                                   @QueryParam("username") String username) {

        Entity EntityDiet = EntityFactory.getCaloriesDate(date,username);
        GetCaloriesDateCommand cmd = CommandsFactory.getCaloriesDateCmd(EntityDiet);

        try {
            cmd.execute();
            respuesta = (Diet) cmd.Respuesta;

        } catch (ListAllException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (ListByIdException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (NoSuchMethodException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (SQLException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (BdConnectException e) {
            respuesta .set_errorMsg(e.getMessage());
        }


        return gson.toJson(respuesta);

    }


    /**
     * Metodo que recibe como parametros el momento (momento del dia en que se alimenta)
     * y el nombre del usuario para eliminar el alimento que ingirió en ese momento del
     * día.
     * @param moment Indica el momento del dia.
     * @param username Indica el nombre del usuario.
     * @return Devuelve un json con elemento llamado data, el cual contiene el mensaje de la peticion
     */
    @DELETE
    @Path("/deleteDiet")
    @Produces("application/json")
    public String deleteDiet(@QueryParam("moment") String moment,
                             @QueryParam("username") String username) {

        Entity EntityDiet = EntityFactory.deleteDiet(moment,username);
        DeleteDietCommand cmd = CommandsFactory.deleteDietCmd(EntityDiet);

        try {
            cmd.execute();
            respuesta = (Diet) cmd.Respuesta;

        } catch (ListAllException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (ListByIdException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (NoSuchMethodException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (SQLException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (BdConnectException e) {
            respuesta .set_errorMsg(e.getMessage());
        }


        return gson.toJson(respuesta);
    }

    /**
     * Funcion que obtiene los alimentos que ingirio el usuario para un momento y dia determinado
     * @param moment Indica el momento
     * @param date Indica la fecha en formato yyyy-mm-dd
     * @param username Indica el nombre del usuario
     * @return Devuelve un json con la informacion de calorias, id, nombre de los alimentos consumidos
     */

    @GET
    @Path("/getMomentFood")
    @Produces("application/json")
    public String getMomentFood(@QueryParam("moment") String moment,
                                           @QueryParam("date") String date,
                                           @QueryParam("username") String username) {

        Entity EntityDiet = EntityFactory.getMoment(moment,date,username);
        GetMomentFoodCommand cmd = CommandsFactory.getMomentFoodCmd(EntityDiet);

       try {
            cmd.execute();
            respuesta = (Diet) cmd.Respuesta;

        } catch (ListAllException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (ListByIdException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (NoSuchMethodException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (SQLException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (BdConnectException e) {
            respuesta .set_errorMsg(e.getMessage());
        }


        return gson.toJson(respuesta);

    }

    /**
     *
     * @param username
     * @return
     */

    @GET
    @Path("/getConsumedCalorieByDay")
    @Produces("application/json")
    public String getConsumedCalorieByDay(@QueryParam("username") String username){

        Entity EntityDiet = EntityFactory.getCaloriesdate(username);
        GetCaloriesConsumedDayCommand cmd = CommandsFactory.getCaloriesDayCmd(EntityDiet);

        try {
            cmd.execute();
            respuesta = (Diet) cmd.Respuesta;

        } catch (ListAllException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (ListByIdException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (NoSuchMethodException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (SQLException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (BdConnectException e) {
            respuesta .set_errorMsg(e.getMessage());
        }


        return gson.toJson(respuesta);
    }

    /**
     *
     * @param username
     * @return
     */

    @GET
    @Path("/getConsumedCalorieByWeek")
    @Produces("application/json")
    public String getConsumedCalorieByWeek(@QueryParam("username") String username){

        Entity EntityDiet = EntityFactory.getCaloriesdate(username);
        GetCaloriesConsumedWeekCommand cmd = CommandsFactory.getCaloriesWeekCmd(EntityDiet);

        try {
            cmd.execute();
            respuesta = (Diet) cmd.Respuesta;

        } catch (ListAllException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (ListByIdException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (NoSuchMethodException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (SQLException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (BdConnectException e) {
            respuesta .set_errorMsg(e.getMessage());
        }


        return gson.toJson(respuesta);
    }

    /**
     * Funcion obtiene las calorias consumidas por un usuario en los ultimos 12 meses.
     * @param username Indica el nombre del usuario.
     * @return Devuelve un json con la informacion de las calorias consumidas relacionada con el mes correspondiente
     */
    @GET
    @Path("/getConsumedCalorieByMonth")
    @Produces("application/json")
    public String getConsumedCalorieByMonth(@QueryParam("username") String username){

        Entity EntityDiet = EntityFactory.getCaloriesdate(username);
        GetCaloriesConsumedMonthCommand cmd = CommandsFactory.getCaloriesMonthCmd(EntityDiet);

        try {
            cmd.execute();
            respuesta = (Diet) cmd.Respuesta;

        } catch (ListAllException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (ListByIdException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (NoSuchMethodException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (SQLException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (BdConnectException e) {
            respuesta .set_errorMsg(e.getMessage());
        }


        return gson.toJson(respuesta);
    }

    /**
     * Funcion que perimite ingresar varios alimentos que consumio el usuario
     * @param jsonDiet Indica los alimentos que se insertaran en formato json, el json debe tener la estructura
     *                  de un arreglo de un objeto Diet(_calorie, _food, _moment, _username) convertido en  json
     * @return Devuelve un json con elemento llamado data, el cual contiene el mensaje de la peticion
     */
    @POST
    @Path("/insertDiet")
    @Produces("application/json")
    public String insertDiet(@QueryParam("diet") String jsonDiet){

        return "";


    }


    /**
     *
     * @param idUser
     * @param dietCalorie
     * @param foodName
     * @param moment
     * @return
     */
    @GET
    @Path("/insertOneDiet")
    @Produces("application/json")
    public String insertDiet(@QueryParam("idUser") int idUser , @QueryParam("dietCalorie") String dietCalorie ,
                             @QueryParam("foodName") String foodName , @QueryParam("moment") String moment) {

        Entity EntityDiet = EntityFactory.insertDiet(idUser,dietCalorie,foodName,moment);
        InsertOneDietCommand cmd = CommandsFactory.insertOneDietCmd(EntityDiet);

        try {
            cmd.execute();
            respuesta = (Diet) cmd.Respuesta;

        } catch (ListAllException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (ListByIdException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (NoSuchMethodException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (SQLException e) {
            respuesta .set_errorMsg(e.getMessage());
        } catch (BdConnectException e) {
            respuesta .set_errorMsg(e.getMessage());
        }


        return gson.toJson(respuesta);

    }

}
