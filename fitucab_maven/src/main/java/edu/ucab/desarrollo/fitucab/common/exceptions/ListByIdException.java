package edu.ucab.desarrollo.fitucab.common.exceptions;

import java.sql.SQLException;

/**
 * Created by root on 25/06/17.
 */
public class ListByIdException extends Exception
{
    /**
     * excepcion personalizada para listar por id
     * @param e
     */
    public ListByIdException(SQLException e)
    {
        super( e );
    }
}
