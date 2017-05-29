package M03_Test;

import Domain.Sql;
import Domain.UserAuxiliar;
import WebServicesClasses.M03_ServicesContacts;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by andres on 29/05/17.
 */
public class M03_ServicesContactsTest {
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
        sep1.set_id(-1);
        UserAuxiliar sep2 = new UserAuxiliar();
        sep2.set_id(-2);


        compare.add(sep1);
        compare.add(new UserAuxiliar(999,"UsuarioPrueba2",0,0));
        compare.add(sep2);
        compare.add(new UserAuxiliar("pepe","email.com","22222"));

        assertTrue((salida.get(0).get_id()==compare.get(0).get_id())&&(salida.get(1).get_id()==compare.get(1).get_id())&&(salida.get(2).get_id()==compare.get(2).get_id())&&(salida.get(3).get_id()==compare.get(3).get_id()));


    }

}