package com.fitucab.ds1617b.fitucab.M03_Test;

import com.fitucab.ds1617b.fitucab.Model.UserAuxiliar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static com.fitucab.ds1617b.fitucab.R.id.nombre;
import static org.junit.Assert.*;

/**
 * Created by Raul A on 5/29/2017.
 */
public class UserAuxiliarTest {

    UserAuxiliar user1 = new UserAuxiliar(1, "Raul Tuozzo",9000,"m","1994-11-24","5");
    UserAuxiliar user2 = new UserAuxiliar("Raul Tuozzo","ratuozzo@gmail.com","04141150083","m");
    UserAuxiliar user3 = new UserAuxiliar("100","200");
    UserAuxiliar user4 = new UserAuxiliar("Raul Tuozzo","ratuozzo@gmail.com","04141150083",3);

    @Test
    public void get_distancia() throws Exception {
        assertEquals(user1.get_distancia(),"5");
    }

    @Test
    public void set_distancia() throws Exception {
        user1.set_distancia("10");
        assertEquals(user1.get_distancia(),"10");
    }

    @Test
    public void get_email() throws Exception {
        assertEquals(user2.get_email(),"ratuozzo@gmail.com");
    }

    @Test
    public void set_email() throws Exception {
        user2.set_email("ratuozzocambio@gmail.com");
        assertEquals(user2.get_email(),"ratuozzocambio@gmail.com");
    }

    @Test
    public void get_phone() throws Exception {
        assertEquals(user2.get_phone(),"04141150083");
    }

    @Test
    public void set_phone() throws Exception {
        user2.set_phone("123456789");
        assertEquals(user2.get_phone(),"123456789");
    }

    @Test
    public void get_sex() throws Exception {
        assertEquals(user1.get_sex(),"m");
    }

    @Test
    public void set_sex() throws Exception {
        user1.set_sex("f");
        assertEquals(user1.get_sex(),"f");
    }

    @Test
    public void get_birthdate() throws Exception {
        assertEquals(user1.get_birthdate(),"1994-11-24");
    }

    @Test
    public void set_birthdate() throws Exception {
        user1.set_birthdate("2017-2-2");
        assertEquals(user1.get_birthdate(),"2017-2-2");
    }

    @Test
    public void get_longitud() throws Exception {
        assertEquals(user3.get_longitud(),"100");
    }

    @Test
    public void set_longitud() throws Exception {
        user3.set_longitud("400");
        assertEquals(user3.get_longitud(),"400");
    }

    @Test
    public void get_latitud() throws Exception {
        assertEquals(user3.get_latitud(),"200");
    }

    @Test
    public void set_latitud() throws Exception {
        user3.set_latitud("800");
        assertEquals(user3.get_latitud(),"800");
    }

    @Test
    public void get_username() throws Exception {
        assertEquals(user1.get_username(),"Raul Tuozzo");
    }

    @Test
    public void get_id() throws Exception {
        assertEquals(user1.get_id(),1);
    }

    @Test
    public void set_id() throws Exception {
        user1.set_id(4);
        assertEquals(user1.get_id(),4);
    }

    @Test
    public void set_username() throws Exception {
        user1.set_username("Long Parselon");
        assertEquals(user1.get_username(),"Long Parselon");
    }

    @Test
    public void get_point() throws Exception {
        assertEquals(user1.get_point(),9000);
    }

    @Test
    public void set_point() throws Exception {
        user1.set_point(400);
        assertEquals(user1.get_point(),400);
    }

    @Test
    public void get_type() throws Exception {
        assertEquals(user4.get_type(),3);
    }

    @Test
    public void set_type() throws Exception {
        user4.set_type(10);
        assertEquals(user4.get_type(),10);
    }

}