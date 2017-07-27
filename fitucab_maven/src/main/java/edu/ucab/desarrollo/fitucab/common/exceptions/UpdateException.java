package edu.ucab.desarrollo.fitucab.common.exceptions;

/**
 * Created by Barbara Fernadez on 7/2/2017.
 */
public class UpdateException extends Exception
{

    public final int ERROR_CODE = 559;
    public final String ERROR_MSG = "Ha ocurrido un error modificando";


    /**
     * excepcion personalizada para el agregar
     * @param e
     */
    public UpdateException(Exception e)
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
