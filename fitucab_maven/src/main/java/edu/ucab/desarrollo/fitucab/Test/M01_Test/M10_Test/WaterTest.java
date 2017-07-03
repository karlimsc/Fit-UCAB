package edu.ucab.desarrollo.fitucab.Test.M01_Test.M10_Test;

import edu.ucab.desarrollo.fitucab.common.entities.Water;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 * Created by RAULI on 7/3/2017.
 */
public class WaterTest {

  Water water1 = new Water(4,2);
  Water water2 = new Water(1,5,"02/10/3000");
  Water water3 = new Water("error");

  @Test
    public void get_time() throws Exception {
      assertEquals(water2.get_time(),"02/10/3000");
  }
  @Test
  public void set_time() throws Exception {
      water2.set_time("02/10/1995");
      assertEquals(water2.get_time(),"02/10/1995");
  }
  @Test
  public void get_glasstype() throws Exception {
      assertEquals((int)water2.get_glasstype(),1);
  }
 @Test
 public void set_glasstype() throws Exception {
        water2.set_glasstype(2);
        assertEquals((int)water2.get_glasstype(),2);
 }
 @Test
 public void get_fkPerson() throws Exception {
     assertEquals((int)water2.get_fkPerson(),5);
 }
 @Test
 public void set_fkPerson() throws Exception {
     water2.set_fkPerson(10);
     assertEquals((int)water2.get_fkPerson(),10);
 }
 @Test
 public void get_cantidad() throws Exception {
        assertEquals((int)water1.get_cantidad(),2);
 }
 @Test
 public void set_cantidad() throws Exception {
        water1.set_cantidad(10);
        assertEquals((int)water1.get_cantidad(),10);
 }
 @Test
 public void get_suma() throws Exception {
        assertEquals((int)water1.get_suma(),4);
 }
 @Test
 public void set_suma() throws Exception {
        water1.set_suma(10);
        assertEquals((int)water1.get_suma(),10);
 }
 @Test
 public void get_error() throws Exception {
        assertEquals(water2.get_error(),"error");
 }
 @Test
 public void set_error() throws Exception {
        water2.set_error("error nuevo");
        assertEquals(water2.get_error(),"error nuevo");
 }
}
