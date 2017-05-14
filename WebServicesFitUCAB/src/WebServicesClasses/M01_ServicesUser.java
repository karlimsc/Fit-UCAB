package WebServicesClasses;

/**
 * Created by root on 14/05/17.
 */
import Domain.User;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;
/* ---------------ESTA CLASE ESTA MAL --------------------------------------------*/

@Path("/M01_ServicesUser")

public class M01_ServicesUser {
    private Connection conn =conectarADb();
    Gson gson = new Gson();

    @GET
    @Path("/insertRegistry")
    @Produces("application/json")
    public String insertUser(@QueryParam("User") String user,@QueryParam("Password") String password,
                             @QueryParam("Email") String email,@QueryParam("Sex") String sex,@QueryParam("Phone") String phone,
                             @QueryParam("Ingreso") String ingreso, @QueryParam("Weight") String weight,
                             @QueryParam("Height")String height,@QueryParam("Registry Point") String registryPoint)
    {
        int id = incrementarId();
        String query= "INSERT INTO PERSON(PERSONID, PERSONUSERNAME, PERSONPASSWORD, PERSONEMAIL, PERSONSEX, PERSONPHONE, PERSONINGRESO) VALUES ('"+id+"','"+user+"','"+password+"','"+email+"','"+sex+"','"+phone+"','"+ingreso+"')";
        String queryDos="INSERT INTO REGISTRY (registryid, registryweight, registryheight, registrypoint, fk_personid) VALUES ('"+id+"','"+weight+"','"+height+"','"+registryPoint+"','"+id+"')";

        try{

            Statement st = conn.createStatement();
            st.executeUpdate(query);
            st.executeUpdate(queryDos);
            return gson.toJson(true);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }
    @GET
    @Path("/getUser")
    @Produces("application/json")
    public String getUser(@QueryParam("User") String user,@QueryParam("Password") String password)
    {
        String query="SELECT PERSONUSERNAME WHERE PERSONUSERNAME="+user+"AND PERSONPASSWORD ="+password;

        try{

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            User result = new User();
            while(rs.next()){

                result.setUser(rs.getString("name"));
                result.setPassword(rs.getString("Password"));
            }
            return gson.toJson(result);
        }
        catch(Exception e) {
            return e.getMessage();
        }
    }

    public int incrementarId()
    {
        int dameId=1;
        //esto es para incrementar
        return dameId;
    }

    public Connection conectarADb()
    {
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/ejemplo";
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
