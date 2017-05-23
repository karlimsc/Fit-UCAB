package WebServicesClasses;



import Domain.Diet;
import Domain.Food;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.ws.rs.*;
import java.sql.*;


/**
 * Created by jaorr on 22/05/17.
 */

@Path("M11_Diet")
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
    @Path("/obtener_calorias_fecha")
    @Produces("application/json")
    public String ObtenerCaloriasPorFecha(@QueryParam("fecha") Date fecha ,
                                          @QueryParam("username") String username)
    {
        String query = "SELECT * FROM get_calorias_fecha(?, ?)";
        Diet diet = new Diet();
        JsonArray arregloJson = new JsonArray();
        try{

            PreparedStatement st = conn.prepareStatement(query);
            st.setDate(1, fecha);
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
    @Path("/eliminar_alimento_dieta")
    @Produces("application/json")
    public void ObtenerCaloriasPorFecha(@QueryParam("momento") String momento ,
                                        @QueryParam("username") String username)
    {
        String query = "SELECT elimina_alimento_dieta(?, ?)";
        try{

            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, momento);
            st.setString(2, username);
            ResultSet rs = st.executeQuery();
        }
        catch(Exception e) {
            e.getMessage();
        }
    }

    @GET
    @Path("Obtener_comida_momento")
    @Produces("application/json")
    public String obtenerComidaDeUnMomento(@QueryParam("momento") String momento,
                                           @QueryParam("fecha") Date fecha,
                                           @QueryParam("username") String username) {

        String query = "select * from get_comida_momento(?, ?, ?)";
        Diet diet = new Diet();
        Food food = new Food();
        JsonArray arregloJson = new JsonArray();

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, momento);
            st.setDate(2, fecha);
            st.setString(3, username);
            ResultSet rs = st.executeQuery();

            while(rs.next()){
                diet.set_calorie(rs.getInt("calorias"));
                food.setFoodName(rs.getString("nombre"));
                diet.set_food(food);
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
/*
    @GET
    @Path("calorias_consumidas_mes")
    @Produces("application/json")
    public String caloriasConsumidasMes(@QueryParam("username") String username){

        String query = "select * from get_calorias_mes(?)";
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
*/


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
