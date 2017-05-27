package WebServicesClasses;

import Domain.Sql;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by andres on 13/05/17.
 */
@Path("/")
public class probarDBService {

    @GET
    @Produces("text/plain")
    public String bienvenida(){

        System.out.println("-------- PostgreSQL "
                + "JDBC Connection Testing ------------");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            e.printStackTrace();
            return "Where is your PostgreSQL JDBC Driver?";

        }

        System.out.println("PostgreSQL JDBC Driver Registered!");

        Sql sql = new Sql();

        String query = "SELECT * FROM public.person where (personid = 1);";
        ResultSet rs = null;
        try {
            rs = sql.sql(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Gson gson = new Gson();


        try {
            rs.next();
            return gson.toJson(rs.getString("personusername"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  "0";
    }


}
