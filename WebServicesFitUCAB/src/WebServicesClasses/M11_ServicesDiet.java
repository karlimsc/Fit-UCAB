package WebServicesClasses;



import Domain.Diet;
import Domain.Food;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.firstDayOfMonth;
import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;
import static java.time.temporal.TemporalAdjusters.firstDayOfNextMonth;
import javax.ws.rs.*;
import java.sql.*;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jaorr on 22/05/17.
 */

@Path("/M11_Diet")
public class M11_ServicesDiet {

    private Connection conn = bdConnect();
    private Gson gson = new Gson();
    private String respuesta;


    /**
     * Funcion que recibe como parametros la fecha y el nombre del usuario
     * para hacer la consulta de las calorias consumidas por el usuario durante
     * esa fecha.
     * @param fecha
     * @param username
     * @return
     */
    @GET
    @Path("obtener_calorias_fecha")
    @Produces("application/json")
    public String obtenerCaloriasPorFecha(@QueryParam("fecha") String fecha ,
                                          @QueryParam("username") String username)
    {

        String query = "SELECT * FROM get_calorias_fecha(?, ?)";
        Diet diet = new Diet();
        JsonArray arregloJson = new JsonArray();
        try{

            PreparedStatement st = conn.prepareStatement(query);
            st.setDate(1, Date.valueOf(fecha));
            st.setString(2, username);
            ResultSet rs = st.executeQuery();
            //La variable donde se almacena el resultado de la consulta.
            while(rs.next()){
                diet.set_calorie(rs.getInt("calorias"));
                arregloJson.add(gson.toJson(diet));
            }
            respuesta = gson.toJson(arregloJson);
        }
        catch(Exception e) {
            respuesta = e.getMessage();
        }
        finally {
            bdClose();
            return respuesta;
        }
    }

