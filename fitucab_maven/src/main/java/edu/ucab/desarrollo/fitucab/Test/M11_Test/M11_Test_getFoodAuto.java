package edu.ucab.desarrollo.fitucab.Test.M11_Test;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.getFoodAutoCommand;

import static org.junit.Assert.assertEquals;

/**
 * Created by JoseA2R on 3/7/17.
 */
public class M11_Test_getFoodAuto {

    public void getFood(){

        Entity auto = EntityFactory.getUsername("Josea2R");
        getFoodAutoCommand cmd = CommandsFactory.getFoodAutoCmd(auto);
        boolean resp = cmd.answer();
        assertEquals(resp,false);
    }

}
