package com.fitucab.ds1617b.fitucab.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by root on 21/05/17.
 */

public class ManagePreferences {
    public static int getIdUser(Context context){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        int storedPreference = preferences.getInt("idUser", 0);
        return storedPreference;


    }
}
