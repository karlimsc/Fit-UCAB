package edu.ucab.desarrollo.fitucab.webService;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Food;
import edu.ucab.desarrollo.fitucab.common.entities.Sql;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.*;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;



/**
 * Created by jaorr on 22/05/17.
 */
@Path("/M11_Food")
public class M11_ServicesFood {

    private Connection conn = Sql.getConInstance();
    //Atributo que se utiliza para transformar a formado JSON las consultas.
    private Gson gson = new Gson();
    private Entity response;
    private ArrayList<Food> jsonArray;
    private  Entity respuesta ;

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(M09_ServicesGamification.class);
    /**
     * Funcion que recibe el nombre del usuario, y con este extrae
     * la informacion de los alimentos personalizados que ha consumido el usuario
     * en el dia.
     * @param username
     * @return Devuelve un json con la estrutura de un objeto Food(foodName, foodWeight, foodCalorie, id)
     */
    @GET
    @Path("/getFoodPersonalized")
    @Produces("application/json")//arreglar return
    public String getFood(@QueryParam("username") String username) {


        Entity EntityFood = EntityFactory.getUsername(username);
        getFoodPerCommand cmd = CommandsFactory.getFoodPerCmd(EntityFood);

        try {
            cmd.execute();
            respuesta = (Food) cmd.Respuesta;

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
    @Path("/getAllFood")
    @Produces("application/json")
    public String getAllFood(@QueryParam("username") String username){

        Entity EntityFood = EntityFactory.getUsername(username);
        getFoodallCommand cmd = CommandsFactory.getFoodallCmd(EntityFood);

        try {
            cmd.execute();
            respuesta = (Food) cmd.Respuesta;

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
     * Funcion que devuelve una lista de alimentos que pueden ser consumidos
     * en la cena, la lista de alimentos es en base a las calorias que faltan
     * para completar la cantidad de calorias consumidas recomendades es
     * un dia
     * @param username Indica el nombre del usuario
     * @param calorie Indica las calorias que el usuario ha consumido hasta el momento
     * @return Lista de alimentos con formato json
     */
    @GET
    @Path("/getSuggestion")
    @Produces("application/json")
    public String getSuggestion(@QueryParam("username") String username,
                                @QueryParam("calorie") int calorie) {

        Entity EntityFood = EntityFactory.getUserCal(username, String.valueOf(calorie));
        getSuggestionCommand cmd = CommandsFactory.getSuggestionCmd(EntityFood);

        try {
            cmd.execute();
            respuesta = (Food) cmd.Respuesta;

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
    @Path("/getFoodAuto")
    @Produces("application/json")
    public String getFoodAuto(@QueryParam("username") String username){

        Entity EntityFood = EntityFactory.getUsername(username);
        getFoodAutoCommand cmd = CommandsFactory.getFoodAutoCmd(EntityFood);

        try {
            cmd.execute();
            respuesta = (Food) cmd.Respuesta;

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
     * Funcion que elimina un alimento que ha personalizado un usuario
     * @param foodName Indica el nombre del alimento a eliminar
     * @return Devuelve un json con elemento llamado data, el cual contiene el mensaje de la peticion
     */




    @DELETE
    @Path("/deletePersonalizedFood")
    @Produces("application/json")
    public String deletePersonalizedFood(@QueryParam("foodName") String foodName,
                                         @QueryParam("IdUser")  int idUser){

        Entity EntityFood = EntityFactory.getFoodIDuser(foodName,idUser);
        deletePersonalizedFoodCommand cmd = CommandsFactory.deletPersFoodCmd(EntityFood);

        try {
            cmd.execute();
            respuesta = (Food) cmd.Respuesta;

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
     * Funcion que actualiza los datos de un alimento personalizado por el usuario
     * @param foodName Indica el nombre del usuario a actualizar
     * @param foodWeight Indica el peso del alimento con el que se actualizara
     * @param calorie Indica las calorias con el que actualizara
     * @param idUser Id del alimento a actualizar
     * @return Devuelve un json con elemento llamado data, el cual contiene el mensaje de la peticion
     */

    @GET
    @Path("/updatePersonalized")//pl mal hecho , hay que arreglarlo
    @Produces("application/json")
    public String updatePersonalized(@QueryParam("foodName") String foodName,
                                     @QueryParam("foodWeight") String foodWeight,
                                     @QueryParam("calorie") String calorie,
                                     @QueryParam("idUser") int idUser){

        Entity EntityFood = EntityFactory.getFoodWeigthCaloriId(foodName,foodWeight,calorie,idUser);
        updatePersoCommand cmd = CommandsFactory.updatepersonCmd(EntityFood);

        try {
            cmd.execute();
            respuesta = (Food) cmd.Respuesta;

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
     * Metodo que inserta un alimento ene specifico a la dieta del dia.
     * @param foodName Representa el nombre del alimento a agregar.
     * @param foodCalorie Represneta las calorias del alimento a agregar.
     * @param foodWeight Representa el peso del alimento a agregar.
     * @param foodDinner Representa el booleano, si esta en true, es un alimento que se va a sugerir para la cena.
     * @param idUser Reprensenta el id del usuario que esta ingresando el alimento.
     * @return Devuelve un mapa dentro de un json con la respuesta.
     */





    @GET
    @Path("/insertOnePersonalizedFood")//listo
    @Produces("application/json")
    public String insertUnAlimento( @QueryParam("foodName") String foodName ,
                                  @QueryParam("foodCalorie") String foodCalorie ,
                                  @QueryParam("foodWeight") String foodWeight ,
                                    @QueryParam("foodDinner") String foodDinner,
                                    @QueryParam("idUser") int idUser){

        Entity EntityFood = EntityFactory.getFoodall
                (foodName,foodCalorie,foodWeight, Boolean.valueOf(foodDinner),idUser);

        insertUnAlimentoCommand cmd = CommandsFactory.insertarAlimentoCmd(EntityFood);

        try {
            cmd.execute();
            respuesta = (Food) cmd.Respuesta;

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
     * Funcion que inserta uno o varios alimentos personalizados
     * @param jsonFood Indica los alimento que se insertaran, debe cumplir con una estructura de
     *                      un arreglo de objetos Food(foodName, foodWeight, FoodCalorie) convertido en json
     * @return  Devuelve un json con elemento llamado data, el cual contiene el mensaje de la peticion
     */

    @POST
    @Path("/insertPersonalizedFood") // confio que funciona
    @Produces("application/json")
    public String insertPersonalizedFood(@QueryParam("jsonFood") String jsonFood){

        Entity EntityFood  = EntityFactory.putJson(jsonFood);
        insertUnAlimentoCommand cmd = CommandsFactory.insertarAlimentoCmd(EntityFood);


        try {
            cmd.execute();
            respuesta = (Food) cmd.Respuesta;

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
     * Funcion que devuelve los alimentos personalizados de un usuario
     * @param username
     * @return
     */
    @GET
    @Path("/getPersonalizedList")//listo
    @Produces("application/json")
    public String getPersonalizedList(@QueryParam("username") String username) {

        Entity EntityFood = EntityFactory.getUsername(username);
        getPersonalizedListCommand cmd = CommandsFactory.getPersoFoodCmd(EntityFood);


        try {
            cmd.execute();
            respuesta = (Food) cmd.Respuesta;

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
