
import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;
import java.util.ArrayList;


/**
 * Created by estefania on 14/05/2017.
 */
@Path("/Manejo_Deportes")
public class SportController {

    Gson gson = new Gson();


    @GET

    @Path("/insertarDeporte")

    @Produces("application/json")

    //Agrega a la lista de deportes disponibles del usuario el id seleccionado
    public  String insertarSport(@QueryParam("idUsu") Integer nombreUsu,@QueryParam("idDep") Integer nombreDep){

        Boolean respuesta;

        String query = "select * from M05_insertarDeporte('"+nombreUsu+"','"+nombreDep+"')" ;

        try{
            Connection conn = conectarADb();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            return gson.toJson(true);

        } catch (Exception e) {
            return  e.getMessage();
        }

    }



    @GET

    @Path("/obtenerDeporte")

    @Produces("application/json")

    //Extrae el nombre de los deportes en funcion del id
    public String obtenerSport(@QueryParam("idDep") Integer id){


        Sport resultado = new Sport();

        //Declarando la sentencia de la funcion de obtenerDatosDeporte que devuelve todos los datos de los deportes
        String query = "select * from M05_obtenerdatosdeporte('"+id+"')";

        try{

            Connection conn = conectarADb();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){

                resultado.setId(rs.getInt(     "iddeporte"));
                resultado.setName(rs.getString("nombredeporte"));


            }

            return gson.toJson(resultado);

        } catch (Exception e) {

            return e.getMessage();

        }
    }

    @GET

    @Path("/obtenerMetDeporte")

    @Produces("application/json")

    //Devuelve a traves del nombre del deporte el met correspondiente al mismo
    //Con el fin de realizar el calculo de las calorias quemadas en la actividad
    public String obtenerMet(@QueryParam("nombreDep") String nombreDep ){


        Sport resultado = new Sport();

        //Declarando la sentencia de lafuncion obtenerMetDeporte que devuelve el met correspondiente al deporte consultado
        String query = "select * from M05_obtenermetdeporte('"+nombreDep.toUpperCase()+"')";

        try{

            Connection conn=conectarADb();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){

                resultado.setMet(rs.getFloat(  "metdeporte"));

            }

            return gson.toJson(resultado);

        } catch (Exception e) {

            return e.getMessage();

        }
    }
    @GET

    @Path("/obtenerDeportesUsuario")

    @Produces("application/json")

    //Devuelve el conjunto de deportes registrados que tiene un usuario
    public String obtenerDeportesUsuario(@QueryParam("idUsu") Integer id ){


        Sport resultado = new Sport();
        ArrayList<String> listaDeportes= new ArrayList<>();

        String query = "select nombredeporte from M05_obtenerdeportesusuario('"+id+"')";

        try{

            Connection conn = conectarADb();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            while(rs.next()){

                resultado.setName(rs.getString(  "nombredeporte"));
                listaDeportes.add(resultado.getName());
            }

            return gson.toJson(listaDeportes);

        } catch (Exception e) {

            return e.getMessage();

        }
    }

    @GET

    @Path("/eliminarDeportes")

    @Produces("application/json")

    public  String eliminarSport(@QueryParam("idUsu") Integer nombreUsu,@QueryParam("idDep") Integer nombreDep){

        Boolean respuesta;

        String query = "select  M05_eliminarDeporte('"+nombreUsu+"','"+nombreDep+"')" ;

        try{
            Connection conn = conectarADb();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            return gson.toJson(true);

        } catch (Exception e) {
            return  e.getMessage();
        }

    }


    private Connection conectarADb(){

        Connection conn = null;

        try {

            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/FitUcabDB";
            conn       = DriverManager.getConnection(url, "postgres",  "postgres");

        } catch (ClassNotFoundException e) {

            e.printStackTrace();
            System.exit(1);
        } catch (SQLException e) {

            e.printStackTrace();
            System.exit(2);
        }
        return conn;
    }

}

