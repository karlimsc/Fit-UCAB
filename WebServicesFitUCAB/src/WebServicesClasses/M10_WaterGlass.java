package WebServicesClasses;

import Domain.User;
import com.google.gson.Gson;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

/**
 *
 * Clase para el manejo de la hidratacion
 */
@Path("/M10_WaterGlass")
public class M10_WaterGlass
{

    Gson gson = new Gson();
    private Connection conn =bdConnect();
    DateFormat dateFormatter;



    @GET
    @Path("/addWater")
    @Produces("application/json")
    public String addWater(@QueryParam("time") String dia , @QueryParam("Fkg") String password
            , @QueryParam("Fkp") String fkp)
    {
        SimpleDateFormat formatter = new SimpleDateFormat("DD/mm/yyyy HH:mm:ss");
        Date date = null;
        try {
         date = formatter.parse(dia);
            
        } catch (ParseException e) {
            e.printStackTrace();
        }


        return gson.toJson(date);
    }



    @GET
    @Path("/a")
    @Produces("application/json")
    public String prueba()
    {
        String aa ="";

        try{

            String query= "SELECT glasshistoricid, fk_glass, fk_person, glasstime" +
                    "  FROM glass_historic where glasshistoricid = 5";

            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()){

             aa  =rs.getString("GLASSHISTORICID").toString();
            }

            return gson.toJson(aa);
        }
        catch(Exception e) {
            return e.getMessage();
        }



    }

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
