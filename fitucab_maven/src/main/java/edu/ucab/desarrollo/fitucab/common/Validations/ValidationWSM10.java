package edu.ucab.desarrollo.fitucab.common.Validations;


import edu.ucab.desarrollo.fitucab.common.exceptions.ParameterNotValidException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ParameterNullException;

import java.util.Map;

/**
 * Created by jaorr on 26/05/17.
 */
public class ValidationWSM10 {

    public static void validarParametros(Map<String, Object> parametros) throws ParameterNotValidException {

        for (Map.Entry<String, Object> entry : parametros.entrySet()){

            if (entry.getValue() == null
            || (entry.getKey().equals("fkp") && Integer.parseInt(entry.getValue().toString()) <= 0)
            || (entry.getKey().equals("dia") && entry.getValue().equals("") )
            || (entry.getKey().equals("glassType") && Integer.parseInt(entry.getValue().toString()) <= 0)){

                throw new ParameterNotValidException(entry.getKey());

            }
        }

    }
}
