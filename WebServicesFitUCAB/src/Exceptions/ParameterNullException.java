package Exceptions;


public class ParameterNullException extends NullPointerException {

    private String _paramName;



    public ParameterNullException(String paramName) {
        this._paramName = paramName;
    }

    public ParameterNullException(String s, String paramName) {
        super(s);
        this._paramName = paramName;
    }

    @Override
    public String getMessage(){
        return "Error: el siguientes campo es null, " + _paramName;
    }

}
