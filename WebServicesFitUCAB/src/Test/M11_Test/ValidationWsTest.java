package M11_Test;
import Exceptions.ParameterNullException;
import Validations.ValidationWS;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;


public class ValidationWsTest {

    @Test(expected = ParameterNullException.class)
    public void ParameterNullConUnParametro(){
        ValidationWS.validarParametrosNotNull(new HashMap<String, Object>() {
            {
                put("test", null);

            }
        });
    }

    @Test(expected = ParameterNullException.class)
    public void ParameterNullConDosParametros(){
        ValidationWS.validarParametrosNotNull(new HashMap<String, Object>() {
            {
                put("test", "hola");
                put("test2", null);

            }
        });
    }

    @Test(expected = ParameterNullException.class)
    public void ParameterNullConTresParametros() {
        ValidationWS.validarParametrosNotNull(new HashMap<String, Object>() {
            {
                put("test", "testing1");
                put("test2", null);
                put("test3", "testing2");

            }
        });

    }


}