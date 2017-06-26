package edu.ucab.desarrollo.fitucab.common.exceptions;

import java.sql.SQLException;

/**
 * Created by root on 25/06/17.
 */
public class ListAllException extends Exception
{
    /**
     * Excepcion personalizada para listar todos
     * @param e
     */
    public ListAllException(SQLException e)
    {
        super( e );
    }
}
