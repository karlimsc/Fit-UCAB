package edu.ucab.desarrollo.fitucab.validation;

import edu.ucab.desarrollo.fitucab.common.exceptions.ParametersNullException;

import java.util.Map;

/**
 * Clase que valida que los parametros recibidos que sean obligatorios no sean nulos
 */
public class ValidationWs {
        public static final int INT_INVALID = -1;

    /**
     * Metodo que realiza la validacion de los parametros.
     * Se considera null un parametro si: el parametro es String y es null,
     * o si es int y su valor es -1.
     * @param parametros
     * @throws ParametersNullException
     * @see ParametersNullException
     */
        public static void validarParametrosNotNull(Map<String, Object> parametros) throws ParametersNullException {
            Object a = -1;
            for (Map.Entry<String, Object> entry : parametros.entrySet()){
                if ((entry.getValue() == null) || (entry.getValue().equals(INT_INVALID))){
                    throw new ParametersNullException(entry.getKey());
                }
            }

        }
}
