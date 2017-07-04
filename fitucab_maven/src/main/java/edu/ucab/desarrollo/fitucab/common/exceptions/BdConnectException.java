package edu.ucab.desarrollo.fitucab.common.exceptions;


public class BdConnectException extends Exception
{
    public final int ERROR_CODE = 551;
    public final String ERROR_MSG = "Ha ocurrido un error con la conexión a base de datos";


    public BdConnectException( Exception e )
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
