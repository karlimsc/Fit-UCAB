package com.fitucab.ds1617b.fitucab.Helper;

import android.widget.TextView;
/**
 * Created by Alejandro Fernandez on 23/5/2017.
 */

public class ArrayAuxiliarTraining {

    private String _nombreEntrenamiento;
    private int _id;


    public ArrayAuxiliarTraining( String nombre ){

        _nombreEntrenamiento = nombre;

    }

    public String get_nombreEntrenamiento() {
        return _nombreEntrenamiento;
    }

    public void set_nombreEntrenamiento(String _nombreEntrenamiento) {
        this._nombreEntrenamiento = _nombreEntrenamiento;
    }


}