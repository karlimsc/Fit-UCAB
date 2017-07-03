package edu.ucab.desarrollo.fitucab.common.exceptions;


import java.io.UnsupportedEncodingException;

public class EncodingExeption extends UnsupportedEncodingException {
    private String _paramName;
    private final int _status = 501;

    public EncodingExeption(String _paramName) {
        this._paramName = _paramName;
    }

    public EncodingExeption(String s, String _paramName) {
        super(s);
        this._paramName = _paramName;
    }

    public int get_status() {
        return _status;
    }

    @Override
    public String getMessage() {
        return "El siguiente paramentro no se puso desencodear: " + _paramName;
    }
}
