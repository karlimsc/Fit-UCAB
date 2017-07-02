package edu.ucab.desarrollo.fitucab.Test.M01_Test.M10_Test;

import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M10.IDaoWater;
import edu.ucab.desarrollo.fitucab.webService.Sql;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Raul A on 7/1/2017.
 */
public class DaoWaterTest {

    SimpleDateFormat _sdf2 = new SimpleDateFormat("MM/dd/yyyy");
    Date fecha = new Date();

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
        String deleteWater = "TRUNCATE glass_historic RESTART IDENTITY;";
        _sql.sql(deleteWater);
    }

    @Test
    public void create() throws Exception {
        Water _water = EntityFactory.createWater();
        _water.set_time(_sdf2.format(fecha));
        Water water = EntityFactory.createWater();
        IDaoWater daoWater = DaoFactory.instanceDaoWater(_water);
        _water.set_glasstype(200);
        _water.set_fkPerson(1);
        for (int i=0;i < 2;i++){
            water = (Water) daoWater.create(_water);
        }
        assertEquals ((long) water.get_cantidad(),2);
    }

    @Test
    public void getList() throws Exception {
        Water _water = EntityFactory.createWater();
        _water.set_fkPerson(1);
        _water.set_time("02/10/3000");
        ArrayList<Water> waterListCompare = new ArrayList<Water>();
        waterListCompare.add(new Water("02/10/3000",250));
        waterListCompare.add(new Water("02/10/3000",300));
        waterListCompare.add(new Water("02/10/3000",350));
        IDaoWater daoWater = DaoFactory.instanceDaoWater(_water);
        ArrayList<Water> waterList = daoWater.getList(_water);
        Arrays.deepEquals(waterList.toArray(), waterListCompare.toArray());
    }

    @Test
    public void getWater() throws Exception {
        Water _water = EntityFactory.createWater();
        _water.set_fkPerson(1);
        _water.set_time("02/10/3000");
        Water water = EntityFactory.createWater();
        Water waterCount = EntityFactory.createWater(900,3);
        IDaoWater daoWater = DaoFactory.instanceDaoWater(_water);
        water = (Water) daoWater.getWater(_water);
        assertTrue(waterCount.get_cantidad() == water.get_cantidad() && waterCount.get_time() == water.get_time());
    }

    @Test
    public void getFechaInt() throws Exception {

    }

    @Test
    public void deleteLast() throws Exception {

    }

    @Test
    public void deleteWaterTm() throws Exception {
    }

    @Test
    public void queryExecute() throws Exception {
    }

    @Test
    public void addWaterResult() throws Exception {
    }

    @Test
    public void getWaterList() throws Exception {
    }

    @Test
    public void getWaterItem() throws Exception {
    }

    @Test
    public void getFechaIntItem() throws Exception {
    }

    @Test
    public void deletLastItem() throws Exception {
    }

    @Test
    public void deletWaterTmItem() throws Exception {
    }

    @Test
    public void read() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

}