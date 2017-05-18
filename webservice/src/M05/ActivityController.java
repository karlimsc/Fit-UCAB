package M05;

import M05.Model.Global;
import M05.Model.Sport;
import com.google.gson.Gson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.*;

/**
 * Created by estefania on 14/05/2017.
 */

@Path("/M05.Manejo_Actividades")
public class ActivityController {


        Gson gson = new Gson();

        @GET

        @Path("/insertarActividad")

        @Produces("application/json")

        public String insertarPersona(@QueryParam("horainicial") String horainicio,@QueryParam("horafinal") String horafinal,
                                      @QueryParam("fecha") String fecha,@QueryParam("km") String km,@QueryParam("calorias") String caloria,
                                      @QueryParam("lugarinicial") String lugarinicio,@QueryParam("lugarfinal") String lugarfinal){

            String query= "INSERT INTO ACTIVITY (ACTIVITYID,ACTIVITYSTARTTIME, ACTIVITYENDTIME,ACTIVITYDATE," +
                    "ACTIVITYKM ,ACTIVITYCALOR,ACTIVITYSTARTSITE,ACTIVITYENDSITE) VALUES (nextval('ACTIVITYID'),'"
                    +horainicio+"','"+horafinal+"','"+fecha+"','"+km+"','"+caloria+"','"+lugarinicio+"','"+lugarfinal+"') " ;

            try{
                Connection conn=conectarADb();
                Statement st=conn.createStatement();
                st.executeUpdate(query);
                return gson.toJson(true);

            } catch (Exception e) {
                return  e.getMessage();
            }

        }


        @GET

        @Path("/ObtenerActividad")

        @Produces("application/json")

        public String obtenerPersona(@QueryParam("nombre") String name){


            String query = "SELECT * FROM sport WHERE sportname='"+name+"'";

            Sport resultado= new Sport();

            try{
                Connection conn=conectarADb();
                Statement st = conn.createStatement();
                ResultSet rs =  st.executeQuery(query);

                while(rs.next()){

                    resultado.setId(rs.getInt("sportid"));
                    resultado.setName(rs.getString("sportname"));
                    resultado.setMet(rs.getFloat("sportmet"));


                }
                return gson.toJson(resultado);

            } catch (Exception e) {
                return e.getMessage();
            }
        }

        public float obtenerMet( String name){


            String query = "SELECT SPORTMET FROM sport WHERE sportname='"+name+"'";

            Sport resultado= new Sport();

            try{
                Connection conn=conectarADb();
                Statement st = conn.createStatement();
                ResultSet rs =  st.executeQuery(query);

                while(rs.next()){

                    resultado.setMet(rs.getFloat("sportmet"));
                }
                return resultado.getMet();

            } catch (Exception e) {
                return 0;
            }
        }

        private Connection conectarADb(){
            Connection conn = null;
            try {
                Class.forName("org.postgresql.Driver");
                String url ="jdbc:postgresql://localhost:"+ Global.port+"/"+Global.nameBd;
                conn       = DriverManager.getConnection(url, "postgres",  "postgres");



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
