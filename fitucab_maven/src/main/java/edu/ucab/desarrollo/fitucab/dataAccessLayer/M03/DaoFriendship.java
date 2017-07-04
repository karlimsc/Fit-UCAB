package edu.ucab.desarrollo.fitucab.dataAccessLayer.M03;
import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que maneja la informacion de las amistades
 * @author Daniel Da Silva, Luis Martinez, Anderson Gomez
 * @version 2.0
 */
public class DaoFriendship extends Dao implements IDaoFriendship {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(DaoFriendship.class);
    private Connection _conn;

    public DaoFriendship( Entity entidad )
    {

    }

    public DaoFriendship()
    {
        try {
            _conn = Dao.getBdConnect();
        } catch (BdConnectException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
    }


    /**
     * Metodo que retorna todos los amigos
     * @param id es el usuario
     * @param Action para saber si son amigos
     * @return retorna la amistad ya agregada
     * @throws SQLException
     */

    public ArrayList<UserAuxiliar> getList(String id, String Action){
        String RequestModifier="";
        String ActionNumber="";
        if(Action.equals("Friends")) {

            ActionNumber = "2";

        }
        else if(Action.equals("Requests")){

            RequestModifier=" AND friendshipuseractivity != " + id;
            ActionNumber="1";


        }

        String query = "SELECT * FROM friendship,person,registry WHERE ((fk_persononeid = "+id
                +" AND fk_persontwoid = personid AND fk_personid = personid )  OR (fk_persontwoid = "+id
                +" AND fk_persononeid = personid AND fk_personid = personid ))AND fk_statusid = "+ActionNumber+RequestModifier;


        ArrayList<UserAuxiliar> ap = new ArrayList<UserAuxiliar>();

        Sql baseGetAllFriends = new Sql();

        try {
            ResultSet rs = baseGetAllFriends.sql(query);

            if(rs!=null && rs.isBeforeFirst()) {
                while (rs.next()) {
                    UserAuxiliar resultado = new UserAuxiliar();
                    resultado.set_id(rs.getInt("personid"));
                    resultado.set_username((rs.getString("personusername")));
                    resultado.set_sex((rs.getString("personsex")));
                    resultado.set_point((rs.getInt("registrypoint")));

                    ap.add(resultado);

                }
            }


        }catch (SQLException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }


        return ap;
    }

    /**
     * Metodo que verifica cual es el mayor de 2 id
     * @param idRequested Id del usuario al que se le solicita amistad
     * @param idRequester Id del usuario que solicito amistad
     * @return el mayor id
     * @throws SQLException
     */

    public int mayor(String idRequested, String idRequester){
        int idMayor;

        if(Integer.parseInt(idRequested)>=Integer.parseInt(idRequester)) {
            idMayor = Integer.parseInt(idRequested);
            return idMayor;

        }else{
            idMayor = Integer.parseInt(idRequester);
            return idMayor;
        }
    }

    /**
     * Metodo que verifica si la amistad ya existe, y si no la agrega
     * @param idRequested Id del usuario al que se le solicita amistad
     * @param idRequester Id del usuario que solicito amistad
     * @return retorna la amistad ya agregada
     * @throws SQLException
     */

    public void requestFriendship( String idRequester, String idRequested){
        //En cada Friendship, el id mas bajo debe ir en fk_personid. Por motivos de orden en la DB.

        //variables de entrada
        int idMayor;
        int idMenor;

        idMayor = Integer.parseInt(idRequested);

        if(idMayor == mayor(idRequested, idRequester)){
            idMenor = Integer.parseInt(idRequester);
        }else{
            idMenor = idMayor;
            idMayor = Integer.parseInt(idRequester);

        }

        //fin variables de entrada
        //primero verifico si la amistad ya existe

        String queryVerificar = "Select * from public.friendship where (fk_persononeid="+idMayor+" and fk_persontwoid= "+idMenor+") or (fk_persononeid="+idMenor+" and fk_persontwoid= "+idMayor+")  ";
        ResultSet verificar = null;
        Sql baseRequestsVerify = new Sql();
        try {
            verificar = baseRequestsVerify.sql(queryVerificar);
            if (verificar.isBeforeFirst())
                logger.error("Error: Ya existe esta amistad");
        } catch (SQLException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }

        //luego la agrego

        String query = "INSERT INTO public.friendship(fk_persononeid, fk_persontwoid, fk_statusid, friendshipuseractivity) VALUES ("
                +idMenor+", "+idMayor+", 1, "+idRequester+")";

        try {
            Statement st = _conn.createStatement();
            ResultSet rs = st.executeQuery(query);

        } catch (SQLException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
    }

    /**
     * Metodo que verifica si la amistad ya existe, y si no la agrega
     * @param idUpdater es la entidad
     * @param idUpdated Id del usuario al que se le solicita amistad
     * @param Action Id del usuario que solicito amistad
     * @return retorna la amistad ya agregada
     * @throws SQLException
     */

    public void updateFriendship(String idUpdater, String idUpdated, String Action){
        //En cada Friendship, el id mas bajo debe ir en fk_persononeid. Por motivos de orden en la DB.

        String ActionNumber="";
        int idMayor;
        int idMenor;

        idMayor = Integer.parseInt(idUpdated);

        if(idMayor == mayor(idUpdated, idUpdater)){
            idMenor = Integer.parseInt(idUpdater);
        }else{
            idMenor = idMayor;
            idMayor = Integer.parseInt(idUpdated);

        }


        if(Action.equals("Accept"))
            ActionNumber="2";
        else if(Action.equals("Block"))
            ActionNumber="3";
        else if(Action.equals("Decline"))
            ActionNumber="4";
        else if(Action.equals("Request"))
            ActionNumber="1";


        String query = "UPDATE public.friendship SET fk_persononeid=" + idMenor + ", fk_persontwoid=" + idMayor + ", fk_statusid="+ActionNumber+", friendshipuseractivity =" + idUpdater +
                " WHERE (fk_persononeid = "+idMenor+" AND fk_persontwoid = "+idMayor+");";


        Boolean rs = null;
        Sql baseUpdateFriendship = new Sql();
        try {
            rs = baseUpdateFriendship.sqlNoReturn(query);

        }catch (SQLException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());

        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }

    }

    /**
     * Metodo para obtener los contactos
     * @param id es el id del usuario
     * @param contacts son los contactos
     * @return retorna los contactos
     * @throws SQLException
     */

    public ArrayList<UserAuxiliar> getContacts(String id, String contacts){
        Gson gson = new Gson();

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
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
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

        return salida;
    }


    @Override
    public Entity create(Entity e) throws SQLException {
        return null;
    }

    @Override
    public Entity read(Entity e) throws CreateHomeException, SQLException, BdConnectException {
        return null;
    }


    @Override
    public Entity update(Entity e) {
        return null;
    }




    public void Create(Entity e) {

    }
}

