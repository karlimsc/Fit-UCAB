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
     * @param username
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

    public String insertUser(@QueryParam("username") String username,@QueryParam("password") String password,
                             @QueryParam("email") String email,@QueryParam("sex") String sex,
                             @QueryParam("phone") String phone,
                             @QueryParam("weight") String weight,@QueryParam("height")String height,
                             @QueryParam("registryPoint") String registryPoint)
    {
        int id = idIncrease();
        String query= "INSERT INTO PERSON(PERSONID, PERSONUSERNAME, PERSONPASSWORD, PERSONEMAIL, PERSONSEX, PERSONPHONE, "
                      + "PERSONINGRESO) VALUES ("+id+",'"+username+"','"+password+"','"+email+"','"+sex+"','"+phone+"')";
        String query2="INSERT INTO REGISTRY (registryid, registryweight, registryheight, registrypoint, fk_personid)" +
                       " VALUES ("+id+",'"+weight+"','"+height+"','"+registryPoint+"','"+id+"')";

        return query;
        /*
        try{

            Statement st = conn.createStatement();
            st.executeUpdate(query);
            st.executeUpdate(query2);
            return gson.toJson(true);
        }
        catch(Exception e) {
            return e.getMessage();
        }
        */
    }

    /**
     * Metodo que es llamado a traves del web service para consultar un usuario existente en la base de datos
     * @param userparam
     * @param passwordparam
     * @return el usuario con los datos que trae la consulta
     */
    @GET
    @Path("/getUser")
    @Produces("application/json")

    public String getUser(@QueryParam("username") String userparam,@QueryParam("password") String passwordparam)
    {
        String query="SELECT * FROM PERSON WHERE PERSONUSERNAME= '" + userparam + "' " +
                     "AND PERSONPASSWORD = '" + passwordparam + "'";

        try{

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            User user= null;

            while(rs.next()){

                String username = rs.getString("PERSONUSERNAME");
                int id = rs.getInt("PERSONID");
                String password = rs.getString("PERSONPASSWORD");
                String sexo= rs.getString("PERSONSEX");
                String phone= rs.getString("PERSONPHONE");
                String email= rs.getString("PERSONEMAIL");
                Date birtdate= rs.getDate("PERSONBIRTHDATE");

                user= new User(id,username,password,email,sexo,phone,birtdate);

            }
            return gson.toJson(user);
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
    @GET
    @Path("/helloWorld")
    @Produces("application/json")
    public String prueba()
    {
        String query="SELECT * FROM PERSON";

        try{

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            User user= null;
            while(rs.next()){
                String username = rs.getString("PERSONUSERNAME");
                int id = rs.getInt("PERSONID");
                String password = rs.getString("PERSONPASSWORD");
                String sexo= rs.getString("PERSONSEX");
                String phone= rs.getString("PERSONPHONE");
                String email= rs.getString("PERSONEMAIL");
                Date birtdate= rs.getDate("PERSONBIRTHDATE");

                user= new User(id,username,password,email,sexo,phone,birtdate);
            }
            return gson.toJson(user);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }

    //esto no va a aqui , se puso momentaneamente.
    public Connection bdConnect()
    {
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/fitucabdb";
            conn = DriverManager.getConnection(url,"fitucab", "fitucab");
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
