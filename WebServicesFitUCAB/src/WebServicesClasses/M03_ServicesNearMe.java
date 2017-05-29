package WebServicesClasses;

import Domain.Sql;
import Domain.UserAuxiliar;
import com.google.gson.Gson;

import javax.ws.rs.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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


        Sql baseNearMe = new Sql();
        ResultSet rs = null;
        ArrayList<UserAuxiliar> userLocations = new ArrayList<UserAuxiliar>();

        try {
            rs = baseNearMe.sql(query);

        if(rs!=null && rs.isBeforeFirst()) {
            while (rs.next()) {
                UserAuxiliar aux = new UserAuxiliar();

                aux.set_id(rs.getInt("personid"));
                aux.set_username(rs.getString("personusername"));
                aux.set_sex(rs.getString("personsex"));
                aux.set_birthdate(rs.getString("personbirthdate"));
                aux.set_point(rs.getInt("registrypoint"));
                aux.set_longitud(rs.getString("geolongitud"));
                aux.set_latitud(rs.getString("geolatitud"));

                if(aux.get_id()!=Integer.parseInt(id))
                    userLocations.add(aux);

            }
        }


        } catch (SQLException e) {
            e.printStackTrace();


        }

        float longitudFloat = Float.parseFloat(longitud);
        float latitudFloat = Float.parseFloat(latitud);
        for(int i=0;i<userLocations.size();i++){
            float d = 0;
            float longitudFloatAux = Float.parseFloat(userLocations.get(i).get_longitud());
            float latitudFloatAux = Float.parseFloat(userLocations.get(i).get_latitud());


            d=distFrom(latitudFloat,longitudFloat,latitudFloatAux,longitudFloatAux)/1000;


            userLocations.get(i).set_distancia(Float.toString(d));

        }

        for(int i=userLocations.size()-1;i>=0;i--){

            if(Float.parseFloat(userLocations.get(i).get_distancia())>Float.parseFloat((rango))){
                userLocations.remove(i);
            }
        }


        Collections.sort(userLocations,(u1,u2)->Float.compare(Float.parseFloat(u1.get_distancia()),Float.parseFloat(u2.get_distancia())));


        return gson.toJson(userLocations);
    }

    @PUT
    @Path("/setLocation")
    @Produces("application/json")
    public String setLocation(@QueryParam("id") String id,@QueryParam("longitud") String longitud,@QueryParam("latitud") String latitud){

        String query="";
        String queryVerificar="Select * from public.geo where (fk_personid="+id+");";
        ResultSet verificar = null;
        Sql baseSetLocationVerify = new Sql();
        try {
            verificar = baseSetLocationVerify.sql(queryVerificar);

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


        Sql baseSetLocation = new Sql();
        Boolean rs = null;
        try {
            rs = baseSetLocation.sqlNoReturn(query);


        } catch (SQLException e) {
            e.printStackTrace();

        }

        return rs.toString();


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
