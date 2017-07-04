package edu.ucab.desarrollo.fitucab.Test.M11_Test;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.insertUnAlimentoCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by JoseA2R on 3/7/17.
 */
public class M11_Test_insertUnAlimento {

    @Test
    public void insertAlimento(){

        Entity pu = EntityFactory.getFoodall("Pasta",
                                              "500",
                                                "350",
                                                true,
                                                    1);
        insertUnAlimentoCommand cmd = CommandsFactory.insertarAlimentoCmd(pu);
        Entity Comida = cmd.Respuesta;
        assertNull(Comida);

    }

}
