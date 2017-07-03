package edu.ucab.desarrollo.fitucab.Test.M03_FriendshipTest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ucab.desarrollo.fitucab.webService.M03_ServicesContacts;
import edu.ucab.desarrollo.fitucab.webService.Sql;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Friendship;
import edu.ucab.desarrollo.fitucab.common.entities.UserAuxiliar;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M03.IDaoFriendship;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M03.IDaoNearMe;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.After;
import org.junit.Before;
import static org.junit.Assert.*;


/**
 * Created by Andy on 3/7/2017.
 */
public class FriendshipTest {
    static Connection conn;
    static Dao _dao;
    static Entity _user;

    @Before
    public void setUp() throws Exception {


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

    /**
     * Prueba unitaria para comprobar que el array usado en el metodo getList no sea nulo
     * @throws SQLException
     */

    @Test
    public void getListFriendstest() throws SQLException {

        ArrayList<UserAuxiliar> ap = new ArrayList<UserAuxiliar>();
        Friendship _friend = EntityFactory.createFriendship();
        IDaoFriendship dapfriend = DaoFactory.instanceDaoFriendship(_friend);
        ap = dapfriend.getList("1","Friends");
        assertNotNull(ap);

    }

    /**
     * Prueba unitaria para comprobar que el array usado en el metodo getList no sea nulo
     * @throws SQLException
     */
    @Test
    public void getListRequeststest() throws SQLException {

        ArrayList<UserAuxiliar> ap = new ArrayList<UserAuxiliar>();

        Friendship _friend = EntityFactory.createFriendship();
        IDaoFriendship daofriend = DaoFactory.instanceDaoFriendship(_friend);
        ap = daofriend.getList("1","Requests");
        assertNotNull(ap);

    }
    /**
     * Prueba unitaria para comprobar que el array usado en el metodo getNearme no sea nulo
     * @throws SQLException
     */
    @Test
    public  void getNearMetest() throws SQLException {

        ArrayList<UserAuxiliar> ap = new ArrayList<UserAuxiliar>();

        Friendship _friend = EntityFactory.createFriendship();
        IDaoNearMe daofriend = DaoFactory.instanceDaoNearMe(_friend);
        ap = daofriend.getNearMe("1","2","3","4");
        assertNotNull(ap);

    }

    /**
     * Prueba unitaria para comprobar que un numero es mayor que el otro del metodo mayor
     * @throws SQLException
     */

    @Test
    public void mayortest() {
        Friendship _friend = EntityFactory.createFriendship();
        IDaoFriendship daofriend = DaoFactory.instanceDaoFriendship(_friend);
        int x = daofriend.mayor("1","2");
        assertEquals(2,x);

    }

    @Test
    public void getContacts() throws Exception {

        Gson gson = new Gson();
        Sql basePruebaFriendSetup = new Sql();

        String query = "insert into person (personid,personusername, personpassword, personemail, personsex," +
                " personphone, personbirthdate) values (998,'UsuarioPrueba', 'Password', 'email@about.com', 'F'," +
                " '04156665488', '31/7/2016'),(999,'UsuarioPrueba2', 'Password', 'email@about.com2', 'F'," +
                " '04144458587', '31/7/2016');";

        basePruebaFriendSetup.sqlNoReturn(query);

        ArrayList<UserAuxiliar> libreta = new ArrayList<UserAuxiliar>();
        libreta.add(new UserAuxiliar("pepe","email.com","22222"));
        libreta.add(new UserAuxiliar("UsuarioPrueba2","email@about.com2","04144458587"));


        M03_ServicesContacts m3Contacts = new M03_ServicesContacts();
        String entrada = m3Contacts.getContacts("998",gson.toJson(libreta));

        ArrayList<UserAuxiliar> salida = new ArrayList<UserAuxiliar>();

        salida = gson.fromJson(entrada,new TypeToken<List<UserAuxiliar>>(){}.getType());

        ArrayList<UserAuxiliar> compare = new ArrayList<UserAuxiliar>();


        UserAuxiliar sep1 = new UserAuxiliar();
        sep1.set_id(-2);

        compare.add(sep1);
        compare.add(new UserAuxiliar(0,"UsuarioPrueba2",0,0));

        assertEquals(salida.get(0).get_id(),compare.get(0).get_id());
        assertEquals(salida.get(1).get_id(), compare.get(1).get_id());






    }


}
