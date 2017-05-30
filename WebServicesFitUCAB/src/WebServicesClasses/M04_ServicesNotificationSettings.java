package WebServicesClasses;

import Domain.Notification_Settings;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;


/**
 * Clase de Servicios Web del Modulo 04
 */
@Path("/M04_ServicesNotificationSettings")
public class M04_ServicesNotificationSettings {

    private Connection conn =bdConnect();
    Gson gson = new Gson();

    /**
     * Metodo que es llamado a traves del web service para agregar a la base de datos los parametros recibidos
     * @param preferenceFriends
     * @param preferenceActivity
     * @param preferenceTraining
     * @param preferenceChallenges
     * @param preferenceHydration
     * @param preferenceCalories
     * @param preferenceGamification
     * @param preferenceLanguage
     * @param preferenceUnit
     * @param preferenceRadius
     * @param userId
     * @return
     */

    @GET
    @Path("/insertSetting")
    @Produces("application/json")
    public String insertSetting(@QueryParam("preferenceFriends") boolean preferenceFriends,
                             @QueryParam("preferenceActivity") boolean preferenceActivity,
                             @QueryParam("preferenceTraining") boolean preferenceTraining,
                             @QueryParam("preferenceChallenges") boolean preferenceChallenges,
                             @QueryParam("preferenceHydration") boolean preferenceHydration,
                             @QueryParam("preferenceCalories") boolean preferenceCalories,
                             @QueryParam("preferenceGamification") boolean preferenceGamification,
                             @QueryParam("preferenceLanguage") String preferenceLanguage,
                             @QueryParam("preferenceUnit") String preferenceUnit,
                             @QueryParam("preferenceRadius") int preferenceRadius,
                             @QueryParam("userId") int userId
    )
    {

        try{

            Statement st = conn.createStatement();
            Notification_Settings notification= null;
            String insertSettingQuery= "INSERT INTO NOTIFICATION_SETTINGS (PREFERENCEFRIENDS, PREFERENCEACTIVITY, PREFERENCETRAINING," +
                    " PREFERENCECHALLENGES, PREFERENCEHYDRATION, PREFERENCECALORIES, PREFERENCEGAMIFICATION, PREFERENCELANGUAGE," +
                    " PREFERENCEUNIT, PREFERENCERADIUS, FK_PERSON)"+
                    " VALUES ( '" +preferenceFriends+ "','" +preferenceActivity+ "','"
                    +preferenceTraining+ "','" +preferenceChallenges+ "','" +preferenceHydration+ "','" +preferenceCalories+ "','"
                    +preferenceGamification+ "','" +preferenceLanguage+ "','" +preferenceUnit+ "','" +preferenceRadius+ "','" +userId+ "')";
            st.executeUpdate(insertSettingQuery);
            notification = new Notification_Settings(preferenceFriends, preferenceActivity, preferenceTraining, preferenceChallenges, preferenceHydration, preferenceCalories, preferenceGamification,
                    preferenceLanguage, preferenceUnit, preferenceRadius);
            return gson.toJson(notification);

        }
        catch(Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Metodo que es llamado para actualizar la configuración del usuario en la base de datos
     * @param preferenceFriends
     * @param preferenceActivity
     * @param preferenceTraining
     * @param preferenceChallenges
     * @param preferenceHydration
     * @param preferenceCalories
     * @param preferenceGamification
     * @param preferenceLanguage
     * @param preferenceUnit
     * @param preferenceRadius
     * @param userId
     * @return
     */

    @GET
    @Path("/updateSetting")
    @Produces("application/json")
    public String updateSetting(@QueryParam("preferenceFriends") boolean preferenceFriends,
                                @QueryParam("preferenceActivity") boolean preferenceActivity,
                                @QueryParam("preferenceTraining") boolean preferenceTraining,
                                @QueryParam("preferenceChallenges") boolean preferenceChallenges,
                                @QueryParam("preferenceHydration") boolean preferenceHydration,
                                @QueryParam("preferenceCalories") boolean preferenceCalories,
                                @QueryParam("preferenceGamification") boolean preferenceGamification,
                                @QueryParam("preferenceLanguage") String preferenceLanguage,
                                @QueryParam("preferenceUnit") String preferenceUnit,
                                @QueryParam("preferenceRadius") int preferenceRadius,
                                @QueryParam("userId") int userId
    )
    {

        try{

            Statement st = conn.createStatement();
            Notification_Settings notification= null;
            String updateSettingQuery= "UPDATE NOTIFICATION_SETTINGS SET (PREFERENCEFRIENDS, PREFERENCEACTIVITY, PREFERENCETRAINING," +
                    " PREFERENCECHALLENGES, PREFERENCEHYDRATION, PREFERENCECALORIES, PREFERENCEGAMIFICATION, PREFERENCELANGUAGE," +
                    " PREFERENCEUNIT, PREFERENCERADIUS)"+ " = ( '" +preferenceFriends+ "','" +preferenceActivity+ "','"
                    +preferenceTraining+ "','" +preferenceChallenges+ "','" +preferenceHydration+ "','" +preferenceCalories+ "','"
                    +preferenceGamification+ "','" +preferenceLanguage+ "','" +preferenceUnit+ "','" +preferenceRadius+ "') " + "WHERE FK_PERSON = " + userId;
            st.executeUpdate(updateSettingQuery);
            notification = new Notification_Settings(preferenceFriends, preferenceActivity, preferenceTraining, preferenceChallenges, preferenceHydration, preferenceCalories, preferenceGamification,
                    preferenceLanguage, preferenceUnit, preferenceRadius);
            return gson.toJson(notification);

        }
        catch(Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Metodo que es llamado para mostrar la configuración del usuario
     * @param userId
     * @return
     */

    @GET
    @Path("/getSetting")
    @Produces("application/json")
    public String getSetting(@QueryParam("userId") int userId)
    {

        try{

            Statement st = conn.createStatement();
            Notification_Settings notification= null;
            String getSettingQuery= "SELECT * FROM NOTIFICATION_SETTINGS WHERE FK_PERSON = " + userId;
            ResultSet rs = st.executeQuery(getSettingQuery);

            while(rs.next()){
                boolean preferenceFriends = rs.getBoolean("PREFERENCEFRIENDS");
                boolean preferenceActivity = rs.getBoolean("PREFERENCEACTIVITY");
                boolean preferenceTraining = rs.getBoolean("PREFERENCETRAINING");
                boolean preferenceChallenges = rs.getBoolean("PREFERENCECHALLENGES");
                boolean preferenceHydration = rs.getBoolean("PREFERENCEHYDRATION");
                boolean preferenceCalories = rs.getBoolean("PREFERENCECALORIES");
                boolean preferenceGamification = rs.getBoolean("PREFERENCEGAMIFICATION");
                String preferenceLanguage = rs.getString("PREFERENCELANGUAGE");
                String preferenceUnit = rs.getString("PREFERENCEUNIT");
                int preferenceRadius = rs.getInt("PREFERENCERADIUS");

                notification = new Notification_Settings(preferenceFriends, preferenceActivity, preferenceTraining, preferenceChallenges, preferenceHydration, preferenceCalories, preferenceGamification,
                        preferenceLanguage, preferenceUnit, preferenceRadius);
            }
            return gson.toJson(notification);

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
            String url = "jdbc:postgresql://localhost/FitUcabDB";
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
