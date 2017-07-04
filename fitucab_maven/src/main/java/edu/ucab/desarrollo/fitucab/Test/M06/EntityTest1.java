package edu.ucab.desarrollo.fitucab.Test.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import junit.framework.Assert;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Barbara Fernadez on 7/1/2017.
 */
class EntityTest1 {
    @Test
    void get_id() {
        int pruebanumero = 1;
        Entity prueba = new Entity();
        prueba.set_id(pruebanumero);
        assertEquals(prueba.get_id(),pruebanumero);
    }

    @Test
    void set_id() {
        int pruebanumero = 1;
        Entity prueba = new Entity();
        prueba.set_id(pruebanumero);
        assertEquals(prueba.get_id(),pruebanumero);
    }

    @Test
    void get_errorCode() {
        int pruebanumero = 1;
        Entity prueba = new Entity();
        prueba.set_errorCode(pruebanumero);
        assertEquals(prueba.get_errorCode(),pruebanumero);

    }

    @Test
    void set_errorCode() {
        int pruebanumero = 1;
        Entity prueba = new Entity();
        prueba.set_errorCode(pruebanumero);
        assertEquals(prueba.get_errorCode(),pruebanumero);
    }

    @Test
    void get_errorMsg() {
        String pruebanumero = "daniel";
        Entity prueba = new Entity();
        prueba.set_errorMsg(pruebanumero);
        assertEquals(prueba.get_errorMsg(),pruebanumero);
    }

    @Test
    void set_errorMsg() {
        String pruebanumero = "daniel";
        Entity prueba = new Entity();
        prueba.set_errorMsg(pruebanumero);
        assertEquals(prueba.get_errorMsg(),pruebanumero);
    }

}