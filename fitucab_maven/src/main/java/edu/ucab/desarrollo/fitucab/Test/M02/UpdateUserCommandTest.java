package edu.ucab.desarrollo.fitucab.Test.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M02.*;
import org.junit.jupiter.api.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Clase de prueba para los comandos de la entidad User
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 2.0
 */
public class UpdateUserCommandTest {

    /**
     * Prueba del constructor de UpdateUserCommand que no de null
     */
    @Test
    public void UpdateUserCommandConstructorTest()
    {
        Command _u = CommandsFactory.instanciateUpdateUserCmd (1, "juan", "1234", "corre@hotmail.com");

        assertNotNull(_u);

        assertEquals(((UpdateUserCommand)_u).get_id(), 1);
        assertEquals(((UpdateUserCommand)_u).get_username(), "juan");
        assertEquals(((UpdateUserCommand)_u).get_phone(), "1234");
        assertEquals(((UpdateUserCommand)_u).get_email(), "corre@hotmail.com");

    }

    /**
     * Prueba de UpdateUser de exito de la actualizacion de usuario
     * @throws Exception
     */
    @Test
    public void UpdateUserCommandExecuteTest() throws Exception {
      Boolean _test = true;

        Command _u = CommandsFactory.instanciateUpdateUserCmd (1, "juan", "1234", "corre@hotmail.com");

        ((UpdateUserCommand)_u).execute();

        assertNotNull(_u.ReturnUpdate());
        assertEquals(_u.ReturnUpdate(),_test);
    }




}
