package edu.ucab.desarrollo.fitucab.common.exceptions.M01;

/**
 * Created by estefania on 02/07/2017.
 */
public class CreateUserException extends Exception {

    private int _code = 554;
    private String _class;
    private String _specificException;

    /**
     * Metodo contructor para lanzar excepcion al crear usuario
     * @param _error
     * @param _class
     * @param _specificException
     */
    public CreateUserException(Exception _error, String _class, String _specificException) {
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
