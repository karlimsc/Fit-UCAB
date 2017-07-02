package edu.ucab.desarrollo.fitucab.common.exceptions.M02;


/**
 * Excepcion personalizada del Modulo 2 para el comando Get User.
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 2.0
 */
public class GetUserException extends Exception{

    private int _code = 551;
    private String _class;
    private String _specificException;

    /**
     * Metodo Constructor de la clase GetUserException
     * @param _error
     * @param _class
     * @param _specificException
     */
    public GetUserException(Exception _error, String _class, String _specificException) {
        super(_error);
        _class = _class;
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