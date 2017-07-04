package edu.ucab.desarrollo.fitucab.Test.M11_Test;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.GetCaloriesConsumedMonthCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.GetCaloriesConsumedWeekCommand;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by JoseA2R on 3/7/17.
 */
public class M11_Test_GetCaloriesMonth {

    @Test
    public void getCalMonth(){

        Entity Calories = EntityFactory.getCaloriesdate("josea2r");
        GetCaloriesConsumedMonthCommand cmd = CommandsFactory.getCaloriesMonthCmd(Calories);
        Entity cal = cmd.Respuesta;
        assertNotNull(cal);

    }

}
