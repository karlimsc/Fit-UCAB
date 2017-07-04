package edu.ucab.desarrollo.fitucab.common.exceptions;

/**
 * Created by Barbara Fernadez on 7/2/2017.
 */
public class DeleteException extends Exception
{

    public final int ERROR_CODE = 555;
    public final String ERROR_MSG = "Ha ocurrido un error eliminando";


    /**
     * excepcion personalizada para el agregar
     * @param e
     */
    public DeleteException(Exception e)
    {
        super( e );
    }
}
