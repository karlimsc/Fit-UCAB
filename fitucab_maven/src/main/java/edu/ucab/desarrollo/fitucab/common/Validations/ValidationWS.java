package edu.ucab.desarrollo.fitucab.common.Validations;


import edu.ucab.desarrollo.fitucab.common.exceptions.ParametersNullException;

import java.util.Map;

/**
 * Created by jaorr on 26/05/17.
 */
public class ValidationWS {

    public static void validarParametrosNotNull(Map<String, Object> parametros) throws ParametersNullException {
        for (Map.Entry<String, Object> entry : parametros.entrySet()){
            if (entry.getValue() == null){
                throw new ParametersNullException(entry.getKey());
            }
        }
    }
}
