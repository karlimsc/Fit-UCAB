package WebServicesClasses;


import Domain.Food;
import Exceptions.ParameterNullException;
import Validations.ValidationWS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.ws.rs.*;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import  Domain.Sql;


/**
 * Created by jaorr on 22/05/17.
 */
@Path("/M11_Food")
public class M11_ServicesFood {

    private Connection conn = Sql.getConInstance();
    //Atributo que se utiliza para transformar a formado JSON las consultas.
    private Gson gson = new Gson();
    private String response;
    private ArrayList<Food> jsonArray;

    /**
     * Funcion que recibe el nombre del usuario, y con este extrae
     * la informacion de los alimentos personalizados que ha consumido el usuario
     * en el dia.
     * @param username
     * @return Devuelve un json con la estrutura de un objeto Food(foodName, foodWeight, foodCalorie, id)
     */
    @GET
    @Path("/getFoodPersonalized")
    @Produces("application/json")
    public String getFood(@QueryParam("username") String username) {
        try{
            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
                put("username", username);
            }});

            String query = "SELECT * FROM m11_get_alimentos_person(?)";
            jsonArray = new ArrayList<>();


            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                jsonArray.add(new Food());
                jsonArray.get(jsonArray.size()).set_foodName(rs.getString("nombre_comida"));
                jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
                jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
                jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));
            }

            response = gson.toJson(jsonArray);
        }
        catch(SQLException e) {
            response = e.getMessage();
        }
        catch (ParameterNullException e){
            response = e.getMessage();
        }
        finally {
            Sql.bdClose(conn);
            return response;
        }
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
        try {
            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
                put("username", username);
            }});

            String query = "select * from m11_get_todos_alimentos(?)";
            jsonArray = new ArrayList<>();


            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                jsonArray.add(new Food());
                jsonArray.get(jsonArray.size() - 1).set_foodName(rs.getString("nombre_comida"));
                // revisar string
                jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
                jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
                jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));
            }

            response = gson.toJson(jsonArray);
        }
        catch (SQLException e){
            response = e.getMessage();
        }
        catch (ParameterNullException e){
            response = e.getMessage();
        }
        finally {
            Sql.bdClose(conn);
            return response;
        }
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

        try {
            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
                put("username", username);
                put("calorie", calorie);
            }});

            String query = "select * from m11_get_alimentos_sugerencia(?, ?)";
            jsonArray = new ArrayList<>();

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            st.setInt(2, calorie);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                jsonArray.add(new Food());
                jsonArray.get(jsonArray.size() - 1).set_foodName(rs.getString("nombre_comida"));
                // revisar string
                jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
                jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
                jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));
            }

            response = gson.toJson(jsonArray);
        }
        catch (SQLException e){
            response = e.getMessage();
        }
        catch (ParameterNullException e){
            response = e.getMessage();
        }
        finally {
            Sql.bdClose(conn);
            return response;
        }
    }

