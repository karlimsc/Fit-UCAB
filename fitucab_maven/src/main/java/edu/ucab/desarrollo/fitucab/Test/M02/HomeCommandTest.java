package M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M02.*;
import org.junit.jupiter.api.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Clase de prueba para los comandos de la entidad Home
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 2.0
 */
public class HomeCommandTest  {
    /**
     * Prueba de exito del constructor del HomeCommand
     */
    @Test
    public void HomeCommandTestExito(){
        Command _h = CommandsFactory.instanciateHomeCmd(1);

        assertNotNull(_h);

        assertEquals(((HomeCommand)_h).getId(),1);
    }

    /**
     * Prueba de exito que devuelva un valor no null de un entity
     */
    @Test
    public void HomeCommandTestEntity()
    {
        Command _h = CommandsFactory.instanciateHomeCmd(1);

        ((HomeCommand)_h).execute();

        Entity _h1 = EntityFactory.createHome(800,0);

        assertNotNull(((HomeCommand)_h).Return());

        Entity h2=((HomeCommand)_h).Return();

        assertTrue(((Home)h2).getTotalWater() == ((Home)_h1).getTotalWater());
        assertTrue(((Home)h2).getTotalCalories() == ((Home)_h1).getTotalCalories());

    }



}
