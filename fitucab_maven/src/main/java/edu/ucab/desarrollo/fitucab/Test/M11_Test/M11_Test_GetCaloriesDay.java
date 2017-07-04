package edu.ucab.desarrollo.fitucab.Test.M11_Test;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.GetCaloriesConsumedDayCommand;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;

/**
 * Created by JoseA2R on 3/7/17.
 */
public class M11_Test_GetCaloriesDay {

    @Test
    public void getCalDay(){

        Entity Calories = EntityFactory.getCaloriesdate("josea2r");
        GetCaloriesConsumedDayCommand cmd = CommandsFactory.getCaloriesDayCmd(Calories);
        Entity cal = cmd.Respuesta;
        assertNotNull(cal);

    }

}
