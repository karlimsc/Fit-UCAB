package edu.ucab.desarrollo.fitucab.Test.M01_ServicesUserTest;


import edu.ucab.desarrollo.fitucab.common.entities.User;
import org.junit.Test;

import static org.junit.Assert.*;


public class UserTest {


    @Test
    public void getId() throws Exception {
        User user = new User(1);
        assertEquals(1,user.getId());

        //hcaer este para todos.
        assertNotNull(user);
    }

    @Test
    public void setId() throws Exception {
        User user = new User(1);
        user.setId(2);
        assertNotEquals(1,user.getId());
        assertEquals(2,user.getId());

    }


    @Test
    public void getUser() throws Exception {

        User user = new User("daniel","holacomoestas");
        assertEquals("daniel",user.getUser());
    }

    @Test
    public void setUser() throws Exception {

        User user = new User("daniel","holacomoestas");
        user.setUser("pablo");
        assertEquals("pablo",user.getUser());
        user.setUser(null);
        assertNull(user.getUser());
    }

    @Test
    public void getPassword() throws Exception {
        User user = new User("daniel","holacomoestas");
        assertEquals("holacomoestas",user.getPassword());

    }

    @Test
    public void setPassword() throws Exception {

        User user = new User("daniel","holacomoestas");
        user.setPassword("holacomoestassss");
        assertEquals("holacomoestassss",user.getPassword());
        user.setPassword(null);
        assertNull(user.getPassword());

        //probare este a ver
        assertNotEquals(user.getUser(),user.getPassword());
    }

    @Test
    public void getEmail() throws Exception {
        User user = new User("daniel@gmail.com");
        assertEquals("daniel@gmail.com",user.getEmail());
    }

    @Test
    public void setEmail() throws Exception {

        User user = new User("daniel@gmail.com");
        user.setEmail("karli@gmail.com");
        assertEquals("karli@gmail.com",user.getEmail());
        user.setEmail(null);
        assertNull(user.getEmail());
    }

    @Test
    public void getSex() throws Exception {

        User user = new User();
        user.setSex("M");
        assertEquals("M",user.getSex());
        assertNotEquals("F",user.getSex());
    }

    @Test
    public void setSex() throws Exception {

        User user = new User();
        user.setSex("M");
        assertEquals("M",user.getSex());
        assertNotEquals("F",user.getSex());
        user.setSex(null);
        assertNull(user.getSex());
    }

    @Test
    public void getPhone() throws Exception {
        User user = new User();
        user.setPhone("04122584809");
        assertEquals("04122584809",user.getPhone());
    }

    @Test
    public void setPhone() throws Exception {
        User user = new User();
        user.setPhone("04122584809");
        assertEquals("04122584809",user.getPhone());
        User user1 = new User();
        user1.setPassword(null);
        assertNull(user1.getPhone());
    }

    @Test
    public void get_birthdate() throws Exception {


        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        User user = new User("daniel","daniel","daniel","daniel","daniel", (java.sql.Date) date);
        assertEquals(date,user.getBirthdate());

    }

    @Test
    public void set_birthdate() throws Exception {
        long millis=System.currentTimeMillis();
        java.sql.Date fecha = new java.sql.Date(millis);
        User user = new User("daniel","daniel","daniel","daniel","daniel", fecha);
        assertEquals(fecha,user.getBirthdate());
        user.setBirthdate(null);
        assertNull(user.getBirthdate());

    }




}