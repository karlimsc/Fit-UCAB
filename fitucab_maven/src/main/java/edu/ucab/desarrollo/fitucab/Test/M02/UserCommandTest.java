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
 * Clase de prueba para los comandos de la entidad User
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 2.0
 */
public class UserCommandTest {

    /**
     * Prueba del UserCommand comparando que el id sea el mismo
     */
    @Test
    public void UserCommandTestConstructor(){
        Command _c = CommandsFactory.instanciateUserCmd(1);

        assertNotNull(_c);

        assertEquals(((HomeCommand)_c).getId(),1);
    }

    /**
     * Prueba del UserCommand para crear un usuario y comprueba que el usuario sea el mismo que se creo
     */
    @Ignore
    @Test
    public void UserCommandTestExecute(){
        Command _uc = CommandsFactory.instanciateUserCmd(1);

        ((UserCommand)_uc).execute();

        Date _now = new Date();
        SimpleDateFormat _format = new SimpleDateFormat("21/04/2017");
        _format.format(_now);

        Entity _u1 = EntityFactory.createUser(1,"rafael","juanmacedoal@hotmail.com","m","1234",_now,80,180);

        assertNotNull(((UserCommand)_uc).Return());

        Entity _u2=((UserCommand)_uc).Return();

        assertTrue(((User)_u2).getId() == ((User)_u1).getId());
        assertTrue(((User)_u2).getUser() == ((User)_u1).getUser());
        assertTrue(((User)_u2).getEmail()== ((User)_u1).getEmail());
        assertTrue(((User)_u2).getSex() == ((User)_u1).getSex());
        assertTrue(((User)_u2).getPhone() == ((User)_u1).getPhone());
        assertTrue(((User)_u2).getBirthdate() == ((User)_u1).getBirthdate());
        assertTrue(((User)_u2).getHeight() == ((User)_u1).getHeight());
        assertTrue(((User)_u2).getWeight() == ((User)_u1).getWeight());
    }
}
