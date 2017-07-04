package edu.ucab.desarrollo.fitucab.Test.M11_Test;

import edu.ucab.desarrollo.fitucab.common.entities.Food;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by JoseA2R on 3/7/17.
 */
public class M11_Test_Food_Entity {


   /* public void setjson (String json) {_json = json;}

    public String getJson () {return  _json;}
*/
   @Test
    public void get_idname(){
        String prueba = "josea2r";
        Food pu = new Food();
        pu.set_idname(prueba);
        assertEquals(pu.get_id(),prueba);
    }

    @Test
    public void get_id() {

         int prueba = 10;
        Food pu = new Food();
        pu.set_id(prueba);
        assertEquals(pu.get_id(),prueba);
    }

    @Test
    public void set_id() {

        int prueba = 10;
        Food pu = new Food();
        pu.set_id(prueba);
        assertEquals(pu.get_id(),prueba);

    }

    @Test
    public void get_foodName() {

        String food = "Pasta";
        Food pu = new Food();
        pu.set_idname(food);
        assertEquals(pu.get_id(),food);

    }

    @Test
    public void set_foodName(String foodName) {

        String food = "Pasta";
        Food pu = new Food();
        pu.set_idname(food);
        assertEquals(pu.get_id(),food);
    }

    @Test
    public void get_foodWeight() {

        String peso = "350";
        Food pu = new Food();
        pu.set_idname(peso);
        assertEquals(pu.get_id(),peso);

    }


    @Test
    public void set_foodWeight() {

        String peso = "350";
        Food pu = new Food();
        pu.set_idname(peso);
        assertEquals(pu.get_id(),peso);

    }

    @Test
    public void get_foodCalorie() {

        String calo = "700";
        Food pu = new Food();
        pu.set_idname(calo);
        assertEquals(pu.get_id(),calo);

    }

    @Test
    public void set_foodCalorie() {

        String calo = "700";
        Food pu = new Food();
        pu.set_idname(calo);
        assertEquals(pu.get_id(),calo);
    }

    @Test
    public void get_foodPersonalized() {
        Boolean pers = true;
        Food pu = new Food();
        pu.set_foodPersonalized(pers);
        assertEquals(pu.get_foodPersonalized(),pers);
    }

    @Test
    public void set_foodPersonalized() {
        Boolean pers = true;
        Food pu = new Food();
        pu.set_foodPersonalized(pers);
        assertEquals(pu.get_foodPersonalized(),pers);
    }

}
