package WebServicesClasses;
import Domain.Diet;
import Domain.Sql;
import Exceptions.ParameterNullException;
import Validations.ValidationWS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.time.LocalDate;
import javax.ws.rs.*;
import java.sql.*;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaorr on 22/05/17.
 */

@Path("/M11_Diet")
public class M11_ServicesDiet {

    private Connection conn = Sql.getConInstance();
    private Gson gson = new Gson();
    private String response;
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

        try{
            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
                put("username", username);
                put("date", date);
            }});

            String query = "SELECT * FROM m11_get_calorias_fecha(?, ?)";
            jsonArray = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement(query);
            st.setDate(1, Date.valueOf(date));
            st.setString(2, username);
            ResultSet rs = st.executeQuery();
            //La variable donde se almacena el resultado de la consulta.
            while(rs.next()){
                jsonArray.add(new Diet());
                jsonArray.get(jsonArray.size() - 1).set_calorie(rs.getInt("calorias"));
            }
            response = gson.toJson(jsonArray);
        }
        catch(SQLException e) {
            response = e.getMessage();
        }
        catch (ParameterNullException e) {
            response = e.getMessage();
        }
        finally {
            Sql.bdClose(conn);
            return response;
        }
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

        Map<String, String> response = new HashMap<String, String>();
        try{

            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
                put("username", username);
                put("moment", moment);
            }});
            String query = "SELECT m11_elimina_alimento_dieta(?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, moment);
            st.setString(2, username);
            ResultSet rs = st.executeQuery();
            response.put("data", "Se elimino exitosamente");

        }
        catch(SQLException e) {
            response.put("error", e.getMessage());
        }
        catch (ParameterNullException e) {
            response.put("error", e.getMessage());
        }
        finally {
            Sql.bdClose(conn);
            return gson.toJson(response);

        }
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

        try {
            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
                put("username", username);
                put("date", date);
                put("moment", moment);
            }});

            String query = "select * from m11_get_comida_momento(?, ?, ?)";
            jsonArray = new ArrayList<>();
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, moment);
            st.setDate(2, Date.valueOf(date));
            st.setString(3, username);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                jsonArray.add(new Diet());
                jsonArray.get(jsonArray.size() - 1).set_calorie(rs.getInt("calorias"));
                jsonArray.get(jsonArray.size() - 1).set_id(rs.getInt("id_dieta"));
                jsonArray.get(jsonArray.size() - 1).set_food(rs.getString("nombre"));
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
     *
     * @param username
     * @return
     */

    @GET
    @Path("/getConsumedCalorieByDay")
    @Produces("application/json")
    public String getConsumedCalorieByDay(@QueryParam("username") String username){
        try {
            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
                put("username", username);
            }});

            String query = "select * from m11_get_calorias_mes(?, ?, ?)";
            jsonArray = new ArrayList<>();
            ResultSet rs;
            LocalDate fecha = LocalDate.now();
            Date day;
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);

            for (int i=0; i<=6; i++){
                day = Date.valueOf(fecha);
                st.setDate(2,day);
                st.setDate(3,day);
                rs = st.executeQuery();
                jsonArray.add(new Diet());
                jsonArray.get(jsonArray.size() - 1).set_dateTime(fecha);
                if (rs.wasNull()){
                    jsonArray.get(jsonArray.size() - 1).set_calorie(0);
                }
                while (rs.next()){
                    jsonArray.get(jsonArray.size() - 1).set_calorie(rs.getInt("calorias"));
                }

                if (i < 6) {
                    fecha = fecha.minusDays(1);
                }
            }
            response = gson.toJson(jsonArray);
        }
        catch (SQLException e) {
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
    @Path("/getConsumedCalorieByWeek")
    @Produces("application/json")
    public String getConsumedCalorieByWeek(@QueryParam("username") String username){
        try {
            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
                put("username", username);
            }});

            String query = "select * from m11_get_calorias_mes(?, ?, ?)";
            jsonArray = new ArrayList<>();
            ResultSet rs;
            LocalDate fecha = LocalDate.now();
            Date day;
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);

            for (int i=0; i<=3; i++){
                day = Date.valueOf(fecha);
                st.setDate(2,day);
                st.setDate(3,day);
                rs = st.executeQuery();
                jsonArray.add(new Diet());
                jsonArray.get(jsonArray.size() - 1).set_dateTime(fecha);
                if (rs.wasNull()){
                    jsonArray.get(jsonArray.size() - 1).set_calorie(0);
                }
                while (rs.next()){
                    jsonArray.get(jsonArray.size() - 1).set_calorie(rs.getInt("calorias"));
                }

                if (i < 6) {
                    fecha = fecha.minusWeeks(1);
                }
            }
            response = gson.toJson(jsonArray);
        }
        catch (SQLException e) {
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
     * Funcion obtiene las calorias consumidas por un usuario en los ultimos 12 meses.
     * @param username Indica el nombre del usuario.
     * @return Devuelve un json con la informacion de las calorias consumidas relacionada con el mes correspondiente
     */
    @GET
    @Path("/getConsumedCalorieByMonth")
    @Produces("application/json")
    public String getConsumedCalorieByMonth(@QueryParam("username") String username){

        try {

            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
                put("username", username);
            }});

            String query = "select * from m11_get_calorias_mes(?, ?, ?)";
            ResultSet rs;
            jsonArray = new ArrayList<>();
            LocalDate fecha = LocalDate.now();
            fecha = fecha.with(TemporalAdjusters.firstDayOfMonth());
            Date fechaInicio = Date.valueOf(fecha);
            Date fechafin = Date.valueOf(fecha.with(TemporalAdjusters.lastDayOfMonth()));
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            st.setDate(2, fechaInicio);
            st.setDate(3, fechafin);
            for (int i = 0; i <= 11; i++) {
                if (i > 0) {
                    fecha = fecha.minusMonths(1);
                    fechaInicio = Date.valueOf(fecha);
                    fechafin = Date.valueOf(fecha.with(TemporalAdjusters.lastDayOfMonth()));
                    st.setDate(2, fechaInicio);
                    st.setDate(3, fechafin);
                }

                rs = st.executeQuery();
                jsonArray.add(new Diet());
                if (rs.wasNull()) {
                    jsonArray.get(jsonArray.size() - 1).set_calorie(0);
                    jsonArray.get(jsonArray.size() - 1).set_dateTime(fechaInicio.toLocalDate());
                }
                else {

                    while (rs.next()) {
                        jsonArray.get(jsonArray.size() - 1).set_calorie(rs.getInt("calorias"));
                        jsonArray.get(jsonArray.size() - 1).set_dateTime(fechaInicio.toLocalDate());
                    }
                }
            }
            response = gson.toJson(jsonArray);
        }
        catch (SQLException e) {
            response = e.getMessage();
        }
        catch (ParameterNullException e) {
            response = e.getMessage();
        }
        finally {
            Sql.bdClose(conn);
            return response;
        }
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

        Map<String, String> response = new HashMap<String, String>();
        try {
            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>(){ {
                put("diet", jsonDiet);
            }});

            String query = "select * from m11_inserta_dieta(?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            Type type = new TypeToken<Diet[]>(){}.getType();
            /*
            Diet[] a = new Diet[3];
            a[0] = new Diet(20, "cachapa", "desayuno", "Jesus");
            a[2] = new Diet(2, "cachap2", "almuerzo", "Jesus");
            a[1] = new Diet(3, "cachap3", "cena", "Jesus");
            jsonDieta = gson.toJson(a);
            */
            Diet[] dieta = gson.fromJson(jsonDiet, type);

            for (int i = 0; i < dieta.length; i++) {
                st.setInt(1, dieta[i].get_calorie());
                st.setString(2, dieta[i].get_food());
                st.setString(3, dieta[i].get_moment());
                st.setString(4, dieta[i].get_username());
                st.executeQuery();
            }

            response.put("data", "Se inserto la dieta de forma exitosa");
        }
        catch (SQLException e){
            response.put("error", e.getMessage());
        }
        catch (ParameterNullException e) {
            response.put("error", e.getMessage());
        }
        finally {
            Sql.bdClose(conn);
            return gson.toJson(response);
        }

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

        Map<String, String> response = new HashMap<String, String>();
        try {
            ValidationWS.validarParametrosNotNull(new HashMap<String, Object>() {
                {
                    put("idUser", idUser);
                    put("dietCalorie", dietCalorie);
                    put("foodName", foodName);
                    put("moment", moment);
                }
            });

            String query = "select * from m11_inserta_dieta(?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            Type type = new TypeToken<Diet[]>() {}.getType();

            st.setInt(1, Integer.parseInt(dietCalorie));
            st.setString(2, foodName);
            st.setString(3, moment);
            st.setInt(4, idUser);
            st.executeQuery();

            response.put("data", "Se inserto la dieta de forma exitosa");
        } catch (SQLException e) {
            response.put("error", e.getMessage());
        } catch (ParameterNullException e) {
            response.put("error", e.getMessage());
        } finally {
            Sql.bdClose(conn);
            return gson.toJson(response);
        }
    }

}
