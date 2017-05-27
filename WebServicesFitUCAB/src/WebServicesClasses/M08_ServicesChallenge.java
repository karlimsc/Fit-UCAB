package WebServicesClasses;

import Domain.Challenge;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;

/**
 * Created by noe on 26/5/2017.
 */

@Path("/M08_Gestion_Retos")
public class M08_ServicesChallenge {

    private Connection conn =bdConnect();
    Gson gson = new Gson();




    /**
     * Metodo que es llamado a traves del web service para agregar a la base de datos los parametros recibidos
     * @param challengeName
     * @param challengeDescription
     * @param challengeLevel
     * @param challengeScore
     * @param challengePredefined
    * @return
     */

    @GET
    @Path("/insertChallenge")
    @Produces("application/json")
    /**
     * metodo para insertar un reto
     */
    public String insertChallenge(@QueryParam("challengeName") String challengeName,
                                  @QueryParam("challengeDescription") String challengeDescription,
                                  @QueryParam("challengeLevel") int challengeLevel,
                                  @QueryParam("challengeScore") int challengeScore,
                                  @QueryParam("challengePredefined") String challengePredefined)

        {//abre el metodo insertChallenge

            String insertChallengeQuery = "INSERT INTO CHALLENGE (CHALLENGENAME, CHALLENGEDESCRIPTION, CHALLENGELEVEL, CHALLENGESCORE, CHALLENGEPREDEFINED)" +
                "VALUES ( '" +challengeName+ "','" +challengeDescription+ "','" +0+ "','" +0+ "','n')";

        try{

            Statement st = conn.createStatement();
            st.executeUpdate(insertChallengeQuery);
            Challenge challenge = null;

            String idQuery="SELECT CHALLENGEID FROM CHALLENGE WHERE CHALLENGENAME='"+challengeName+"'";
            ResultSet rs = st.executeQuery(idQuery);


            int CHALLENGEID = 0;
            if ( rs.next()) {
                CHALLENGEID = rs.getInt("_id");
            }

            String insertDetailQuery = "INSERT INTO DETAIL (DETAILDATESTART, DETAILDATEEND , DETAILPOSITION , DETAILACTIVE ,FK_FRIENDSHIPID,FK_CHALLENGEID)" +
                    " VALUES (" ++" , "++" ,0, "+userId+" )";


            st.executeUpdate(insertRegistryQuery);
            user= new User(userId);
            return gson.toJson(user);

        }
        catch(Exception e) {
            return e.getMessage();
        }
    }












    /*
     * metodo para la conexion de la base de datos
     */
    public Connection bdConnect()
    {
        Connection conn = null;
        try
        {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost/fitucabdb";
            conn = DriverManager.getConnection(url,"fitucab", "fitucab");
        }//cierre del try

        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            System.exit(1);
        }//cierre del catch ClassNotFoundException

        catch (SQLException e)
        {
            e.printStackTrace();
            System.exit(2);
        }//cierre del catch SQLException
        return conn;
    }//cierre del metodo Connection


}//cierre de la clase M08_ServicesChallenge
