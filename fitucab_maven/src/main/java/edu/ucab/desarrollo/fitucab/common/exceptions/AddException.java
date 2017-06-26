package edu.ucab.desarrollo.fitucab.common.exceptions;

import java.sql.SQLException;

/**
 * Created by root on 25/06/17.
 */
public class AddException extends Exception
{
    /**
     * excepcion personalizada para el agregar
     * @param e
     */
    public AddException(SQLException e)
    {
        super( e );
    }
}
