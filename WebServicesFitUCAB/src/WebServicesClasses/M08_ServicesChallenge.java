package WebServicesClasses;

import Domain.Activity;
import Domain.Challenge;
import Domain.DetailChallenge;
import Domain.User;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.sql.*;
import java.util.ArrayList;

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
     * @param challengeScore
     * @param challengePredefined
    * @return
     */

    @POST
    @Path("/insertChallenge")
    @Produces("application/json")
    /**
     * metodo para insertar un reto
     */
    public String insertChallenge(@QueryParam("userId") int userId,
                                  @QueryParam("challengeName") String challengeName,
                                  @QueryParam("challengeDescription") String challengeDescription,
                                  @QueryParam("challengeScore") int challengeScore,
                                  @QueryParam("challengePredefined") String challengePredefined,
                                  @QueryParam("detailInitDay") String detailInitDay,
                                  @QueryParam("detailEndDay") String detailEndDay)


    {//abre el metodo insertChallenge

        /*
        String insertChallengeQuery = "INSERT INTO CHALLENGE (CHALLENGENAME, CHALLENGEDESCRIPTION,CHALLENGESCORE, " +
                "CHALLENGEPREDEFINED)" +
                "VALUES ( '" + challengeName + "','" + challengeDescription + "','" + 0 + "','n')";
        */
        String insertChallengeQuery = "INSERT INTO CHALLENGE (CHALLENGENAME, CHALLENGEDESCRIPTION,CHALLENGESCORE, " +
                "CHALLENGEPREDEFINED)" +
                "VALUES ( 'noe','reto 1',12,'n')";
        try {

            Statement st = conn.createStatement();
            st.executeUpdate(insertChallengeQuery);
            Challenge challenge = null;

            String idQuery = "SELECT CHALLENGEID FROM CHALLENGE WHERE CHALLENGENAME='" + challengeName + "'";
            ResultSet rs = st.executeQuery(idQuery);


            int CHALLENGEID = 0;
            if (rs.next()) {
                CHALLENGEID = rs.getInt("_id");
            }

            String insertDetailQuery = "INSERT INTO DETAIL (DETAILDATESTART, " +
                    "DETAILDATEEND, DETAILACTIVE ,FK_FRIENDSHIPID," +
                    "FK_CHALLENGEID)" +
                    " VALUES (" + detailInitDay + ", " + detailEndDay + " ," + "'n'" + ", " + userId
                    + "," + CHALLENGEID + " )";

            st.executeUpdate(insertDetailQuery);


            return gson.toJson("Reto Agregado Exitosamente");

        } catch (Exception e) {
            return e.getMessage();
        }
    }
            @GET
            @Path("/getPredefinedChallenges")
            @Produces("application/json")
            /**
             * Carga todos los retos predefinidos
             * @return lista de retos
             */

            public String getPredefinedChallenges(){

            String query = "select * from CHALLENGE where CHALLENGEPREDEFINED = 'n'";

            Challenge resultado = new Challenge();

            //Array creado para almacenar los retos predefinidos
            ArrayList<Challenge> challengeList = new ArrayList<>();

            try{
                Statement   st  = conn.createStatement();
                ResultSet   rs  =  st.executeQuery(query);

                while(rs.next()){

                    resultado.set_challengeName         (rs.getString("CHALLENGENAME"));
                    resultado.set_challengeDescription  (rs.getString("CHALLENGEDESCRIPTION"));
                    resultado.set_challengeScore        (rs.getInt("CHALLENGESCORE"));
                    resultado.set_challengeId           (rs.getInt( "CHALLENGEID"));
                    resultado.set_challengeKilometers   (rs.getInt( "CHALLENGEKILOMETERS"));
                    resultado.set_challengeType         (rs.getString("CHALLENGETYPE"));
                    resultado.set_challengePredefined   ('n');

                    challengeList.add(resultado);

                }
                return gson.toJson(challengeList);

            } catch (Exception e) {
                return e.getMessage();
            }
        }

    @GET
    @Path("/getPersonalChallenges")
    @Produces("application/json")
    /**
     * Carga todos los retos (no predefinidos) donde se encuentre el usuario
     * @return
     */

    public String getUserChallenge(@QueryParam("userId") int userId){

        String query = "select CHALLENGENAME,CHALLENGEDESCRIPTION,CHALLENGESCORE" +
                ",CHALLENGEID, CHALLENGEKILOMETERS, CHALLENGETYPE from DETAIL,CHALLENGE where FK_FRIENDSHIPID = "+userId+" " +
                "and CHALLENGEID = FK_CHALLENGEID" +
                " and  CHALLENGEPREDEFINED = 's'";

        Challenge resultado = new Challenge();

        //Array creado para almacenar los retos no predefinidos
        ArrayList<Challenge> challengeList = new ArrayList<>();

        try{
            Statement   st  = conn.createStatement();
            ResultSet   rs  =  st.executeQuery(query);

            while(rs.next()){

                resultado.set_challengeName         (rs.getString("CHALLENGENAME"));
                resultado.set_challengeDescription  (rs.getString("CHALLENGEDESCRIPTION"));
                resultado.set_challengeScore        (rs.getInt("CHALLENGESCORE"));
                resultado.set_challengeId           (rs.getInt( "CHALLENGEID"));
                resultado.set_challengeKilometers   (rs.getInt( "CHALLENGEKILOMETERS"));
                resultado.set_challengeType         (rs.getString("CHALLENGETYPE"));
                resultado.set_challengePredefined   ('s');

                challengeList.add(resultado);

            }
            return gson.toJson(challengeList);

        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Metodo para obtener los detalles de un reto por persona
     * @param userId
     * @param challengeId
     * @return
     */
    public String getChallengeDetail(@QueryParam("userId") int userId,
                                     @QueryParam("challengeId") int challengeId){

        String query = "select * from DETAILS where FK_FRIENDSHIPID = "
                +userId+" and FK_CHALLENGE = "+challengeId+"";

        DetailChallenge resultado = new DetailChallenge();

        try{
            Statement   st  = conn.createStatement();
            ResultSet   rs  =  st.executeQuery(query);

            if(rs.next()){

                resultado.set_detailId          (rs.getInt("DETAILID"));
                resultado.set_detailDateStart   (rs.getDate("DETAILDATESTART"));
                resultado.set_detailDateEnd     (rs.getDate("DETAILDATEEND"));
                resultado.set_detailActive      (rs.getBoolean("DETAILACTIVE"));
                resultado.set_detailIdUser      (rs.getInt("FK_FRIENDSHIPID"));
                resultado.set_detailIdChallenge (rs.getInt("FK_CHALLENGEID"));

            }
            return gson.toJson(resultado);

        } catch (Exception e) {
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
