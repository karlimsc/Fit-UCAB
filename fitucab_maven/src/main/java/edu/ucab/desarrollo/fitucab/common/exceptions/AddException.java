package edu.ucab.desarrollo.fitucab.common.exceptions;

import java.sql.SQLException;

/**
 * Created by root on 25/06/17.
 */
public class AddException extends Exception
{

    public final int ERROR_CODE = 550;
    public final String ERROR_MSG = "Ha ocurrido un error agregando";


    /**
     * excepcion personalizada para el agregar
     * @param e
     */
    public AddException(Exception e)
    {
        super( e );
    }
}
