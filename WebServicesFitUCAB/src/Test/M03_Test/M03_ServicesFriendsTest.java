package M03_Test;

import Domain.Sql;
import Domain.UserAuxiliar;
import WebServicesClasses.M03_ServicesFriends;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.*;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by andres on 29/05/17.
 */
public class M03_ServicesFriendsTest {

    @Before
    public void setUp() throws Exception {

        Sql basePruebaFriendSetup = new Sql();

        //Agregamos dos usuarios de prueba.
        String query = "insert into person (personid,personusername, personpassword, personemail, personsex," +
                " personphone, personbirthdate) values (998,'UsuarioPrueba', 'Password', 'email@about.com', 'F'," +
                " '04156665488', '31/7/2016'),(999,'UsuarioPrueba2', 'Password', 'email@about.com2', 'F'," +
                " '04144458587', '31/7/2016');";

        basePruebaFriendSetup.sqlNoReturn(query);

    }

    @After
    public void tearDown() throws Exception {

        Sql basePruebaFriendTearDown = new Sql();

        //Eliminamos los dos usuarios de prueba.
        String query = "DELETE from person WHERE (personid= 998) or (personid = 999) ";

        basePruebaFriendTearDown.sqlNoReturn(query);

        basePruebaFriendTearDown = new Sql();

        //Eliminamos los dos usuarios de prueba.
        query = "DELETE from friendship WHERE (fk_persononeid = 998) and (fk_persontwoid = 999) ";

        basePruebaFriendTearDown.sqlNoReturn(query);


    }

    @Test
    public void getAll() throws Exception {



        M03_ServicesFriends m3Friends = new M03_ServicesFriends();
        m3Friends.request(Integer.toString(998),Integer.toString(999));
        m3Friends.update(Integer.toString(999),Integer.toString(998),"Accept");

        String respuesta = m3Friends.getAll(Integer.toString(998),"Friends");

        Gson gson = new Gson();

        ArrayList<UserAuxiliar> ua = gson.fromJson(respuesta, new TypeToken<List<UserAuxiliar>>(){}.getType());

        assertEquals(999,ua.get(0).get_id());




    }

    @Test
    public void request() throws Exception {

        /*M03_ServicesFriends m3Friends = new M03_ServicesFriends();

        m3Friends.request(Integer.toString(998),Integer.toString(999));

        Sql basePruebaFriendRequest = new Sql();


        String queryRequest = "SELECT * from friendship WHERE (fk_persononeid=998 and fk_persontwoid=999 and fk_statusid = 1) ";

        ResultSet rsRequest = basePruebaFriendRequest.sql(queryRequest);

        rsRequest.next();
        assertEquals(998,rsRequest.getInt("friendshipuseractivity"));*/


    }

    @Test
    public void updateAccept() throws Exception {

        /*M03_ServicesFriends m3Friends = new M03_ServicesFriends();

        m3Friends.request(Integer.toString(998),Integer.toString(999));
        m3Friends.update(Integer.toString(999),Integer.toString(998),"Accept");

        Sql basePruebaFriendAccept = new Sql();

        String queryAccept = "SELECT * from friendship WHERE (fk_persononeid=998 and fk_persontwoid=999 and fk_statusid = 2); ";

        ResultSet rsAccept = basePruebaFriendAccept.sql(queryAccept);

        rsAccept.next();
        assertEquals(999,rsAccept.getInt("friendshipuseractivity"));*/

    }

    @Test
    public void updateDecline() throws Exception {

        /*M03_ServicesFriends m3Friends = new M03_ServicesFriends();

        m3Friends.request(Integer.toString(998),Integer.toString(999));
        m3Friends.update(Integer.toString(999),Integer.toString(998),"Decline");

        Sql basePruebaFriendAccept = new Sql();

        String queryAccept = "SELECT * from friendship WHERE (fk_persononeid=998 and fk_persontwoid=999 and fk_statusid = 4); ";

        ResultSet rsAccept = basePruebaFriendAccept.sql(queryAccept);

        rsAccept.next();
        assertEquals(999,rsAccept.getInt("friendshipuseractivity"));
*/
    }

    @Test
    public void updateBlock() throws Exception {

        /*M03_ServicesFriends m3Friends = new M03_ServicesFriends();

        m3Friends.request(Integer.toString(998),Integer.toString(999));
        m3Friends.update(Integer.toString(999),Integer.toString(998),"Block");

        Sql basePruebaFriendAccept = new Sql();

        String queryAccept = "SELECT * from friendship WHERE (fk_persononeid=998 and fk_persontwoid=999 and fk_statusid = 3); ";

        ResultSet rsAccept = basePruebaFriendAccept.sql(queryAccept);

        rsAccept.next();
        assertEquals(999,rsAccept.getInt("friendshipuseractivity"));*/

    }

}