import com.google.gson.Gson;
import sun.rmi.runtime.Log;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;

// El path del servicio sera "/ejemploDB"
@Path("/ejemploDB")
public class EjemploDB {

    Gson gson = new Gson();

    //tipo de HTTP que será
    @GET
    //path de la funcion
    @Path("/pruebaInsertar")
    //El tipo MIME que retorna
    @Produces("application/json")
    public String insertarPersona(@QueryParam("nombre") String nombre, @QueryParam("id") String id, @QueryParam("fin") String apellido){

        String query="INSERT INTO SPORT (nombre, ID, fin) VALUES('"+nombre+"',"+ id+",'"+ apellido+"')";

        try{
            Connection conn = conectarADb();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return gson.toJson(true);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }


    @GET
    @Path("/pruebaObtener")
    @Produces("application/json")
    public String obtenerPersona(@QueryParam("id") String id){
        System.out.print("PRUEBA OBTENER");
        String nombre="";
        String apellido="";
        String query="SELECT * FROM " + "SPORT" + " WHERE ID= "+id;
        persona resultado = new persona();
        try{
            Connection conn = conectarADb();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){

                resultado.setId(rs.getInt("id"));
                resultado.setNombre(rs.getString("nombre"));
            }
            return gson.toJson(resultado);
        }
        catch(Exception e) {
            System.out.print(e.getMessage());
            return e.getMessage();
        }
    }


    /**
     * Metodo que crea el conector de la base de datos
     * @return un conector para hacer llamadas a la BD
     */
    private Connection conectarADb()
    {
        System.out.print("OBTENER");
        Connection conn = null;
        try
        {
            //llamo al driver de Postgre (el primer import que muestro en el video)
            Class.forName("org.postgresql.Driver");
            //el string de conexion de la db el formato es el siguiente:
            //jdbc:postgresql://HOST//NOMBRE_DE_LA_DB
            String url = "jdbc:postgresql:webservice";
            //parametros de la conexion que basicamente es el usuario en mi caso es postgres y la clave es root
            // NO DEBEN DEJAR ESTO ASI POR DEFECTO
            conn = DriverManager.getConnection(url,"postgres", "8888");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(2);
        }
        return conn;
    }
}
