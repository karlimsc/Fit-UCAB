package M02_Test;

import Domain.Home;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class HomeTest {
    Home home;

    @Test
    public void testHomeCreated(){
        home = new Home(1, 2);
        assertEquals("Home creado: ", 2, home.getTotalAgua());
        assertTrue(home.getTotalCaloria() == 1);
    }

    @Test
    public void testHomeNotCreated(){
        assertEquals("Home sin crear: ", null, home);
    }

    @Test
    public void testHomeEmpty() {
        home = new Home();
        assertTrue(home.getTotalAgua() == 0);
    }

    @Test
    public void setTotalAguaTestPUE() {
        Home home = new Home(1,1);
        home.setTotalAgua(1);
        assertEquals(1,home.getTotalAgua());
    }


    @Test
    public void setTotalCaloriaTestPUE() {
        Home home = new Home((float) 1,1);
        home.setTotalCaloria((float) 1);
        assertEquals((float) 1,home.getTotalCaloria(),(float) 1);
    }


    @Test
    public void getTotalCaloriaTestPUE() {
        Home home = new Home((float) 1,1);
        assertEquals((float) 1,home.getTotalCaloria(),(float) 1);
    }


    @Test
    public void getTotalAguaTestPUE() {
        Home home = new Home(1,1);
        assertEquals(1,home.getTotalAgua());
    }


    @Test(expected = AssertionError.class)
    public void getTotalAguaTestPUFE() {
        Home home = new Home(1,1);
        assertNull(home.getTotalAgua());
    }


    @Test(expected = AssertionError.class)
    public void getTotalCaloriaTestPUFE() {
        Home home = new Home(1,1);
        assertNull(home.getTotalCaloria());
    }


    @Test(expected = AssertionError.class)
    public void setTotalAguaTestPUFE() {
        Home home = new Home(1,1);
        home.setTotalAgua(2);
        assertNull(home.getTotalAgua());
    }


    @Test(expected = AssertionError.class)
    public void setTotalCaloriaTestPUFE() {
        Home home = new Home((float) 1,1);
        home.setTotalCaloria((float) 2);
        assertNull(home.getTotalCaloria());
    }


    @Test(expected = AssertionError.class)
    public void HomeTestPUF() {
        Home home = null;
        assertNotNull(home);
    }


    @Test(expected = AssertionError.class)
    public void HomeTestPUE() {
        Home home = new Home (1,1);
        assertNull(home);
    }

}