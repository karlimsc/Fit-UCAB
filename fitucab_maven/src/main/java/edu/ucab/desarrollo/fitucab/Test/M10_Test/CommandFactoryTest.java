package edu.ucab.desarrollo.fitucab.Test.M10_Test;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Sql;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M10.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Raul A on 7/2/2017.
 */
public class CommandFactoryTest {


    @Test
    public void instantiateAddWater(){

        Entity WaterObject = EntityFactory.createWater();
        AddWaterCommand cmd = CommandsFactory.instatiateAddWaterCmd(WaterObject);

        assertNotNull(cmd);
    }

    @Test
    public void instantiateDeleteLast(){

        Entity WaterObject = EntityFactory.createWater();
        DeletLastCommand cmd = CommandsFactory.instatiateDeletLastCmd(WaterObject);

        assertNotNull(cmd);
    }

    @Test
    public void instantiateGetFecha(){

        Entity WaterObject = EntityFactory.createWater();
        GetFechaCommand cmd = CommandsFactory.instatiateGetFechaCmd(WaterObject);

        assertNotNull(cmd);
    }

    @Test
    public void instantiateGetList(){

        Entity WaterObject = EntityFactory.createWater();
        GetListDateCommand cmd = CommandsFactory.instatiateGetListDateCmd(WaterObject);

        assertNotNull(cmd);
    }

    @Test
    public void instantiateGetWater(){

        Entity WaterObject = EntityFactory.createWater();
        GetWaterCommand cmd = CommandsFactory.instatiateGetWaterCmd(WaterObject);

        assertNotNull(cmd);
    }
}