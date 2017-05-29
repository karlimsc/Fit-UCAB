import Domain.Nivel;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by root on 27/05/17.
 */
public class NivelTest {
    @Test
    public void getNivel() throws Exception {
        Nivel nivel = new Nivel();
        nivel.setNivel(1);
        assertTrue(nivel.getNivel()==1);
        System.out.println("Pruebas 1: "+ nivel.getNivel());


    }

    @Test
    public void setNivel() throws Exception {
        Nivel nivel = new Nivel();
        nivel.setNivel(1);
        assertTrue(nivel.getNivel()==1);

        System.out.println("Pruebas 2: "+ nivel.getNivel());

    }

}