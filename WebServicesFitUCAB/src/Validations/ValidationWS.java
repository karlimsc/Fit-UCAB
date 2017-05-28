package Validations;

import Exceptions.ParameterNullException;

import java.util.Map;

/**
 * Created by jaorr on 26/05/17.
 */
public class ValidationWS {

    public static void validarParametrosNotNull(Map<String, Object> parametros) throws ParameterNullException{
        for (Map.Entry<String, Object> entry : parametros.entrySet()){
            if (entry.getValue() == null){
                throw new ParameterNullException(entry.getKey());
            }
        }

    }
}
