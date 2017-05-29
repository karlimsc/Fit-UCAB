import Domain.Detail;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by root on 27/05/17.
 */
public class DetailTest {
    @Test
    public void DetalleTest() throws Exception {
        Detail detail = new Detail();
        assertNotNull(detail);

        System.out.println("Prueba 1:");
        System.out.println("Id: "+ detail.get_id());
        System.out.println("RetoId: "+ detail.get_challengeId());
        System.out.println("Comienzo: "+ detail.get_start());
        System.out.println("Fin: "+ detail.get_end());
        System.out.println("Terminada: "+ detail.is_ended());
        System.out.println("Posicion :"+ detail.get_position());
        System.out.println();

    }

    @Test
    public void getId() throws Exception {
        Detail detail = new Detail();
        detail.set_id(1);
        assertTrue(detail.get_id()==1);

        System.out.println("Prueba 2:");
        System.out.println("IdGet: "+ detail.get_id());
        System.out.println();
    }

    @Test
    public void setId() throws Exception {
        Detail detail = new Detail();
        detail.set_id(1);
        assertTrue(detail.get_id()==1);

        System.out.println("Prueba 2:");
        System.out.println("IdSet: "+ detail.get_id());
        System.out.println();
    }


    @Test
    public void getRetoId() throws Exception {
        Detail detail = new Detail();
        detail.set_challengeId(1);
        assertTrue(detail.get_challengeId()==1);

        System.out.println("Prueba 3:");
        System.out.println("RetoIdGet: "+ detail.get_challengeId());
        System.out.println();

    }

    @Test
    public void setRetoId() throws Exception {
        Detail detail = new Detail();
        detail.set_challengeId(1);
        assertTrue(detail.get_challengeId()==1);

        System.out.println("Prueba 4:");
        System.out.println("RetoIdSet: "+ detail.get_challengeId());
        System.out.println();
    }

    @Test
    public void getComienzo() throws Exception {
        Detail detail = new Detail();
        detail.set_start("prueba");
        assertTrue(detail.get_start()=="prueba");

        System.out.println("Prueba 5:");
        System.out.println("ComienzoGet: "+ detail.get_start());
        System.out.println();
    }

    @Test
    public void setComienzo() throws Exception {
        Detail detail = new Detail();
        detail.set_start("prueba");
        assertTrue(detail.get_start()=="prueba");

        System.out.println("Prueba 6:");
        System.out.println("ComienzoSet: "+ detail.get_start());
        System.out.println();
    }

    @Test
    public void getFin() throws Exception {
        Detail detail = new Detail();
        detail.set_end("prueba");
        assertTrue(detail.get_end()=="prueba");

        System.out.println("Prueba 7:");
        System.out.println("FinGet: "+ detail.get_end());
        System.out.println();
    }

    @Test
    public void setFin() throws Exception {
        Detail detail = new Detail();
        detail.set_end("prueba");
        assertTrue(detail.get_end()=="prueba");

        System.out.println("Prueba 8:");
        System.out.println("FinSet: "+ detail.get_end());
        System.out.println();
    }

    @Test
    public void isTerminada() throws Exception {
        Detail detail = new Detail();
        detail.set_ended(false);
        assertTrue(detail.is_ended()==false);

        System.out.println("Prueba 9:");
        System.out.println("TerminadaIs: "+ detail.is_ended());
        System.out.println();
    }

    @Test
    public void setTerminada() throws Exception {
        Detail detail = new Detail();
        detail.set_ended(false);
        assertTrue(detail.is_ended()==false);

        System.out.println("Prueba 10:");
        System.out.println("TerminadaSet: "+ detail.is_ended());
        System.out.println();
    }

    @Test
    public void getPosicion() throws Exception {
        Detail detail = new Detail();
        detail.set_position(1);
        assertTrue(detail.get_position()==1);

        System.out.println("Prueba 11:");
        System.out.println("PosicionGet: "+ detail.get_position());
        System.out.println();
    }

    @Test
    public void setPosicion() throws Exception {
        Detail detail = new Detail();
        detail.set_position(1);
        assertTrue(detail.get_position()==1);

        System.out.println("Prueba 12:");
        System.out.println("PosicionSet: "+ detail.get_position());
        System.out.println();
    }

}