package edu.ucab.desarrollo.fitucab.Test.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M02.*;
import org.junit.jupiter.api.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by root on 03/07/17.
 */
public class UpdateUserCommandTest {

    /**
     * Prueba del constructor de UpdateUserCommand que no de null
     */
    @Test
    public void UpdateUserCommandConstructorTest()
    {
        Command u = CommandsFactory.instanciateUpdateUserCmd (1, "juan", "1234", "corre@hotmail.com");

        assertNotNull(u);

        assertEquals(((UpdateUserCommand)u).get_id(), 1);
        assertEquals(((UpdateUserCommand)u).get_username(), "juan");
        assertEquals(((UpdateUserCommand)u).get_phone(), "1234");
        assertEquals(((UpdateUserCommand)u).get_email(), "corre@hotmail.com");

    }

    /**
     * Prueba de UpdateUser de exito de la actualizacion de usuario
     * @throws Exception
     */
    @Test
    public void UpdateUserCommandExecuteTest() throws Exception {
      Boolean test = true;

        Command u = CommandsFactory.instanciateUpdateUserCmd (1, "juan", "1234", "corre@hotmail.com");

        ((UpdateUserCommand)u).execute();

        assertNotNull(u.ReturnUpdate());
        assertEquals(u.ReturnUpdate(),test);
    }




}
