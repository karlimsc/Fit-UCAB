package WebServicesClasses;

import Domain.User;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;

/**
 * Clase de Servicios Web del Modulo 01
 */
@Path("/M01_ServicesUser")
public class M01_ServicesUser {

    private Connection conn =bdConnect();
    Gson gson = new Gson();

    /**
     * Metodo que es llamado a traves del web service para agregar a la base de datos los parametros recibidos
     * @param user
     * @param password
     * @param email
     * @param sex
     * @param phone
     * @param weight
     * @param height
     * @param registryPoint
     * @return
     */


    @GET
    @Path("/insertRegistry")
    @Produces("application/json")
    public String insertUser(@QueryParam("User") String user,@QueryParam("Password") String password,
                             @QueryParam("Email") String email,@QueryParam("Sex") String sex,@QueryParam("Phone") String phone,
                             @QueryParam("Weight") String weight,@QueryParam("Height")String height,@QueryParam("Registry Point") String registryPoint)
    {
        int id = idIncrease();
        String query= "INSERT INTO PERSON(PERSONID, PERSONUSERNAME, PERSONPASSWORD, PERSONEMAIL, PERSONSEX, PERSONPHONE, PERSONINGRESO) VALUES ('"+id+"','"+user+"','"+password+"','"+email+"','"+sex+"','"+phone+"')";
        String query2="INSERT INTO REGISTRY (registryid, registryweight, registryheight, registrypoint, fk_personid) VALUES ('"+id+"','"+weight+"','"+height+"','"+registryPoint+"','"+id+"')";

        try{

            Statement st = conn.createStatement();
            st.executeUpdate(query);
            st.executeUpdate(query2);
            return gson.toJson(true);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }


    @GET
    @Path("/helloWorld")
    @Produces("application/json")
    public String prueba()
    {
        return ("hola mundo");
    }


    /***
     * Metodo que es llamado a traves del web service para consultar un usuario existente en la base de datos
     * @param user
     * @param password
     * @return
     */
    @GET
    @Path("/getUser")
    @Produces("application/json")
    public String getUser(@QueryParam("User") String user,@QueryParam("Password") String password)
    {
        String query="SELECT PERSONUSERNAME FROM PERSON WHERE PERSONUSERNAME= '" + user + "' AND PERSONPASSWORD = '" + password + "'";

        try{

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            User result = new User();
            while(rs.next()){

                result.setUser(rs.getString("PERSONUSERNAME"));

            }
            return gson.toJson(result);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Metodo que es llamado en otros metodos para obtener el ultimo id e incrementarlo
     * @return devuelve un numero incrementado
     */
    public int idIncrease()
    {
        int Id=1;
        //esto es para incrementar, no implemenrado por ahora
        return Id;
    }


    //esto no va a aqui , se puso momentaneamente.
    public Connection bdConnect()
    {
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/FitUcabDB";
            conn = DriverManager.getConnection(url,"postgres", "root");
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
