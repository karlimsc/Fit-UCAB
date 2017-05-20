package com.fitucab.ds1617b.fitucab.Model.Helper;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;

import java.util.List;


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
     * Metodo encargado de realizar los cambios entre activities
     * @param activityName
     * @param bundle
     */
    void onSwapActivity(String activityName, Bundle bundle);
}
