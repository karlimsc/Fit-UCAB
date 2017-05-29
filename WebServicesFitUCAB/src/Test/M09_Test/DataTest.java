import Domain.Data;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by root on 27/05/17.
 */
public class DataTest {
    @Test
    public void getDatosTest() throws Exception {
        Data data = new Data();
        assertNotNull(data);
        System.out.println("Prueba 1:");
        System.out.println("Data id: " + data.getId() );
        System.out.println("Data nombre: "+ data.getName());
        System.out.println();
    }

    @Test
    public void getDescriptionTest() throws Exception {
        Data data = new Data();
        data.setDescription("prueba");
        assertTrue(data.getDescription()=="prueba");

        System.out.println("Prueba 2:");
        System.out.println("DescripcionGet: "+ data.getDescription());
        System.out.println();
    }

    @Test
    public void setDescriptionTest() throws Exception {
        Data data = new Data();
        data.setDescription("prueba");
        assertTrue(data.getDescription()=="prueba");

        System.out.println("Prueba 3:");
        System.out.println("DescripcionSet" + data.getDescription());
        System.out.println();
    }

    @Test
    public void getScoreTest() throws Exception {
        Data data = new Data();
        data.setScore(1);
        assertTrue(data.getScore()==1);

        System.out.println("Prueba 4:");
        System.out.println("PuntajeGet: "+ data.getScore());
        System.out.println();
    }

    @Test
    public void setScoreTest() throws Exception {
        Data data = new Data();
        data.setScore(1);
        assertTrue(data.getScore()==1);

        System.out.println("Prueba 5:");
        System.out.println("PuntajeSet: "+ data.getScore());
        System.out.println();
    }

    @Test
    public void getLevelTest() throws Exception {
        Data data = new Data();
        data.setLevel(1);
        assertTrue(data.getLevel()==1);

        System.out.println("Prueba 6:");
        System.out.println("NivelGet: "+ data.getLevel());
        System.out.println();
    }

    @Test
    public void setLevelTest() throws Exception {
        Data data = new Data();
        data.setLevel(1);
        assertTrue(data.getLevel()==1);

        System.out.println("Prueba 7:");
        System.out.println("NivelSet: "+ data.getLevel());
        System.out.println();
    }

    @Test
    public void getIdTest() throws Exception {
        Data data = new Data();
        data.setId(1);
        assertTrue(data.getId()==1);

        System.out.println("Prueba 8:");
        System.out.println("IdGet: "+ data.getId());
        System.out.println();
    }

    @Test
    public void setIdTest() throws Exception {
        Data data = new Data();
        data.setId(1);
        assertTrue(data.getId()==1);

        System.out.println("Prueba 9:");
        System.out.println("IdSet: "+ data.getId());
        System.out.println();
    }

    @Test
    public void getNameTest() throws Exception {
        Data data = new Data();
        data.setName("prueba");
        assertTrue(data.getName()=="prueba");

        System.out.println("Prueba 10:");
        System.out.println("NombreGet: "+ data.getName());
        System.out.println();
    }

}