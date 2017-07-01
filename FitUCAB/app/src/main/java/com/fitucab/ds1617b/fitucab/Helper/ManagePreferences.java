package com.fitucab.ds1617b.fitucab.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by root on 21/05/17.
 */

public class ManagePreferences {

    /**
     * Metodo por el que cual puedes saber cual usuario esta logueado
     * @param context
     * @return el id del usuario
     */
    public static int getIdUser(Context context){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int storedPreference = preferences.getInt("idUser", 0);
        return storedPreference;

    }


    /**
     * Metodo por el que cual puedes saber el username del usuario que esta logueado
     * @param context
     * @return el username del usuario
     */
    public static String getUsername(Context context){

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String storedPreference = preferences.getString("username", null);
        return storedPreference;

    }
}
