package edu.ucab.desarrollo.fitucab.common.exceptions.M02;


/**
 * Excepcion personalizada del Modulo 2 para el comando Update User.
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 1.0
 */
public class UpdateUserException extends Exception{

    private int _code = 551;
    private String _class;
    private String _method;
    private String _specificException;

    public UpdateUserException(Exception error, String _class, String _method, String _specificException) {
        super(error);
        _class = _class;
        _method =_method;
        _specificException=_specificException;
    }


    @Override
    public String toString() {
        StringBuilder _strB = new StringBuilder(_code);
        _strB.append(_class);
        _strB.append(_method);
        _strB.append(_specificException);
        _strB.append(super.toString());
        return super.toString();
    }

}