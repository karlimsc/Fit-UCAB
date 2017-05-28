package WebServicesClasses;

import Domain.Sport;
import Domain.Training;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;
import java.util.ArrayList;


@Path("/M06_ServicesTraining")
public class M06_ServicesTraining {

    Gson gson = new Gson();

    //el tipo de instruccion HTTP
    @GET
    //el path de la funcion
    @Path("/createTraining")
    //formato de retorno
    @Produces("application/json")

    /**
     * Metodo utilizado a traves de web service para agregar los parametros a la base de datos
     * @param trainingName
     * @param trainingPeriod
     * @param trainingCalories
     * @return
     */
    public String createTraining(@QueryParam("trainingName") String name,
                                 @QueryParam("trainingPeriod") int period,
                                 @QueryParam("trainingCalories") int calories) {

        String query = "INSERT INTO TRAINING (NAME, PERIOD, CALORIES) VALUES (name,period,calories)";

        try {
            Connection conn = connectDb();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return gson.toJson(true);
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @GET
    @Path("/createPersonalizedTraining")
    @Produces ("application/json")
    

    /**
    * Metodo utilizado a traves de web service para agregar un ejercicio personalizado  a la base de datos con los parametros
    * @param trainingName
    * @param trainingPeriod 
    * @param trainingCalories
    * @param typeComplexity
    * @param timeVar
    * @param sportId
    * @return
    */

    public String createPersonalizedTraining (@QueryParam("trainingName") String name, @QueryParam("trainingPeriod") int period,
                                              @QueryParam("trainingCalories") int calories, @QueryParam("typeComplexity") String complexity,
                                              @QueryParam("timeVar") int time,@QueryParam("fk_sportId") int sportId) {
        String query = "INSERT INTO TRAINING (NAME, PERIOD, CALORIES) VALUES (" +name+ "," +period+ "," +calories+")";
        String query2 = "INSERT INTO SPOR_TRAINING VALUES( nextval('SPOR_TRAININGID') ," +complexity+ "," +time+ ", NULL ," +sportId+ ", nextval('trainingid')-1 );"
        try {
            Connection conn = connectDb();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return gson.toJson(true);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @GET
    @Path("/updatePersonalizedTraining")
    @Produces("application/json")

    /**
    * Metodo utilizado a traves de webservice para cambiar los parametros de un entrenamiento existente en la base de datos
    * @param idTraining
    * @param trainingName
    * @param traningPeriod
    * @param trainingCalories
    * @param typeComplexity
    * @param timeVar
    * @param distance
    * @param sportId    
    * @return
    */

    public String updatePersonalizedTraining (@QueryParam("idTraining") int id, @QueryParam("trainingName") String name, 
                                              @QueryParam("trainingPeriod") int period, @QueryParam("trainingCalories") int calories,
                                              @QueryParam("typeComplexity") String complexity, @QueryParam("timeVar") int time, @QueryParam("distance") int distance
                                              @QueryParam("sportId") int sportId) {
        String query = "UPDATE TRAINING SET TRAININGNAME=" +name+ ", TRAININGPERIOD=" +period+ ", TRAININGCALORIES=" +calories+ "WHERE TRAININGID=" +id;
        String query2 = "UPDATE SPOR_TRAINING SET TYPECOMPLEXITY=" +complexity+", TIMETRAINING=" +period+ ", DISTANCE=" +calories+ "WHERE FK_SPORTID=" +sportId+ "AND FK_TRAININGID=" +id;
        try {
            Connection conn = connectDb();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return gson.toJson(true);
        } catch (Exception e) {
            return e.getMessage();
        }
    }



    @GET
    @Path("/updateTraining")
    @Produces("application/json")

    /**
    * Metodo utilizado a traves de webservice para cambiar los parametros de un entrenamiento existente en la base de datos
    * @param idTraining
    * @param trainingName
    * @param traningPeriod
    * @param trainingCalories
    * @return
    */

    public String updateTraining (@QueryParam("idTraining") int id,
                                  @QueryParam("trainingName") String name,
                                  @QueryParam("trainingPeriod") int period,
                                  @QueryParam("trainingCalories") int calories) {

        String query = "UPDATE TRAINING " +
                        "SET TRAININGNAME=" +name+ ", TRAININGPERIOD=" +period+
                        ", TRAININGCALORIES=" +calories+ "WHERE TRAININGID=" +id;

        try {
            Connection conn = connectDb();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            return gson.toJson(true);
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    @GET
    @Path("/displayTraining")
    @Produces("application/json")


    /**
     * Metodo utilizado a traves de web service para visualizar los entrenamientos que posee el usuario
     * @param userId
     * @return
     */


     public String getTraining(@QueryParam("userId") int userId) {

        String query = "SELECT * from M06_obtenerEntrenamientos("+ userId +")";

        try {
            Connection conn = connectDb();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Training results = new Training();
            ArrayList<Training> training = new ArrayList<Training>();
            while (rs.next()) {

                results.setId(rs.getInt("trainingid"));
                results.setTrainingName(rs.getString("trainingname"));
                results.setTrainingPeriod(rs.getInt("trainingperiod"));
                results.setTrainingCalories(rs.getInt("trainingcalories"));
                training.add(results);

            }

            return gson.toJson(training);

        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @GET
    @Path("/detailedTraining")
    @Produces("application/json")

    /**
    * Metodo utilizado a traves de webservice para visualizar los detalles de un entrenamiento especifico
    * trainingId
    * @return
    */

    public String getDetailedTraining(@QueryParam("trainingId") int trainingId) {
        String query = "SELECT SPORTNAME " +
                "FROM SPORT, SPOR_TRAINING, TRAINING" + " " +
                "WHERE ( (TRAININGID = " + trainingId+ ") AND (FK_TRAININGID = TRAININGID) AND (FK_SPORTID = SPORTID))";
        try {
            Connection conn = connectDb();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Sport results = new Sport();
            ArrayList<Sport> sport = new ArrayList<Sport>();
            while (rs.next()) {

                results.setName(rs.getString("sportName"));
                sport.add(results);

            }

            return gson.toJson(sport);

        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    @GET
    @Path("/createPredefinedTrainingTime")
    @Produces("application/json")

    /**
     * Metodo para insertar un entrenamiento predefinido con tiempo
     */
    public String createPredefinedTrainingTime(@QueryParam( "nombreEntrenamiento" ) String nombreEntrenamiento,
                                               @QueryParam( "nivelEntrenamiento"  ) String nivelEntrenamiento,
                                               @QueryParam( "tipoEntrenamiento"   ) String tipoEntrenamiento,
                                               @QueryParam( "calorias"            ) String caloriasEntrenamiento,
                                               @QueryParam( "periodicidad"        ) String periodicidad,
                                               @QueryParam( "tiempo"              ) String tiempo,
                                               @QueryParam( "deporte"             ) String deporte,
                                               @QueryParam( "userId"              ) String userId                ) {

        String query = "INSERT INTO TRAINING (TRAININGID , TRAININGNAME, TRAININGPERIOD,TRAININGCALORIES,FK_USERID)" +
                "VALUES (nextval('TRAININGID'), '"+ nombreEntrenamiento+"', " + periodicidad + ", " +caloriasEntrenamiento+ ","+ userId+") " ;

        try {
            //Se inserta el entrenamiento
            Connection conn = connectDb();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.close();
            //Se inserta el spor_training
            query = "INSERT INTO SPOR_TRAINING (SPOR_TRAININGID, TYPE_COMPLEXITY, TIMETRAINING, FK_SPORTID, FK_TRAININGID) "
                    +"VALUES (nextval( 'SPORT_TRAININGID' ), '" +nivelEntrenamiento+ "' , "+ tiempo +", "+ deporte +", (SELECT last_value FROM trainingid) )";
            conn = connectDb();
            st = conn.createStatement();
            st.executeUpdate(query);
            conn.close();

            return gson.toJson(true);
        } catch (Exception e) {

            return e.getMessage();

        }


    }

    @GET
    @Path("/createPredefinedTrainingKilometros")
    @Produces("application/json")

    /**
     * Metodo para insertar un entrenamiento predefinido con KILOMETROS
     */
    public String createPredefinedTrainingDistance(@QueryParam( "nombreEntrenamiento" ) String nombreEntrenamiento,
                                               @QueryParam( "nivelEntrenamiento"  ) String nivelEntrenamiento,
                                               @QueryParam( "tipoEntrenamiento"   ) String tipoEntrenamiento,
                                               @QueryParam( "calorias"            ) int caloriasEntrenamiento,
                                               @QueryParam( "periodicidad"        ) int periodicidad,
                                               @QueryParam( "kilometros"          ) int kilometros,
                                               @QueryParam( "deporte"             ) int deporte,
                                               @QueryParam( "userId"              ) int userId                ) {

        String query = "INSERT INTO TRAINING (TRAININGID , TRAININGNAME, TRAININGPERIOD,TRAININGCALORIES,FK_USERID)" +
                "VALUES (nextval('TRAININGID'), '"+ nombreEntrenamiento+"', " + periodicidad + ", " +caloriasEntrenamiento+ ","+ userId+") " ;

        try {
            //Se inserta el entrenamiento
            Connection conn = connectDb();
            Statement st = conn.createStatement();
            st.executeUpdate(query);
            conn.close();

            //Se inserta el spor_training
            query = "INSERT INTO SPORT_TRAINING (SPOR_TRAININGID, TYPE_COMPLEXITY, DISTANCE, FK_SPORTID, FK_TRAININGID) "
                    +"VALUES (nextval( 'SPORT_TRAININGID' ), '" +nivelEntrenamiento+ "' , "+ kilometros +", "+ deporte +", SELECT last_value FROM trainingid)";
            conn = connectDb();
            st = conn.createStatement();
            st.executeUpdate(query);
            conn.close();


            return gson.toJson(true);
        } catch (Exception e) {

            return e.getMessage();

        }


    }

    @DELETE
    @Path("/deleteTraining")
    
    /**
    * Metodo encargado de borrar un entrenamiento relacionado con un usuario al recibir el identificador
    * @param id 
    * @return
    */

    public String deleteTraining (@QueryParam("id") int id) {
        
        String query = "DELETE FROM TRAINING WHERE TRAININGID =" +id;
        
        try{
            
            Connection conn = connectDb();
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery(query);

            return gson.toJson(true);

        } catch (Exception e) {
            return  e.getMessage();
        }
    }

    /**
     * Metodo que sirve para crear el conector a la base de datos
     *
     * @return un conector para hacer llamadas a la BD
     */
    private Connection connectDb() {
        Connection conn = null;
        try {
            //llamada al driver de PostgreSQL
            Class.forName("org.postgresql.Driver");
            //String de conexion a la db:
            String url = "jdbc:postgresql://localhost/fitucabdb";

            conn = DriverManager.getConnection(url, "fitucab", "fitucab");
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
