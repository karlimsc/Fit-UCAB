package edu.ucab.desarrollo.fitucab.Test.M11_Test;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.updatePersoCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by JoseA2R on 3/7/17.
 */
public class M11_Test_UpdatePersoCmd {

    @Test
    public void update() {
        Entity updat = EntityFactory.getFoodall("Pasta",
                "500",
                "350",
                true,
                1);
        updatePersoCommand cmd = CommandsFactory.updatepersonCmd(updat);
        Entity respuesta = cmd.Respuesta;
        assertNull(respuesta);
    }

}