//por aqui
    /**
     *
     * @param username
     * @return
     */
    @GET
    @Path("/getFoodAuto")
    @Produces("application/json")
    public String getFoodAuto(@QueryParam("username") String username){
        try {
            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
            put("username", username);
            }});
            String query = "select * from m11_get_todos_alimentos_autocompletar(?)";
            jsonArray = new ArrayList<>();


            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                jsonArray.add(new Food());
                jsonArray.get(jsonArray.size() - 1).set_foodName(rs.getString("nombre_comida"));
                // revisar string
                jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
                jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
                jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));

            }

            response = gson.toJson(jsonArray);
        }
        catch (SQLException e){
            response = e.getMessage();
        }
        catch (ParameterNullException e){
            response = e.getMessage();
        }
        finally {
            Sql.bdClose(conn);
            return response;
        }
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

        Map<String, String> response = new HashMap<String, String>();
        try {

            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
                put("foodName", foodName);
                put("idUser", idUser);
            }});

        String query = "select * from m11_elimina_alimento_person(?, ?)";



            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, foodName);
            st.setInt(2, idUser);
            st.executeQuery();
            response.put("data", "Se elimino el alimento de forma exitosa");
        }
        catch (SQLException e){
            response.put("error", e.getMessage());
        }
        catch (ParameterNullException e){
            response.put("error", e.getMessage());
        }
        finally {
            Sql.bdClose(conn);
            return gson.toJson(response);
        }
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
    @Path("/updatePersonalized")
    @Produces("application/json")
    public String updatePersonalized(@QueryParam("foodName") String foodName,
                                     @QueryParam("foodWeight") String foodWeight,
                                     @QueryParam("calorie") String calorie,
                                     @QueryParam("idUser") int idUser){

        Map<String, String> response = new HashMap<String, String>();
        try {
            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
                put("foodName", foodName);
                put("foodWeight", foodWeight);
                put("calorie", calorie);
                put("idUser", idUser);
            }});
            String query = "select * from m11_act_alimento_person(?, ?, ?, ?)";



            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, foodName);
            st.setInt(2, Integer.parseInt(foodWeight));
            st.setInt(3, Integer.parseInt(calorie));
            st.setInt(4, idUser);
            st.executeQuery();
            response.put("data", "Se actualizo el alimento de forma exitosa");
        }
        catch (SQLException e){
            response.put("error", e.getMessage());
        }
        catch (ParameterNullException e){
            response.put("error", e.getMessage());
        }
        finally {
            Sql.bdClose(conn);
            return gson.toJson(response);
        }
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
    @Path("/insertOnePersonalizedFood")
    @Produces("application/json")
    public String insertUnAlimento( @QueryParam("foodName") String foodName ,
                                  @QueryParam("foodCalorie") String foodCalorie ,
                                  @QueryParam("foodWeight") String foodWeight ,
                                    @QueryParam("foodDinner") String foodDinner,
                                    @QueryParam("idUser") int idUser){

        Map<String, String> response = new HashMap<String, String>();
        try {


            String query = "select m11_inserta_alim_person(? , ?, ?, ?, ?)";


            PreparedStatement st = conn.prepareStatement(query);

                st.setString(1, foodName);
                st.setInt(2, Integer.parseInt(foodCalorie));
                st.setInt(3, Integer.parseInt(foodWeight));
                st.setBoolean(4 , Boolean.parseBoolean(foodDinner));
                st.setInt(5, idUser);

                st.executeQuery();
            response.put("data", "Se insertaron los alimentos de forma exitosa");
        }
        catch (SQLException e) {
            //response.put("error", e.getMessage());
        }
        catch (ParameterNullException e){
            //.put("error", e.getMessage());
        }
        finally {
            Sql.bdClose(conn);
            return gson.toJson(response);
        }
    }

    /**
     * Funcion que inserta uno o varios alimentos personalizados
     * @param jsonFood Indica los alimento que se insertaran, debe cumplir con una estructura de
     *                      un arreglo de objetos Food(foodName, foodWeight, FoodCalorie) convertido en json
     * @return  Devuelve un json con elemento llamado data, el cual contiene el mensaje de la peticion
     */

    @POST
    @Path("/insertPersonalizedFood")
    @Produces("application/json")
    public String insertPersonalizedFood(@QueryParam("jsonFood") String jsonFood){

        Map<String, String> response = new HashMap<String, String>();
        try {
            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
                put("jsonFood", jsonFood);
            }});

            String query = "select * from m11_inserta_alim_person(? , ?, ?)";
            Type type = new TypeToken<Food[]>(){}.getType();
            /*
            Food[] a = new Food[3];
            a[0] = new Food(1, "cachapa", "20", "52", true);
            a[2] = new Food(2, "cachap2", "21", "53", true);
            a[1] = new Food(3, "cachap3", "22", "54", true);
            jsonAlimentos = gson.toJson(a);
            */
            Food[] alimentos = gson.fromJson(jsonFood, type);

            PreparedStatement st = conn.prepareStatement(query);
            for (int i = 0; i < alimentos.length; i++) {
                st.setString(1, alimentos[i].get_foodName());
                st.setInt(2, Integer.parseInt(alimentos[i].get_foodWeight()));
                st.setInt(3, Integer.parseInt(alimentos[i].get_foodCalorie()));
                st.executeQuery();
            }

            response.put("data", "Se insertaron los alimentos de forma exitosa");
        }
        catch (SQLException e) {
            response.put("error", e.getMessage());
        }
        catch (ParameterNullException e){
            response.put("error", e.getMessage());
        }
        finally {
            Sql.bdClose(conn);
            return gson.toJson(response);
        }
    }

    /**
     * Funcion que devuelve los alimentos personalizados de un usuario
     * @param username
     * @return
     */
    @GET
    @Path("/getPersonalizedList")
    @Produces("application/json")
    public String getPersonalizedList(@QueryParam("username") String username) {

        try {
            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
                put("username", username);
            }});

            String query = "select * from m11_get_alimentos_person_lista(?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            jsonArray = new ArrayList<>();
            while(rs.next()){
                jsonArray.add(new Food());
                jsonArray.get(jsonArray.size() - 1).set_foodName(rs.getString("nombre_comida"));
                jsonArray.get(jsonArray.size() - 1).set_foodWeight(rs.getString("peso_comida"));
                jsonArray.get(jsonArray.size() - 1).set_foodCalorie(rs.getString("calorias_comida"));
                jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_alimento"));

            }

            response = gson.toJson(jsonArray);
        }
        catch (SQLException e){
            Map<String, String> respuestaError = new HashMap<String, String>();
            respuestaError.put("error", e.getMessage());
            response = gson.toJson(respuestaError);
        }
        catch (ParameterNullException e) {
            Map<String, String> respuestaError = new HashMap<String, String>();
            respuestaError.put("error", e.getMessage());
            response = gson.toJson(respuestaError);
        }
        finally {
            Sql.bdClose(conn);
            return response;
        }


    }

}
