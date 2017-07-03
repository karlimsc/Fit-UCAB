package edu.ucab.desarrollo.fitucab.Test.M01_ServicesUserTest;

import edu.ucab.desarrollo.fitucab.common.entities.Registry;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class RegistryTest {

    @Before
    public void setUp() throws Exception {
    }


    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getIdRegistry() throws Exception {

        // public Registry(int idRegistry)
        Registry user = new Registry(1);
        assertEquals(1,user.getIdRegistry());

    }

    @Test
    public void setIdRegistry() throws Exception {

        Registry user = new Registry(1);
        user.setIdRegistry(2);
        assertEquals(2,user.getIdRegistry());
    }

    @Test
    public void getWeight() throws Exception {

        //public Registry( float weight,float height)
        Registry user = new Registry(12,2);
        assertEquals(12,user.getWeight(),12);
    }

    @Test
    public void setWeight() throws Exception {

        Registry user = new Registry();
        user.setWeight(12);
        assertEquals(12,user.getWeight(),12);
    }

    @Test
    public void getHeight() throws Exception {

        Registry user = new Registry();
        user.setHeight(12);
        assertEquals(12,user.getHeight(),12);
        user.setHeight(123);
        assertNotEquals(14,user.getHeight());
    }

    @Test
    public void setHeight() throws Exception {

        Registry user = new Registry();
        user.setHeight(12);
        assertEquals(12,user.getHeight(),12);
    }

    @Test
    public void getRegistryPoint() throws Exception {
        Registry user = new Registry(1,1,1);
        assertEquals(1.0,user.getIdRegistry(),1.0);
    }

    @Test
    public void setregistryPoint() throws Exception {

        Registry user = new Registry();
        user.setIdRegistry(23);
        assertEquals(23,user.getIdRegistry());
    }

}