import Domain.Achievement;
import Domain.Data;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by root on 27/05/17.
 */
public class LogrosTest {

    @Test
    public void LogrosTest(){
        Achievement logros = new Achievement(2);
        assertNotNull(logros);
        System.out.println("Prueba 1: "+ logros.toString());
    }

    @Test
    public void setRetos() throws Exception {
        Achievement logros = new Achievement(1);
        Data data = new Data();
        data.setName("Data 1");
        logros.set_challenges(data);
        Data dato[] = logros.get_challenge();
        assertTrue(dato[0].getName()=="Data 1");

        System.out.println("Prueba 2: \n"+ dato[0].getName() );


    }

}