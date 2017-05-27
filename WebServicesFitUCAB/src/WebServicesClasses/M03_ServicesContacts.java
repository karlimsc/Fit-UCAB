package WebServicesClasses;

import Domain.Sql;
import Domain.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Created by andres on 13/05/17.
 */
@Path("/contact")
public class M03_ServicesContacts{

    Gson gson = new Gson();
    Sql base = new Sql();

    @GET
    @Path("/getContacts")
    @Produces("application/json")
    public String getContacts(@QueryParam("id") String id,@QueryParam("contacts") String contacts) {

        ArrayList<User> entrada = new ArrayList<User>();
        entrada = gson.fromJson(contacts, new TypeToken<List<User>>() {
        }.getType());
        String query = "SELECT * FROM person where(";
        for (int i = 0; i < entrada.size(); i++) {
            query = query + " (personphone = '" + entrada.get(i).getPhone() + "' or personphone = '0" + entrada.get(i).getPhone() +
                    "' or personemail = '" + entrada.get(i).getEmail() + "') ";
            if (i != entrada.size() - 1)
                query = query + " or";
        }
        query = query + ");";

        ArrayList<User> conFitUcab = new ArrayList<User>();
        ArrayList<User> sinFitUcab = new ArrayList<User>();
        try {
            ResultSet rs = base.sql(query);

            while (rs.next()) {
                User resultado = new User();
                resultado.setId(rs.getInt("personid"));
                resultado.setUser((rs.getString("personusername")));
                resultado.setPassword((rs.getString("personpassword")));
                resultado.setEmail((rs.getString("personemail")));
                resultado.setSex((rs.getString("personsex")));
                resultado.setPhone((rs.getString("personphone")));
                //resultado.set_birthdate((rs.getString("personphone")));
                conFitUcab.add(resultado);

            }

            sinFitUcab = new ArrayList<User>();

            for (int i = 0; i < entrada.size(); i++) {
                boolean agregar=true;
                for (int j = 0; j < conFitUcab.size(); j++) {
                    if (entrada.get(i).getPhone().equals(conFitUcab.get(j).getPhone()) || entrada.get(i).getEmail().equals(conFitUcab.get(j).getEmail())) {
                        agregar=false;
                        break;
                    }

                }
                if(agregar)
                    sinFitUcab.add(entrada.get(i));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        String queryAmistades = "select * from person,friendship where (";
        for (int i = 0; i < conFitUcab.size(); i++) {
            int idMayor;
            int idMenor;

            if (Integer.parseInt(id) > conFitUcab.get(i).getId()) {

                idMayor = Integer.parseInt(id);
                idMenor = conFitUcab.get(i).getId();

            } else {

                idMayor = conFitUcab.get(i).getId();
                ;
                idMenor = Integer.parseInt(id);

            }

            queryAmistades = queryAmistades + " ((fk_persononeid = '" + idMenor + "' and fk_persontwoid  = '" + idMayor + "' and fk_statusid != '4' AND personid != " + id
                    + " and fk_persononeid = personid) or";

            queryAmistades = queryAmistades + " (fk_persononeid = '" + idMenor + "' and fk_persontwoid  = '" + idMayor + "' and fk_statusid != '4' AND personid != " + id +
                    " and fk_persontwoid = personid)) ";

            if (i != conFitUcab.size() - 1)
                queryAmistades = queryAmistades + " or";
        }
        queryAmistades = queryAmistades + " );";


        ArrayList<User> amigoNo4 = new ArrayList<User>();

        base = new Sql();
        ArrayList<User> noAmiyDeclined = new ArrayList<User>();
        try {
            ResultSet rs2 = base.sql(queryAmistades);

            while (rs2.next()) {
                User resultado = new User();
                resultado.setId(rs2.getInt("personid"));
                resultado.setUser((rs2.getString("personusername")));
                resultado.setPassword((rs2.getString("personpassword")));
                resultado.setEmail((rs2.getString("personemail")));
                resultado.setSex((rs2.getString("personsex")));
                resultado.setPhone((rs2.getString("personphone")));
                //resultado.set_birthdate((rs2.getString("personphone")));
                amigoNo4.add(resultado);
            }

            noAmiyDeclined = new ArrayList<User>();

            for (int i = 0; i < conFitUcab.size(); i++) {
                boolean agregar = true;
                for (int j = 0; j < amigoNo4.size(); j++) {
                    if (conFitUcab.get(i).getPhone().equals(amigoNo4.get(j).getPhone()) || conFitUcab.get(i).getEmail().equals(amigoNo4.get(j).getEmail())) {
                        agregar=false;
                        break;
                    }
                }
                if(agregar) {
                    noAmiyDeclined.add(conFitUcab.get(i));

                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        ArrayList<User> salida = new ArrayList<User>();

        User sep1 = new User();
        sep1.setId(-1);
        User sep2 = new User();
        sep2.setId(-2);

        salida.add(sep1);
        for (int i = 0; i < noAmiyDeclined.size(); i++)
            salida.add(noAmiyDeclined.get(i));
        salida.add(sep2);

        for (int i = 0; i < sinFitUcab.size(); i++)
            salida.add(sinFitUcab.get(i));





        return gson.toJson(salida);
    }

   /* @PUT
    @Path("/request")
    @Produces("plain/text")
    public String request(@QueryParam("idRequester") String idRequester, @QueryParam("idRequested") String idRequested) {

        //En cada Friendship, el id mas bajo debe ir en fk_persononeid. Por motivos de orden en la DB.

        int idMayor;
        int idMenor;

        if (Integer.parseInt(idRequested) > Integer.parseInt(idRequester)) {

            idMayor = Integer.parseInt(idRequested);
            idMenor = Integer.parseInt(idRequester);

        } else {

            idMayor = Integer.parseInt(idRequester);
            idMenor = Integer.parseInt(idRequested);

        }
        String queryVerificar = "Select * from public.friendship where (fk_persononeid=" + idMayor
                + " and fk_persontwoid= " + idMenor + ") or (fk_persononeid=" + idMenor + " and fk_persontwoid= " + idMayor + ")  ";




        try {
            ResultSet verificar = base.sql(queryVerificar);
            if (verificar.isBeforeFirst())
                return "Ya Existe esta amistad.";
        } catch (SQLException e) {
            e.printStackTrace();
        }


        String query = "INSERT INTO public.friendship(fk_persononeid, fk_persontwoid, fk_statusid, friendshipuseractivity) VALUES ("
                + idMenor + ", " + idMayor + ", 1, " + idRequester + ")";


        ResultSet rs = null;
        try {
            rs = base.sql(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rs != null) {
            return "1";//Se ejecuto el query con exito.

        } else {

            return "0";//No Se ejecuto el query con exito.
        }

    }


    @POST
    @Path("/update")
    @Produces("application/json")
    public String update(@QueryParam("idUpdater") String idUpdater, @QueryParam("idUpdated") String idUpdated, @QueryParam("Action") String Action) {
        //En cada Friendship, el id mas bajo debe ir en fk_persononeid. Por motivos de orden en la DB.


        String ActionNumber = "";
        int idMayor;
        int idMenor;


        if (Integer.parseInt(idUpdated) > Integer.parseInt(idUpdater)) {

            idMayor = Integer.parseInt(idUpdated);
            idMenor = Integer.parseInt(idUpdater);

        } else {

            idMayor = Integer.parseInt(idUpdater);
            idMenor = Integer.parseInt(idUpdated);

        }

        if (Action.equals("Accept"))
            ActionNumber = "2";
        else if (Action.equals("Block"))
            ActionNumber = "3";
        else if (Action.equals("Decline"))
            ActionNumber = "4";


        String query = "UPDATE public.friendship SET fk_persononeid=" + idMenor + ", fk_persontwoid=" + idMayor + ", fk_statusid=" + ActionNumber + ", friendshipuseractivity =" + idUpdater +
                "WHERE (fk_persononeid = " + idMenor + " AND fk_persontwoid = " + idMayor + ");";


        ResultSet rs = null;
        try {
            rs = base.sql(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (rs != null) {
            return "1";//Se ejecuto el query con exito.

        } else {

            return "0";//No Se ejecuto el query con exito.
        }
    }*/
}









