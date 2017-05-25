package WebServicesClasses;


import Domain.Food;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import javax.ws.rs.*;
import java.lang.reflect.Type;
import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by jaorr on 22/05/17.
 */
@Path("/M11_Food")
public class M11_ServicesFood {

    private Connection conn = bdConnect();
    //Atributo que se utiliza para transformar a formado JSON las consultas.
    private Gson gson = new Gson();
    private String respuesta;
    /**
     * Funcion que recibe el nombre del usuario, y con este extrae
     * la informacion de los alimentos que ha consumido el usuario.
     * @param username
     * @return
     */
    @GET
    @Path("/obtener_alimentos_personalizados")
    @Produces("application/json")
    public String ObtenerAlimento(@QueryParam("username") String username) throws SQLException {
        String query = "SELECT * FROM get_alimentos_person(?)";

        Food food = new Food();
        JsonArray arregloJson = new JsonArray();
        try{
            PreparedStatement stm = conn.prepareStatement(query);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();

            while(rs.next()){
                food.setFoodName(rs.getString("nombre_comida"));
                food.setFoodWeight(rs.getString("peso_comida"));
                food.setFoodCalorie(rs.getString("calorias_comida"));
                food.setId(rs.getInt("id_alimento"));
                arregloJson.add(gson.toJson(food));
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
     *
     * @param username
     * @return
     */
    @GET
    @Path("obtener_todos_alimentos")
    @Produces("application/json")
    public String obtenerTodosAlimentos(@QueryParam("username") String username){

        String query = "select * from get_todos_alimentos(?)";
        Food food = new Food();
        JsonArray arregloJson = new JsonArray();

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                food.setFoodName(rs.getString("nombre_comida"));
                // revisar string
                food.setFoodCalorie(rs.getString("calorias_comida"));
                food.setFoodWeight(rs.getString("peso_comida"));
                food.setId(rs.getInt("id_alimento"));
                arregloJson.add(gson.toJson(food));
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
     * @param calorie
     * @return
     */
    @GET
    @Path("obtener_sugerencia")
    @Produces("application/json")
    public String obtenersugerencia(@QueryParam("username") String username,
                                    @QueryParam("calorie") int calorie){

        String query = "select * from get_alimentos_sugerencia(?, ?)";
        Food food = new Food();
        JsonArray arregloJson = new JsonArray();

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            st.setInt(2, calorie);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                food.setFoodName(rs.getString("nombre_comida"));
                // revisar string
                food.setFoodCalorie(rs.getString("calorias_comida"));
                food.setFoodWeight(rs.getString("peso_comida"));
                food.setId(rs.getInt("id_alimento"));
                arregloJson.add(gson.toJson(food));
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
    @Path("obtener_todoas_alimentos_auto")
    @Produces("application/json")
    public String obtenerAlimentosAuto(@QueryParam("username") String username){

        String query = "select * from get_todos_alimentos_autocompletar(?)";
        Food food = new Food();
        JsonArray arregloJson = new JsonArray();

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();

            while(rs.next()) {
                food.setFoodName(rs.getString("nombre_comida"));
                // revisar string
                food.setFoodCalorie(rs.getString("calorias_comida"));
                food.setFoodWeight(rs.getString("peso_comida"));
                food.setId(rs.getInt("id_alimento"));
                arregloJson.add(gson.toJson(food));
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
     * @param alimento
     * @return
     */

    @DELETE
    @Path("eliminar_personalizado")
    @Produces("application/json")
    public String eliminarAlimentoPersonalizado(@QueryParam("alimento") String alimento){
        String query = "select * from elimina_alimento_person(?)";
        Map<String, String> respuesta = new HashMap<String, String>();

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, alimento);
            st.executeQuery();
            respuesta.put("data", "Se elimino el alimento de forma exitosa");
        }
        catch (SQLException e){
            respuesta.put("data", e.getMessage());
        }
        finally {
            bdClose();
            return gson.toJson(respuesta);
        }
    }

    /**
     *
     * @param alimento
     * @param peso
     * @param calorie
     * @return
     */

    @GET
    @Path("actualizar_personalizado")
    @Produces("application/json")
    public String actualizarPersonalizado(@QueryParam("alimento") String alimento,
                                          @QueryParam("peso") double peso,
                                          @QueryParam("calorie") int calorie){

        String query = "select * from act_alimento_person(?, ?, ?)";
        Map<String, String> respuesta = new HashMap<String, String>();

        try {
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, alimento);
            st.setString(2, String.valueOf(peso));
            st.setInt(3, calorie);
            st.executeQuery();
            respuesta.put("data", "Se actualizo el alimento de forma exitosa");
        }
        catch (SQLException e){
            respuesta.put("data", e.getMessage());
        }
        finally {
            return gson.toJson(respuesta);
        }
    }

    /**
     *
     * @param jsonAlimentos
     * @return
     */

    @POST
    @Path("insertar_personalizado")
    @Produces("application/json")
    public String insertarAlimentoPersonlizado(@QueryParam("alimentos") String jsonAlimentos){

        String query = "select * from inserta_alim_person(? , ?, ?)";
        Map<String, String> respuesta = new HashMap<String, String>();
        Type type = new TypeToken<Food[]>(){}.getType();
        /*
        Food[] a = new Food[3];
        a[0] = new Food(1, "cachapa", "20", "52", true);
        a[2] = new Food(2, "cachap2", "21", "53", true);
        a[1] = new Food(3, "cachap3", "22", "54", true);
        jsonAlimentos = gson.toJson(a);
        */
        Food[] alimentos = gson.fromJson(jsonAlimentos, type);
        try {
            PreparedStatement st = conn.prepareStatement(query);
            for (int i = 0; i < alimentos.length; i++) {
                st.setString(1, alimentos[i].getFoodName());
                st.setInt(2, Integer.parseInt(alimentos[i].getFoodWeight()));
                st.setInt(3, Integer.parseInt(alimentos[i].getFoodCalorie()));
                st.executeQuery();
            }

            respuesta.put("data", "Se insertaron los alimentos de forma exitosa");
        }
        catch (SQLException e) {
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
