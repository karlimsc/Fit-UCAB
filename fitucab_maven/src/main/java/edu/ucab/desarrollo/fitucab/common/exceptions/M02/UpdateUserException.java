package edu.ucab.desarrollo.fitucab.common.exceptions.M02;


/**
 * Excepcion personalizada del Modulo 2 para el comando update User.
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 2.0
 */
public class UpdateUserException extends Exception{

    private int _code = 551;
    private String _class;
    private String _specificException;

    /**
     * Metodo constructor de la clase UpdateUserException
     * @param error
     * @param _class
     * @param _method
     * @param _specificException
     */
    public UpdateUserException(Exception error, String _class, String _method, String _specificException) {
        super(error);
        _class = _class;
        _method =_method;
        _specificException=_specificException;
    }


    /**
     * Sobreescritura del Metodo toString heredado de la clase Exception
     * @return super.toString();
     */
    @Override
    public String toString() {
        StringBuilder _strB = new StringBuilder(_code);
        _strB.append(_class);
        _strB.append(_specificException);
        _strB.append(super.toString());
        return super.toString();
    }

}