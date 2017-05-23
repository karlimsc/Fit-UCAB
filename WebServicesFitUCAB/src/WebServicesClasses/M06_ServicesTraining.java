package WebServicesClasses;7

import Domain.Training;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;


@Path("/training")
public class M06_ServicesTraining {

    Gson gson = new Gson();

    //el tipo de instruccion HTTP
    @GET
    //el path de la funcion
    @Path("/createTraining")
    //formato de retorno
    @Produces("application/json")

    /***
     * Metodo utilizado a traves de web service para agregar los parametros a la base de datos
     * @param trainingName
     * @param trainingPeriod
     * @param trainingCalories
     * @return
     */
    public String createTraining(@QueryParam("trainingName") String name, @QueryParam("id") int id,
                                 @QueryParam("trainingPeriod") int period, @QueryParam("trainingCalories") int calories) {
        String query = "INSERT INTO TRAINING (NAME, ID, PERIOD, CALORIES) VALUES (name,id,period,calories)";

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

    /***
     * Metodo utilizado a traves de web service para visualizar los entrenamientos que posee el usuario
     * @return
     */


     public String getTraining(@QueryParam("userId") int userId) {
        int id = 0;
        String name = "";
        int period = 0;
        int calories = 0;
        String query = "SELECT TRAININGID, TRAININGNAME, TRAININGPERIOD, TRAININGCALORIES FROM TRAINING WHERE FK_USERID =" +userId;

        try {
            Connection conn = connectDb();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Training results = new Training();
            while (rs.next()) {

                results.setId(rs.getInt("trainingid"));
                results.setTrainingName(rs.getString("trainingname"));
                results.setTrainingPeriod(rs.getInt("trainingperiod"));
                results.setTrainingCalories(rs.getInt("trainingcalories"));


            }
            return gson.toJson(results);
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }

    public String deleteTraining (@QueryParam("id") int id) {
        
        String query = "DELETE FROM TRAINING WHERE TRAININGID =" +id;
        
        try{
            
            Connection conn = conectarADb();
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
