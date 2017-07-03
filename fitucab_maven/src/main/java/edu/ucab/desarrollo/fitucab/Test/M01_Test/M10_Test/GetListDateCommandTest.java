package edu.ucab.desarrollo.fitucab.Test.M01_Test.M10_Test;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Sql;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M10.GetListDateCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Raul A on 7/2/2017.
 */
public class GetListDateCommandTest {

    @Before
    public void setUp() throws Exception {
        Sql _sql = new Sql();
        String insertPerson = "insert into person (personid, personusername, personpassword, personemail, personsex," +
                " personphone, personbirthdate) values (1, 'Sholom Meedendorpe', 'AOA', 'smeedendorpe0@goo.ne.jp'," +
                " 'f', '244-(874)954-1391', '1997-7-7');";
        _sql.sql(insertPerson);
        Sql _sql2 = new Sql();
        String insertWaterList1 = "INSERT INTO public.glass_historic(glasshistoricid, glasstime, glasstype, " +
                "fk_person) VALUES(201,'02/10/3000', 250, 1),(202,'02/10/3000', 300, 1),(203,'02/10/3000', 350, 1);";
        _sql2.sql(insertWaterList1);
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
        Gson gson2 = new Gson();
        int fkp = 1;
        String dia = "02/10/3000";
        Entity WaterObject = EntityFactory.createWater(fkp,dia);
        ArrayList<Water> waterListCompare = new ArrayList<Water>();
        waterListCompare.add(new Water("10/02/3000",250));
        waterListCompare.add(new Water("10/02/3000",300));
        waterListCompare.add(new Water("10/02/3000",350));
        ArrayList<Water> water;
        GetListDateCommand cmd = CommandsFactory.instatiateGetListDateCmd(WaterObject);
        cmd.execute();
        waterListCompare = gson.fromJson(gson.toJson(waterListCompare),ArrayList.class);
        water = gson.fromJson(cmd.returned,ArrayList.class);
        Arrays.deepEquals(water.toArray(), waterListCompare.toArray());
    }


}