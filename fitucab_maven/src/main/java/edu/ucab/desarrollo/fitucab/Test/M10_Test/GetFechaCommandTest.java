package edu.ucab.desarrollo.fitucab.Test.M10_Test;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Sql;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M10.DeletLastCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M10.GetFechaCommand;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by Raul A on 7/2/2017.
 */
public class GetFechaCommandTest {


    SimpleDateFormat _sdf1 = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat _sdf2 = new SimpleDateFormat("yyyy/MM/dd");
    Date fecha = new Date();
    String dia =_sdf2.format(fecha);

    @Before
    public void setUp() throws Exception {
        Sql _sql = new Sql();
        String insertPerson = "insert into person (personid, personusername, personpassword, personemail, personsex," +
                " personphone, personbirthdate) values (1, 'Sholom Meedendorpe', 'AOA', 'smeedendorpe0@goo.ne.jp'," +
                " 'f', '244-(874)954-1391', '1997-7-7');";
        _sql.sql(insertPerson);
        Sql _sql2 = new Sql();
        String insertWaterList1 = "INSERT INTO public.glass_historic(glasshistoricid, glasstime, glasstype, " +
                "fk_person) VALUES(201,'"+dia+"', 250, 1),(202,'"+dia+"', 300, 1);";
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
        int fkp = 1;
        Entity WaterObject = EntityFactory.createWater(fkp);
        GetFechaCommand cmd = CommandsFactory.instatiateGetFechaCmd(WaterObject);
        cmd.execute();
        Water water = gson.fromJson(cmd.returned,Water.class);
        Water waterComparacion = EntityFactory.createWater();
        waterComparacion.set_cantidad(2);
        waterComparacion.set_suma(550);
        dia = _sdf1.format(fecha);
        waterComparacion.set_time(dia);
        assertTrue(water.equals(waterComparacion));
    }

}