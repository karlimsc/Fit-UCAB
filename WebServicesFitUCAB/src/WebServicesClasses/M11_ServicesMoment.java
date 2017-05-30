package WebServicesClasses;


import Domain.Moment;
import Domain.Sql;
import Exceptions.ParameterNullException;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.sql.*;
import java.util.ArrayList;


/**
 * Created by jaorr on 22/05/17.
 */

@Path("M11_Moment")
public class M11_ServicesMoment {

    private Connection conn = Sql.getConInstance();
    private Gson gson = new Gson();
    private String response;
    private ArrayList<Moment> jsonArray;


    /**
     * Funcion que devulve los momentos del dia registrados en la BD
     * @return Lista de momentos en formato json
     */
    @GET
    @Produces("application/json")
    public String obtenerMomentos() {

        String query = "Select * from m11_get_momentos()";
        jsonArray = new ArrayList<>();
        try {
            PreparedStatement st = conn.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                jsonArray.add(new Moment());
                jsonArray.get(rs.getRow() - 1).set_description(rs.getString("momento"));
                jsonArray.get(rs.getRow() - 1).set_id(rs.getInt("momento_id"));
            }
            response = gson.toJson(jsonArray);

        } catch (SQLException e) {
            response =  e.getMessage();
        }
        catch (ParameterNullException e){
            response = e.getMessage();
        }

        finally {
            Sql.bdClose(conn);
            return response;
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