    /**
     * Metodo que recibe como parametros el momento (momento del dia en que se alimenta)
     * y el nombre del usuario para eliminar el alimento que ingirió en ese momento del
     * día.
     * @param momento
     * @param username
     */
    @DELETE
    @Path("eliminar_alimento_dieta")
    @Produces("application/json")
    public String eliminarDieta(@QueryParam("momento") String momento ,
                                @QueryParam("username") String username)
    {
        String query = "SELECT elimina_alimento_dieta(?, ?)";
        Map<String, String> respuesta = new HashMap<String, String>();
        try{

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, momento);
            st.setString(2, username);
            ResultSet rs = st.executeQuery();
            respuesta.put("data", "Se elimino exitosamente");

        }
        catch(Exception e) {
            respuesta.put("data", e.getMessage());
        }
        finally {
            bdClose();
            return gson.toJson(respuesta);

        }
    }

    /**
     *
     * @param momento
     * @param fecha
     * @param username
     * @return
     */

    @GET
    @Path("obtener_comida_momento")
    @Produces("application/json")
    public String obtenerComidaDeUnMomento(@QueryParam("momento") String momento,
                                           @QueryParam("fecha") String fecha,
                                           @QueryParam("username") String username) {

        String query = "select * from get_comida_momento(?, ?, ?)";
        Diet diet = new Diet();
        Food food = new Food();
        JsonArray arregloJson = new JsonArray();

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, momento);
            st.setDate(2, Date.valueOf(fecha));
            st.setString(3, username);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                diet.set_calorie(rs.getInt("calorias"));
                diet.set_id(rs.getInt("id_dieta"));
                diet.set_food(rs.getString("nombre"));
                arregloJson.add(gson.toJson(diet));
            }
            respuesta = gson.toJson(arregloJson);
        }
        catch (SQLException e){
            respuesta = e.getMessage();
        }
        finally {
            bdClose();
            return respuesta;
        }

    }

    /**
     *
     * @param username
     * @return
     */

    @GET
    @Path("calorias_consumidas_dia")
    @Produces("application/json")
    public String caloriasConsumidasDia(@QueryParam("username") String username){

        String query = "select * from get_calorias_dia(?)";
        Diet diet = new Diet();
        JsonArray arregloJson = new JsonArray();

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                diet.set_calorie(rs.getInt("calorias"));
                arregloJson.add(gson.toJson(diet));
            }

            respuesta = gson.toJson(arregloJson);
        }
        catch (SQLException e) {
            respuesta = e.getMessage();
        }

        finally {
            bdClose();
            return respuesta;
        }
    }

    /**
     *
     * @param username
     * @return
     */

    @GET
    @Path("calorias_consumidas_semana")
    @Produces("application/json")
    public String caloriasConsumidasSemana(@QueryParam("username") String username){

        String query = "select * from get_calorias_semana(?)";
        Diet diet = new Diet();
        JsonArray arregloJson = new JsonArray();

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while (rs.next()){
                diet.set_calorie(rs.getInt("calorias"));
                arregloJson.add(gson.toJson(diet));
            }

            respuesta = gson.toJson(arregloJson);
        }
        catch (SQLException e) {
            respuesta = e.getMessage();
        }

        finally {
            bdClose();
            return respuesta;
        }
    }

    /**
     *
     * @param username
     * @return
     */
    @GET
    @Path("calorias_consumidas_mes")
    @Produces("application/json")
    public String caloriasConsumidasMes(@QueryParam("username") String username){

        String query = "select * from get_calorias_mes(?, ?, ?)";
        Diet diet = new Diet();
        ResultSet rs;
        JsonArray arregloJson = new JsonArray();
        LocalDate fecha = LocalDate.now();
        Date fechaInicio = Date.valueOf(fecha.with(TemporalAdjusters.firstDayOfMonth()));
        Date fechafin = Date.valueOf(fecha.with(TemporalAdjusters.lastDayOfMonth()));

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            st.setDate(2, fechaInicio);
            st.setDate(3, fechafin);
            for (int i = 0; i <= 11; i++) {
                if (i > 0) {
                    fecha = fecha.with(TemporalAdjusters.firstDayOfNextMonth());
                    fechaInicio = Date.valueOf(fecha);
                    fechafin = Date.valueOf(fecha.with(TemporalAdjusters.lastDayOfMonth()));
                    st.setDate(2, fechaInicio);
                    st.setDate(3, fechafin);
                }

                rs = st.executeQuery();

                if (rs.wasNull()) {
                    diet.set_calorie(0);
                    diet.set_dateTime(fechaInicio.toLocalDate());
                    arregloJson.add(gson.toJson(diet));
                } else {

                    while (rs.next()) {
                        diet.set_calorie(rs.getInt("calorias"));
                        diet.set_dateTime(fechaInicio.toLocalDate());
                        arregloJson.add(gson.toJson(diet));
                    }
                }
            }
            respuesta = gson.toJson(arregloJson);
        }
        catch (SQLException e) {
            respuesta = e.getMessage();
        }
        finally {
            bdClose();
            return respuesta;
        }
    }

    /**
     *
     * @param jsonDieta
     * @return
     */
    @POST
    @Path("insertar_dieta")
    @Produces("application/json")
    public String insertar_dieta(@QueryParam("dieta") String jsonDieta){

        String query = "select * from inserta_dieta(?, ?, ?, ?)";
        Map<String, String> respuesta = new HashMap<String, String>();

        try {
            PreparedStatement st = conn.prepareStatement(query);
            Type type = new TypeToken<Diet[]>(){}.getType();

            Diet[] a = new Diet[3];
            a[0] = new Diet(20, "cachapa", "desayuno", "Jesus");
            a[2] = new Diet(2, "cachap2", "almuerzo", "Jesus");
            a[1] = new Diet(3, "cachap3", "cena", "Jesus");
            jsonDieta = gson.toJson(a);

            Diet[] dieta = gson.fromJson(jsonDieta, type);

            for (int i = 0; i < dieta.length; i++) {
                st.setInt(1, dieta[i].get_calorie());
                st.setString(2, dieta[i].get_food());
                st.setString(3, dieta[i].get_momento());
                st.setString(4, dieta[i].get_username());
                st.executeQuery();
            }

            respuesta.put("data", "Se inserto la dieta de forma exitosa");
        }
        catch (SQLException e){
            respuesta.put("data", e.getMessage());
        }
        finally {
            bdClose();
            return gson.toJson(respuesta);
        }

    }

    //esto no va a aqui , se puso momentaneamente.
    public Connection bdConnect()
    {
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/FitUcabDB";
            conn = DriverManager.getConnection(url,"fitucab", "fitucab");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            bdClose();
            System.exit(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            bdClose();
            System.exit(2);
        }
        return conn;
    }

    public int bdClose(){
        try{
            conn.close();
        }

        catch (java.sql.SQLException e) {
            System.out.println("fallo cerrar");
            System.exit( 0);
        }
        return 1;
    }

}
