package edu.ucab.desarrollo.fitucab.validation;

import edu.ucab.desarrollo.fitucab.common.exceptions.ParametersNullException;

import java.util.Map;

/**
 * Created by jaorr on 30/06/17.
 */
public class ValidationWs {
        public static final int INT_INVALID = -1;
        public static void validarParametrosNotNull(Map<String, Object> parametros) throws ParametersNullException {
            Object a = -1;
            for (Map.Entry<String, Object> entry : parametros.entrySet()){
                if ((entry.getValue() == null) || (entry.getValue().equals(INT_INVALID))){
                    throw new ParametersNullException(entry.getKey());
                }
            }

        }
}
