package edu.ucab.desarrollo.fitucab.common.exceptions;


public class ParameterNotValidException extends NullPointerException {

    private String _paramName;



    public ParameterNotValidException(String paramName) {
        this._paramName = paramName;
    }

    public ParameterNotValidException(String s, String paramName) {
        super(s);
        this._paramName = paramName;
    }

    @Override
    public String getMessage(){
        return "Error: el campo "+ _paramName +" es invalido.";
    }

}
