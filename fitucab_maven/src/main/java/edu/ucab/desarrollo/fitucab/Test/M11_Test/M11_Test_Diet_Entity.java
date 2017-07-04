package edu.ucab.desarrollo.fitucab.Test.M11_Test;

import edu.ucab.desarrollo.fitucab.common.entities.Diet;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by JoseA2R on 3/7/17.
 */
public class M11_Test_Diet_Entity {

   /* @Test
    public void get_date() throws ParseException {
        String data = "10/11/1999";
        Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(data).;
        Diet pu = new Diet();
        pu.set_date(sdf);
        assertEquals(pu.get_date(),data);
    };

    @Test
    public void set_date() throws ParseException {
        String data = "20/11/1999";
        Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        Diet pu = new Diet();
        pu.set_date(sdf);
        assertEquals(pu.get_date(),data);
    }
*/
    @Test
    public void get_id() {
        int prueba = 10;
        Diet pu = new Diet();
        pu.set_id(prueba);
        assertEquals(pu.get_id(),prueba);

    }

    @Test
    public void set_id() {

        int prueba = 10;
        Diet pu = new Diet();
        pu.set_id(prueba);
        assertEquals(pu.get_id(),prueba);

    }

    @Test
    public void get_calorie() {
        int prucalorie = 100;
        Diet pu = new Diet();
        pu.set_calorie(prucalorie);
        assertEquals(pu.get_calorie(),prucalorie);
    }

    @Test
    public void set_calorie() {

        int prucalorie = 100;
        Diet pu = new Diet();
        pu.set_calorie(prucalorie);
        assertEquals(pu.get_calorie(),prucalorie);

    }



    @Test
    public void get_food() {

        String food = "Pasta";
        Diet pu = new Diet();
        pu.set_food(food);
        assertEquals(pu.get_food(),food);

    }

    @Test
    public void set_food() {

        String food = "Pasta";
        Diet pu = new Diet();
        pu.set_food(food);
        assertEquals(pu.get_food(),food);

    }

    @Test
    public void get_moment() {
        String moment = "Cena";
        Diet pu = new Diet();
        pu.set_moment(moment);
        assertEquals(pu.get_moment(),moment);
    }

    @Test
    public void set_moment( ) {
        String moment = "cena";
        Diet pu = new Diet();
        pu.set_moment(moment);
        assertEquals(pu.get_moment(),moment);
    }

    @Test
    public void get_username() {
        String moment = "cena";
        Diet pu = new Diet();
        pu.set_moment(moment);
        assertEquals(pu.get_moment(),moment);
    }

    @Test
    public void set_username(String username) {

        String moment = "cena";
        Diet pu = new Diet();
        pu.set_moment(moment);
        assertEquals(pu.get_moment(),moment);

    }

}
