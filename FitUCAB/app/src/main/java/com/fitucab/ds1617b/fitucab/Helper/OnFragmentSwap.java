package com.fitucab.ds1617b.fitucab.Helper;

import android.os.Bundle;

import com.fitucab.ds1617b.fitucab.Model.Planification;


/**
 * Interface que debe ser implementada por TODAS LAS ACTIVITIES que manejen fragmentos
 */
public interface OnFragmentSwap {

    /**
     * Metodo encargado de realizar los cambios entre fragment dentro de un activity
     * @param fragmentName Recibe el nobmre del fragment (nombre de la clase)
     * @param bundle Recibe la data encapsulada que se le pasara al fragment, (puede ser null)
     */
    void onSwap(String fragmentName, Bundle bundle);

    /**
     * Metodo encargado de realizar los cambios entre fragment dentro de un activity
     * @param fragmentName Recibe el nobmre del fragment (nombre de la clase)
     * @param bundle Recibe la data encapsulada que se le pasara al fragment, (puede ser null)
     *@param planification Recibe la data de la planificacion para identificar su tipo
     */
    void onSwapData(String fragmentName, Bundle bundle, Planification planification);


    /**
     * Metodo encargado de realizar los cambios entre activities
     * @param activityName
     * @param bundle
     */
    void onSwapActivity(String activityName, Bundle bundle);
}
