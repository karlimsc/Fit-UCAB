package M02_Test;

import Domain.Home;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by david on 5/29/17.
 */
public class HomeTest {
    Home home;

    @Test
    public void testHomeCreated(){
        home = new Home(1, 2);
        assertEquals("Home creado: ", 2, home.getTotalAgua());
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
}