package com.fitucab.ds1617b.fitucab.M01_Test;

import com.fitucab.ds1617b.fitucab.Model.Registry;
import com.fitucab.ds1617b.fitucab.Model.User;

import org.junit.Test;
import org.junit.*;

import static org.junit.Assert.*;

public class UserTest {



    User user = new User(1,"daniel","daniel","daniel","daniel",'F',"daniel",1,1,1);
    Registry regis = new Registry(1,1);
    User user1 = new User(1,"daniel1","daniel1","daniel1","daniel1",'F',"daniel1",regis);


    @Test
    public void get_idUser() throws Exception {

        assertEquals(1,user.get_idUser());
        assertNotNull(user.get_idUser());
    }

    @Test
    public void set_idUser() throws Exception {
        user.set_idUser(2);
        assertEquals(2,user.get_idUser());


    }

    @Test
    public void get_username() throws Exception {

        assertEquals("daniel",user.get_username());
        assertNotNull(user.get_username());

    }

    @Test
    public void set_username() throws Exception {
        user.set_username("dan");
        assertEquals("dan",user.get_username());
    }

    @Test
    public void get_password() throws Exception {
        assertEquals("daniel",user.get_password());
        assertNotNull(user.get_password());
    }

    @Test
    public void set_password() throws Exception {
        user.set_password("dan");
        assertEquals("dan",user.get_password());

    }

    @Test
    public void get_email() throws Exception {
            assertEquals("daniel",user.get_email());
        assertNotNull(user.get_email());
    }

    @Test
    public void set_email() throws Exception {
        user.set_email("dan");
        assertNotNull("dan",user.get_email());

    }

    @Test
    public void get_phone() throws Exception {


            assertEquals("daniel",user.get_phone());
        assertNotNull(user.get_phone());
    }

    @Test
    public void set_phone() throws Exception {
        user.set_phone("dan");
        assertEquals("dan",user.get_phone());
    }

    @Test
    public void get_sex() throws Exception {

            assertEquals('F',user.get_sex());
            assertNotNull(user.get_sex());
    }

    @Test
    public void set_sex() throws Exception {

        user.set_sex('M');
        assertEquals('M',user.get_sex());
    }

    @Test
    public void get_birthdate() throws Exception {

            assertEquals("daniel",user.get_birthdate());
            assertNotNull(user.get_birthdate());
    }

    @Test
    public void set_birthdate() throws Exception {
        user.set_birthdate("dan");
        assertEquals("dan",user.get_birthdate());
    }

    @Test
    public void get_height() throws Exception {
        assertEquals(1,user.get_height(),1);
        assertNotNull(user.get_height());
    }

    @Test
    public void set_height() throws Exception {

        user.set_height(1);
        assertEquals(1,user.get_height(),1);
    }

    @Test
    public void get_weight() throws Exception {
        assertEquals(1,user.get_weight(),1);
        assertNotNull(user.get_weight());
    }

    @Test
    public void set_weight() throws Exception {
        user.set_weight(1);
        assertEquals(1,user.get_weight(),1);
    }

    @Test
    public void get_point() throws Exception {

        assertEquals(1,user.get_point(),1);
        assertNotNull(user.get_point());
    }

    @Test
    public void set_point() throws Exception {

    }

    @Test
    public void get_registry() throws Exception {

       assertEquals(1,user1.get_height(),1);

    }

    @Test
    public void set_registry() throws Exception {
        user1.set_weight(1);
        assertEquals(1,user1.get_weight(),1);

    }

}