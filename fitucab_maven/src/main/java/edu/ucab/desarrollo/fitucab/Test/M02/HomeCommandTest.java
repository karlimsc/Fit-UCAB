package edu.ucab.desarrollo.fitucab.Test.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M02.*;
import org.junit.jupiter.api.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by root on 03/07/17.
 */
public class HomeCommandTest  {
    /**
     * Prueba de exito del constructor del HomeCommand
     */
    @Test
    public void HomeCommandTestExito(){
        Command h = CommandsFactory.instanciateHomeCmd(1);

        assertNotNull(h);

        assertEquals(((HomeCommand)h).getId(),1);
    }

    /**
     * Prueba de exito que devuelva un valor no null de un entity
     */
    @Test
    public void HomeCommandTestEntity()
    {
        Command h = CommandsFactory.instanciateHomeCmd(1);

        ((HomeCommand)h).execute();

        Entity h1 = EntityFactory.createHome(800,0);

        assertNotNull(((HomeCommand)h).Return());

        Entity h2=((HomeCommand)h).Return();

        assertTrue(((Home)h2).getTotalAgua() == ((Home)h1).getTotalAgua());
        assertTrue(((Home)h2).getTotalCaloria() == ((Home)h1).getTotalCaloria());

    }



}
