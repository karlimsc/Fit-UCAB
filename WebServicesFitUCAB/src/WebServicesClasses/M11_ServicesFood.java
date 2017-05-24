package WebServicesClasses;


import Domain.Food;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import javax.ws.rs.*;
import java.sql.*;
import java.sql.Date;


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

    @GET
    @Path("obtener_sugerencia")
    @Produces("application/json")
    public String obtenersugerencia(@QueryParam("username") String username){

        String query = "select * from get_alimentos_sugerencia(?)";
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
