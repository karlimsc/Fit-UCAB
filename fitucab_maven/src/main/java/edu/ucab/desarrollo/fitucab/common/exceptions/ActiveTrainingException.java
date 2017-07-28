package edu.ucab.desarrollo.fitucab.common.exceptions;

/**
 * Created by root on 28/07/17.
 */
public class ActiveTrainingException extends Exception
{
    public final int ERROR_CODE = 557;
    public final String ERROR_MSG = "Ha ocurrido un error activando un entrenamiento";


    /**
     * excepcion personalizada para el agregar
     * @param e
     */
    public ActiveTrainingException( Exception e )
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
