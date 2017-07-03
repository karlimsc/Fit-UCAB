package edu.ucab.desarrollo.fitucab.Test.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M02.*;
import org.junit.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;

import java.text.*;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by root on 03/07/17.
 */
public class UserCommandTest {

    /**
     * Prueba del UserCommand comparando que el id sea el mismo
     */
    @Test
    public void UserCommandTestConstructor(){
        Command c = CommandsFactory.instanciateUserCmd(1);

        assertNotNull(c);

        assertEquals(((HomeCommand)c).getId(),1);
    }

    /**
     * Prueba del UserCommand para crear un usuario y comprueba que el usuario sea el mismo que se creo
     */
    @Ignore
    @Test
    public void UserCommandTestExecute(){
        Command uc = CommandsFactory.instanciateUserCmd(1);

        ((UserCommand)uc).execute();

        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("21/04/2017");
        formateador.format(ahora);

        Entity u1 = EntityFactory.createUser(1,"rafael","juanmacedoal@hotmail.com","m","1234",ahora,80,180);

        assertNotNull(((UserCommand)uc).Return());

        Entity u2=((UserCommand)uc).Return();

        assertTrue(((User)u2).getId() == ((User)u1).getId());
        assertTrue(((User)u2).getUser() == ((User)u1).getUser());
        assertTrue(((User)u2).getEmail()== ((User)u1).getEmail());
        assertTrue(((User)u2).getSex() == ((User)u1).getSex());
        assertTrue(((User)u2).getPhone() == ((User)u1).getPhone());
        assertTrue(((User)u2).getBirthdate() == ((User)u1).getBirthdate());
        assertTrue(((User)u2).getHeight() == ((User)u1).getHeight());
        assertTrue(((User)u2).getWeight() == ((User)u1).getWeight());
    }
}
