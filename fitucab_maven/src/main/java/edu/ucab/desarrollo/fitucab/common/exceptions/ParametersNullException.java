package edu.ucab.desarrollo.fitucab.common.exceptions;

/**
 * Created by jaorr on 30/06/17.
 * Excepcion que se levantara cuando un paramtro obligatorio sea nulo
 */
public class ParametersNullException extends NullPointerException {

    private String _paramName;
    private final int _status = 501;


    /**
     * Constructor que recibe el nombre del parametro nulo
     * @param paramName nombre del parametro obligatorio que es nulo
     */
    public ParametersNullException(String paramName) {
        this._paramName = paramName;
    }

    public ParametersNullException(String s, String paramName) {
        super(s);
        this._paramName = paramName;
    }

    /**
     * Getter que obtiene el estatus del error
     * @return _status
     */
    public int getStatus() {
        return _status;
    }

    @Override
    public String getMessage(){
        return "Error: el siguientes campo es null, " + _paramName;
    }
}
