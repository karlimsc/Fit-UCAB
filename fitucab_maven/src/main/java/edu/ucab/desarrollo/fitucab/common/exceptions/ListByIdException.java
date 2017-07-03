package edu.ucab.desarrollo.fitucab.common.exceptions;

import java.sql.SQLException;

/**
 * Created by root on 25/06/17.
 */
public class ListByIdException extends Exception
{

    public final int ERROR_CODE = 553;
    public final String ERROR_MSG = "Ha ocurrido un error consultado una entidad por id";


    /**
     * excepcion personalizada para listar por id
     * @param e
     */
    public ListByIdException(Exception e)
    {
        super( e );
    }


    @Override
    public String toString()
    {
        StringBuilder str = null;


        str = new StringBuilder( ERROR_CODE + "\n" );
        str.append( ERROR_MSG + "\n" );
        str.append( super.toString() );

        return str.toString();
    }
}
