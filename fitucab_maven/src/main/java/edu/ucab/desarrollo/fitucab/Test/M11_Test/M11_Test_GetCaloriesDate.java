package edu.ucab.desarrollo.fitucab.Test.M11_Test;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.GetCaloriesConsumedWeekCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.GetCaloriesDateCommand;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by JoseA2R on 3/7/17.
 */
public class M11_Test_GetCaloriesDate {

    @Test
    public void getCalDate(){

        Entity Calories = EntityFactory.getCaloriesDate("20/10/2000","josea2r");
        GetCaloriesDateCommand cmd = CommandsFactory.getCaloriesDateCmd(Calories);
        Entity cal = cmd.Respuesta;
        assertNotNull(cal);

    }
}
