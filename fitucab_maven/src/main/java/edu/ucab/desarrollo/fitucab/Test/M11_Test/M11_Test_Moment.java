package edu.ucab.desarrollo.fitucab.Test.M11_Test;

import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Moment;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by charbel on 03/07/2017.
 */
public class M11_Test_Moment {





    @Test
    public void Momentv ()
    {
        Moment a =(Moment) EntityFactory.getMoments();
        assertNotNull(a);

    }

    @Test
    public void Momentinstr()
    {
        Moment a = (Moment) EntityFactory.getMoments();
        a.set_id(1);
        assertEquals(a.get_id(),1);

    }

    @Test
    public void set_des()
    {
        Moment a = (Moment) EntityFactory.getMoments();
        a.set_description("hola");
        assertEquals(a.get_description(),"hola");

    }
}
