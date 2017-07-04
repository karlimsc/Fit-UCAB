package edu.ucab.desarrollo.fitucab.Test.M11_Test;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.GetCaloriesConsumedDayCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.GetCaloriesConsumedWeekCommand;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by JoseA2R on 3/7/17.
 */
public class M11_Test_GetCaloriesWeek {

    @Test
    public void getCalWeek(){

        Entity Calories = EntityFactory.getCaloriesdate("josea2r");
        GetCaloriesConsumedWeekCommand cmd = CommandsFactory.getCaloriesWeekCmd(Calories);
        Entity cal = cmd.Respuesta;
        assertNotNull(cal);

    }

}
