package edu.ucab.desarrollo.fitucab.common.exceptions;

/**
 * Created by Barbara Fernadez on 7/2/2017.
 */
public class SharedException extends Exception
{

    public final int ERROR_CODE = 554;
    public final String ERROR_MSG = "Ha ocurrido un error compartiendo";


    /**
     * excepcion personalizada para el agregar
     * @param e
     */
    public SharedException(Exception e)
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