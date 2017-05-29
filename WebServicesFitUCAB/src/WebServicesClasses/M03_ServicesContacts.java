package WebServicesClasses;

import Domain.Sql;
import Domain.User;
import Domain.UserAuxiliar;
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


    @GET
    @Path("/getContacts")
    @Produces("application/json")
    public String getContacts(@QueryParam("id") String id,@QueryParam("contacts") String contacts) {

        ArrayList<User> entrada = new ArrayList<User>();
        entrada = gson.fromJson(contacts, new TypeToken<List<User>>() {
        }.getType());
        String query = "SELECT * FROM person,registry where(";
        for (int i = 0; i < entrada.size(); i++) {
            query = query + " ((personphone = '" + entrada.get(i).getPhone() + "' or personphone = '0" + entrada.get(i).getPhone() +
                    "' or personemail = '" + entrada.get(i).getEmail() + "') and fk_personid = personid)";
            if (i != entrada.size() - 1)
                query = query + " or";
        }
        query = query + ");";

        ArrayList<UserAuxiliar> conFitUcab = new ArrayList<UserAuxiliar>();
        ArrayList<UserAuxiliar> sinFitUcab = new ArrayList<UserAuxiliar>();

        Sql baseGetContacts = new Sql();
        try {
            ResultSet rs = baseGetContacts.sql(query);

        if(rs!=null && rs.isBeforeFirst()) {
            while (rs.next()) {
                UserAuxiliar resultado = new UserAuxiliar();
                resultado.set_id(rs.getInt("personid"));
                resultado.set_username((rs.getString("personusername")));
                resultado.set_email((rs.getString("personemail")));
                resultado.set_sex((rs.getString("personsex")));
                resultado.set_phone((rs.getString("personphone")));
                resultado.set_point((rs.getInt("registrypoint")));

                conFitUcab.add(resultado);

            }
        }



            for (int i = 0; i < entrada.size(); i++) {
                boolean agregar = true;
                for (int j = 0; j < conFitUcab.size(); j++) {
                    if (entrada.get(i).getPhone().equals(conFitUcab.get(j).get_phone()) || entrada.get(i).getEmail().equals(conFitUcab.get(j).get_email())) {
                        agregar = false;
                        break;
                    }

                }
                if (agregar) {
                    UserAuxiliar aux = new UserAuxiliar();
                    aux.set_username(entrada.get(i).getUser());
                    aux.set_email(entrada.get(i).getEmail());
                    aux.set_phone(entrada.get(i).getPhone());
                    sinFitUcab.add(aux);

                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        String queryAmistades = "select * from person,friendship where (";
        for (int i = 0; i < conFitUcab.size(); i++) {
            int idMayor;
            int idMenor;

            if (Integer.parseInt(id) >= conFitUcab.get(i).get_id()) {

                idMayor = Integer.parseInt(id);
                idMenor = conFitUcab.get(i).get_id();

            } else {

                idMayor = conFitUcab.get(i).get_id();
                ;
                idMenor = Integer.parseInt(id);

            }

            queryAmistades = queryAmistades + " ((fk_persononeid = '" + idMenor + "' and fk_persontwoid  = '" + idMayor + "' and fk_statusid != '4' AND personid != " + id
                    + " and fk_persononeid = personid ) or";

            queryAmistades = queryAmistades + " (fk_persononeid = '" + idMenor + "' and fk_persontwoid  = '" + idMayor + "' and fk_statusid != '4' AND personid != " + id +
                    " and fk_persontwoid = personid )) ";

            if (i != conFitUcab.size() - 1)
                queryAmistades = queryAmistades + " or";
        }
        queryAmistades = queryAmistades + " );";




        Sql baseQueryAmistades = new Sql();

        ArrayList<UserAuxiliar> amigoNo4 = new ArrayList<>();
        ArrayList<UserAuxiliar> noAmiyDeclined = new ArrayList<UserAuxiliar>();
        try {
            ResultSet rs2 = baseQueryAmistades.sql(queryAmistades);

            if(rs2!=null && rs2.isBeforeFirst()) {
                while (rs2.next()) {
                    UserAuxiliar resultado = new UserAuxiliar();
                    resultado.set_id(rs2.getInt("personid"));
                    resultado.set_username((rs2.getString("personusername")));
                    resultado.set_email((rs2.getString("personemail")));
                    resultado.set_sex((rs2.getString("personsex")));
                    resultado.set_phone((rs2.getString("personphone")));


                    //resultado.set_birthdate((rs2.getString("personphone")));
                    amigoNo4.add(resultado);
                }
            }


            for (int i = 0; i < conFitUcab.size(); i++) {
                boolean agregar = true;
                for (int j = 0; j < amigoNo4.size(); j++) {
                    if (conFitUcab.get(i).get_phone().equals(amigoNo4.get(j).get_phone()) || conFitUcab.get(i).get_email().equals(amigoNo4.get(j).get_email())) {
                        agregar = false;
                        break;
                    }
                }
                if (agregar) {
                    UserAuxiliar aux = new UserAuxiliar();

                    aux.set_id(conFitUcab.get(i).get_id());
                    aux.set_username(conFitUcab.get(i).get_username());
                    aux.set_email(conFitUcab.get(i).get_email());
                    aux.set_phone(conFitUcab.get(i).get_phone());
                    aux.set_sex(conFitUcab.get(i).get_sex());
                    aux.set_point(conFitUcab.get(i).get_point());
                    //aux.set_point(conFitUcab.get(i).get_());


                    noAmiyDeclined.add(aux);
                }
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }


        ArrayList<UserAuxiliar> salida = new ArrayList<>();

        UserAuxiliar sep1 = new UserAuxiliar();
        sep1.set_id(-1);
        UserAuxiliar sep2 = new UserAuxiliar();
        sep2.set_id(-2);


        if(noAmiyDeclined!=null && !noAmiyDeclined.isEmpty()) {
            salida.add(sep1);
            for (int i = 0; i < noAmiyDeclined.size(); i++) {
                if(noAmiyDeclined.get(i).get_id() != Integer.parseInt(id))
                    salida.add(noAmiyDeclined.get(i));
            }
        }



        if(sinFitUcab!=null && !sinFitUcab.isEmpty()) {
            salida.add(sep2);
            for (int i = 0; i < sinFitUcab.size(); i++)
                salida.add(sinFitUcab.get(i));
        }

        return gson.toJson(salida);

    }


}









