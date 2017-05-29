package M11_Test;

/**
 * Created by jaorr on 29/05/17.
 */
import Exceptions.ParameterNullException;
import Validations.ValidationWS;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
public class ParameterNullExceptionTest {

    @Test
    public void constructorSinSuperTest(){
        ParameterNullException test = new ParameterNullException("testing");
        assertEquals("Error: el siguientes campo es null, testing", test.getMessage());

    }

    @Test
    public void constructorConSuperTest(){
        ParameterNullException test = new ParameterNullException("testingSuper","testing");
        assertEquals("Error: el siguientes campo es null, testing", test.getMessage());

    }


}
