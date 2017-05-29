package com.fitucab.ds1617b.fitucab.Helper;

import android.content.SharedPreferences;

import com.google.gson.JsonObject;

/**
 * Created by Familia on 29/5/2017.
 */

public class M02Exception extends Exception {

    public SharedPreferences preferences;
    public String username;
    public String email;
    public String phone;
    public int weight;
    public JsonObject response;

    public M02Exception() {
    }

    @Override
    public String toString() {
        super.toString();
        return "Error";
    }
}
