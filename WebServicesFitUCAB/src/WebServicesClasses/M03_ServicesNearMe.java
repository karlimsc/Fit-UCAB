package WebServicesClasses;

import Domain.Sql;
import Domain.UserAuxiliar;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by andres on 13/05/17.
 */
@Path("/nearMe")
public class M03_ServicesNearMe {

    Gson gson = new Gson();
    Sql base = new Sql();
    @GET
    @Produces("application/json")
    public String nearMe(@QueryParam("id") String id,@QueryParam("longitud") String longitud,@QueryParam("latitud") String latitud,@QueryParam("rango") String rango){

        String query = "SELECT personid,personusername,geolongitud,geolatitud,personsex,personbirthdate,registrypoint from person,geo,registry where (personid = geo.fk_personid)" +
                " and (personid = registry.fk_personid)   EXCEPT("+
                "SELECT personid,personusername,geolongitud,geolatitud,personsex,personbirthdate,registrypoint from person,friendship,geo,registry " +
                "where ((fk_persononeid = "+id+" and fk_persontwoid = personid) or " +
                "(fk_persontwoid = "+id+" and fk_persononeid = personid)) and  (personid = geo.fk_personid) and (personid = registry.fk_personid)" +
                " group by personid,friendshipid,geolongitud,geolatitud,registrypoint);";
        base = new Sql();
        ResultSet rs = null;
        ArrayList<UserAuxiliar> userLocations = new ArrayList<UserAuxiliar>();

        try {
            rs = base.sql(query);


            while (rs.next()){
                UserAuxiliar aux = new UserAuxiliar();

                aux.set_id(rs.getInt("personid"));
                aux.set_username(rs.getString("personusername"));
                aux.set_sex(rs.getString("personsex"));
                aux.set_birthdate(rs.getString("personbirthdate"));
                aux.set_point(rs.getInt("registrypoint"));
                aux.set_longitud(rs.getString("geolongitud"));
                aux.set_latitud(rs.getString("geolatitud"));

                userLocations.add(aux);

            }


        } catch (SQLException e) {
            e.printStackTrace();


        }

        //userLocations.sort();


        //Float.toString(distFrom(Float.parseFloat(latitud),Float.parseFloat(longitud),Float.parseFloat(userLocations.get(0).get_longitud()),Float.parseFloat(userLocations.get(0).get_latitud())));
        float longitudFloat = Float.parseFloat(longitud);
        float latitudFloat = Float.parseFloat(latitud);
        float longitudFloatAux = Float.parseFloat(userLocations.get(0).get_longitud());
        float latitudFloatAux = Float.parseFloat(userLocations.get(0).get_latitud());


        //return Float.toString(latitudFloatAux)+Float.toString(longitudFloatAux);
        return Float.toString(distFrom(latitudFloat,longitudFloat,latitudFloatAux,longitudFloatAux));
    }

    @PUT
    @Path("/setLocation")
    @Produces("application/json")
    public String setLocation(@QueryParam("id") String id,@QueryParam("latitud") String latitud,@QueryParam("longitud") String longitud){

        String query="";
        String queryVerificar="Select * from public.geo where (fk_personid="+id+");";
        ResultSet verificar = null;
        try {
            verificar = base.sql(queryVerificar);

            if (verificar.isBeforeFirst()) {

                //Ya existe el GEO, UPDATE
                query = "UPDATE public.geo SET geolongitud = '"+longitud+"', geolatitud = '"+latitud+"' WHERE fk_personid = "+id+";";

            }else {
                //No existe el geo, Insert
                query = "INSERT INTO public.geo (fk_personid,geolongitud,geolatitud) VALUES ("+id+","+longitud+","+latitud+")";


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        base = new Sql();
        ResultSet rs = null;
        try {
            rs = base.sql(query);


        } catch (SQLException e) {
            e.printStackTrace();
            return "1";//Se agrego con exito!!

        }

        return "0";


    }


    public static float distFrom(float lat1, float lng1, float lat2, float lng2) {


        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2-lat1);
        double dLng = Math.toRadians(lng2-lng1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng/2) * Math.sin(dLng/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        float dist = (float) (earthRadius * c);


        return dist;
    }


}
