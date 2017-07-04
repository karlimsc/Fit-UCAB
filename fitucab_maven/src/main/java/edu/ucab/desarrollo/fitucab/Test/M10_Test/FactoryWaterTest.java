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
public class FactoryWaterTest {


    @Test
    public void instantiateWaterIntIntString(){

        Entity WaterObject = EntityFactory.createWater(1,1,"Hola");

        assertNotNull(WaterObject);
    }

    @Test
    public void instantiateWaterIntString(){

        Entity WaterObject = EntityFactory.createWater(1,"Hola");

        assertNotNull(WaterObject);
    }

    @Test
    public void instantiateWaterInt(){

        Entity WaterObject = EntityFactory.createWater(1);

        assertNotNull(WaterObject);
    }

    @Test
    public void instantiateWaterVacio(){

        Entity WaterObject = EntityFactory.createWater();

        assertNotNull(WaterObject);
    }

    @Test
    public void instantiateWaterIntInt(){

        Entity WaterObject = EntityFactory.createWater(1,1);

        assertNotNull(WaterObject);
    }

    @Test
    public void instantiateWaterStringInt(){

        Entity WaterObject = EntityFactory.createWater("Hola",1);

        assertNotNull(WaterObject);
    }
}