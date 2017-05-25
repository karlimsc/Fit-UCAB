package com.fitucab.ds1617b.fitucab.Model;

import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.R.attr.id;
import static com.fitucab.ds1617b.fitucab.R.id.nombre;

/**
 * Created by Raul A on 5/13/2017.
 */

public class ArrayAuxiliar {

    private int _id;
    private String _nombre;
    private int _puntaje;
    private int _type;
    private String _email;
    private long _phone;


    public ArrayAuxiliar(String nombre, String email, long phone){
        _nombre = nombre;
        _email = email;
        _phone = phone;
    }

    public ArrayAuxiliar(int id, String nombre, int puntaje, int type){
        _id = id;
        _nombre = nombre;
        _puntaje = puntaje;
        _type = type;
    }

    public String get_nombre()
    {
        return _nombre;
    }

    public int get_id()
    {
        return _id;
    }

    public void set_id(int id)
    {
        this._id = id;
    }

    public void set_nombre(String _nombre)
    {
        this._nombre = _nombre;
    }

    public int get_puntaje()
    {
        return _puntaje;
    }

    public void set_puntaje(int _puntaje)
    {
        this._puntaje = _puntaje;
    }

    public int get_type()
    {
        return _type;
    }

    public void set_type(int _type)
    {
        this._type = _type;
    }
}
