package edu.ucab.desarrollo.fitucab.common.exceptions;

/**
 * Created by Barbara Fernadez on 7/2/2017.
 */
public class ShareException extends Exception
{

    public final int ERROR_CODE = 554;
    public final String ERROR_MSG = "Ha ocurrido un error compartiendo";


    /**
     * excepcion personalizada para el agregar
     * @param e
     */
    public ShareException(Exception e)
    {
        super( e );
    }
}