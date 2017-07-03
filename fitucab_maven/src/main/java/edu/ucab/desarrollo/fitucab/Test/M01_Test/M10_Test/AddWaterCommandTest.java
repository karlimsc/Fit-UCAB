package edu.ucab.desarrollo.fitucab.Test.M01_Test.M10_Test;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Sql;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M10.AddWaterCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Raul A on 7/2/2017.
 */
public class AddWaterCommandTest {
    @Before
    public void setUp() throws Exception {
        Sql _sql = new Sql();
        String insertPerson = "insert into person (personid, personusername, personpassword, personemail, personsex," +
                " personphone, personbirthdate) values (1, 'Sholom Meedendorpe', 'AOA', 'smeedendorpe0@goo.ne.jp'," +
                " 'f', '244-(874)954-1391', '1997-7-7');";
        _sql.sql(insertPerson);
    }

    @After
    public void tearDown() throws Exception {
        Sql _sql = new Sql();
        String deletePerson = "delete from person where personid = 1;";
        _sql.sql(deletePerson);
        Sql _sql2 = new Sql();
        String deleteWater = "TRUNCATE glass_historic RESTART IDENTITY;";
        _sql2.sql(deleteWater);
    }

    @Test
    public void execute() throws Exception {
        Gson gson = new Gson();
        int fkp = 1;
        int glassType = 250;
        String dia = "02/10/3000";
        Entity WaterObject = EntityFactory.createWater(glassType,fkp,dia);
        AddWaterCommand cmd = CommandsFactory.instatiateAddWaterCmd(WaterObject);
        cmd.execute();
        cmd.execute();
        Water water = gson.fromJson(cmd.returned,Water.class);
        assertEquals((long) water.get_cantidad(),2);
    }

}