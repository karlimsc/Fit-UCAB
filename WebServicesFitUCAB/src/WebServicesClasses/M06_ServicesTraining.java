package WebServicesClasses;

import Domain.Training;

import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;

/**
 * Created by LuisMiguel on 18/05/2017.
 */
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
    public String getTraining(@QueryParam("id") int id) {
        String name = "";
        int period = 0;
        int calories = 0;
        String query = "SELECT * FROM TRAINING";
        Training results = new Training(id, name, period, calories);
        try {
            Connection conn = connectDb();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {

                results.setId(rs.getInt("id"));
                results.setTrainingName(rs.getString("trainingName"));
                results.setTrainingPeriod(rs.getInt("trainingPeriod"));
                results.setTrainingCalories(rs.getInt("trainingCalories"));

            }
            return gson.toJson(results);
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Metodo que sirve para crear el conector a la base de datos
     *
     * @return un conector para hacer llamadas a la BD
     */
    // PARA HACER PRUEBAS!!
    private Connection connectDb() {
        Connection conn = null;
        try {
            //llamada al driver de PostgreSQL
            Class.forName("org.postgresql.Driver");
            //String de conexion a la db:
            String url = "jdbc:postgresql://localhost/FitUcabDB";

            conn = DriverManager.getConnection(url, "FitUcab", "fitucab");
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
