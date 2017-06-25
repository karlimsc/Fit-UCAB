package Test.M09_Test;


import Domain.Size;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by david on 6/25/17.
 */
public class TamanoTest {
    @Test
    public void TamañoTest(){
        Size tamaño = new Size();
        assertNotNull(tamaño);

        System.out.println("Prueba 1: tamaño logrado = " + tamaño.getLogrado());
        System.out.println("\n tamaño no logrado = " + tamaño.getLogrado());
    }

    @Test
    public void setLogrado() throws Exception {
        Size tamaño = new Size();
        tamaño.setLogrado(1);
        assertTrue(1 ==tamaño.getLogrado());

        System.out.println("Prueba 2: tamaño logrado = " + tamaño.getLogrado());
    }

    @Test
    public void setNoLogrado() throws Exception {
        Size tamaño = new Size();
        tamaño.setNoLogrado(1);
        assertTrue(1 ==tamaño.getNoLogrado());

        System.out.println("Prueba 3: tamaño no logrado = " + tamaño.getNoLogrado());
    }

    @Test
    public void getLogrado() throws Exception {
        Size tamaño = new Size();
        tamaño.setLogrado(1);
        assertTrue(1 ==tamaño.getLogrado());

        System.out.println("Prueba 4: tamaño logrado = " + tamaño.getLogrado());
    }

    @Test
    public void getNoLogrado() throws Exception {
        Size tamaño = new Size();
        tamaño.setNoLogrado(1);
        assertTrue(1 ==tamaño.getNoLogrado());

        System.out.println("Prueba 5: tamaño no logrado = " + tamaño.getNoLogrado());
    }
}
