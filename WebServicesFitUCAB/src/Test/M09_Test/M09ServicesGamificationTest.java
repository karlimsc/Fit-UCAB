import WebServicesClasses.M09_ServicesGamification;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * Created by root on 27/05/17.
 */
public class M09ServicesGamificationTest {
    @Test
    public void ConstructorTest() throws Exception {
        M09_ServicesGamification m09ServicesGamification = new M09_ServicesGamification();
        assertNotNull(m09ServicesGamification);

    }

    @Test
    public void getRetosTest() throws Exception {

        M09_ServicesGamification retos = new M09_ServicesGamification();
        int prueba = 1;

        String tamaño = retos.getRetos(prueba);

        assertNotNull(tamaño);
        System.out.print("Retos" + tamaño.toString());


    }
    @Test
    public void getCantidadTest() throws Exception {

        M09_ServicesGamification grafica = new M09_ServicesGamification();
        int prueba = 1;

        String tamaño = grafica.getCantidad(prueba);

        assertNotNull(tamaño);
        System.out.print("Cantidad" + tamaño.toString());


    }

    @Test
    public void getQuantityTest() throws Exception {
        M09_ServicesGamification nivel = new M09_ServicesGamification();
        int prueba = 1;

        String tamaño = nivel.getQuantity(prueba);

        assertNotNull(tamaño);

        System.out.println("Nivel: " + tamaño.toString());
        System.out.println();
    }


}