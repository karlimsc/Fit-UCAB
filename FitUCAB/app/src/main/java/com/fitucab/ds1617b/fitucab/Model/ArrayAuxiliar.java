package com.fitucab.ds1617b.fitucab.Model;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Raul A on 5/13/2017.
 */

public class ArrayAuxiliar {

    private String _nombre;
    private int _puntaje;
    private int _type;

    public ArrayAuxiliar(String nombre, int puntaje, int type){
        _nombre = nombre;
        _puntaje = puntaje;
        _type = type;
    }

    public String get_nombre() {
        return _nombre;
    }

    public void set_nombre(String _nombre) {
        this._nombre = _nombre;
    }

    public int get_puntaje() {
        return _puntaje;
    }

    public void set_puntaje(int _puntaje) {
        this._puntaje = _puntaje;
    }

    public int get_type() {
        return _type;
    }

    public void set_type(int _type) {
        this._type = _type;
    }
}
