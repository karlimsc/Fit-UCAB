package WebServicesClasses;

import Domain.Sql;
import Domain.User;
import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by andres on 13/05/17.
 */
@Path("/friend")
public class M03_ServicesFriends{

    Gson gson = new Gson();
    Sql base = new Sql();
    @GET
    @Path("/getAll")
    @Produces("application/json")
    public String getAll(@QueryParam("id") String id,@QueryParam("Action") String Action){
        String RequestModifier="";
        String ActionNumber="";
        if(Action.equals("Friends")) {

            ActionNumber = "2";

        }
        else if(Action.equals("Requests")){

            RequestModifier=" AND friendshipuseractivity != " + id;
            ActionNumber="1";


        }



        String query = "SELECT * FROM friendship,person WHERE ((fk_persononeid = "+id
                +" AND fk_persontwoid = personid) OR (fk_persontwoid = "+id
                +" AND fk_persononeid = personid))AND fk_statusid = "+ActionNumber+RequestModifier;



        ArrayList<User> ap = new ArrayList<User>();


        try {
            ResultSet rs = base.sql(query);

            while(rs.next()) {
                User resultado = new User();
                resultado.setId(rs.getInt("personid"));
                resultado.setUser((rs.getString("personusername")));
                resultado.setPassword((rs.getString("personpassword")));
                resultado.setEmail((rs.getString("personemail")));
                resultado.setSex((rs.getString("personsex")));
                resultado.setPhone((rs.getString("personphone")));
                //resultado.set_birthdate((rs.getString("personphone")));
                ap.add(resultado);

            }



        }catch (SQLException e) {
            e.printStackTrace();
        }


        return gson.toJson(ap);
    }

    @PUT
    @Path("/request")
    @Produces("plain/text")
    public String request(@QueryParam("idRequester") String idRequester,@QueryParam("idRequested") String idRequested){

        //En cada Friendship, el id mas bajo debe ir en fk_persononeid. Por motivos de orden en la DB.

        int idMayor;
        int idMenor;

        if(Integer.parseInt(idRequested)>Integer.parseInt(idRequester)) {

            idMayor = Integer.parseInt(idRequested);
            idMenor = Integer.parseInt(idRequester);

        }else{

            idMayor = Integer.parseInt(idRequester);
            idMenor = Integer.parseInt(idRequested);

        }
        String queryVerificar="Select * from public.friendship where (fk_persononeid="+idMayor+" and fk_persontwoid= "+idMenor+") or (fk_persononeid="+idMenor+" and fk_persontwoid= "+idMayor+")  ";
        ResultSet verificar = null;
        try {
            verificar = base.sql(queryVerificar);

            if (verificar.isBeforeFirst())
                return "Ya Existe esta amistad.";
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String query = "INSERT INTO public.friendship(fk_persononeid, fk_persontwoid, fk_statusid, friendshipuseractivity) VALUES ("
                +idMenor+", "+idMayor+", 1, "+idRequester+")";

        base = new Sql();
        ResultSet rs = null;
        try {
            rs = base.sql(query);


        } catch (SQLException e) {
            e.printStackTrace();
            return "1";//Se agrego con exito!!

        }


        return "0";//No se agrego



    }


    @POST
    @Path("/update")
    @Produces("application/json")
    public String update(@QueryParam("idUpdater") String idUpdater,@QueryParam("idUpdated") String idUpdated,@QueryParam("Action") String Action) {
        //En cada Friendship, el id mas bajo debe ir en fk_persononeid. Por motivos de orden en la DB.


        String ActionNumber="";
        int idMayor;
        int idMenor;


        if (Integer.parseInt(idUpdated) > Integer.parseInt(idUpdater)) {

            idMayor = Integer.parseInt(idUpdated);
            idMenor = Integer.parseInt(idUpdater);

        } else {

            idMayor = Integer.parseInt(idUpdater);
            idMenor = Integer.parseInt(idUpdated);

        }

        if(Action.equals("Accept"))
            ActionNumber="2";
        else if(Action.equals("Block"))
            ActionNumber="3";
        else if(Action.equals("Decline"))
            ActionNumber="4";



        String query = "UPDATE public.friendship SET fk_persononeid=" + idMenor + ", fk_persontwoid=" + idMayor + ", fk_statusid="+ActionNumber+", friendshipuseractivity =" + idUpdater +
                "WHERE (fk_persononeid = "+idMenor+" AND fk_persontwoid = "+idMayor+");";


        ResultSet rs = null;
        try {
            rs = base.sql(query);

        } catch (SQLException e) {
            e.printStackTrace();
            return "1";//Se agrego con exito!!
        }

        return "0";//No Se agrego


    }












}

