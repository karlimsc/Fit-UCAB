package edu.ucab.desarrollo.fitucab.Test.M11_Test;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.getSuggestionCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by JoseA2R on 3/7/17.
 */
public class M11_Test_getSuggestions {

    @Test
    public void suggest(){

        Entity suggest = EntityFactory.getUserCal("Josea2r", "2500");
        getSuggestionCommand cmd = CommandsFactory.getSuggestionCmd(suggest);
        boolean ans = cmd.answer();
        assertEquals(ans,false);


    }

}
