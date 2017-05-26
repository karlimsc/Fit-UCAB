package com.fitucab.ds1617b.fitucab.Model;

/**
 * Created by jorgelinux on 26/05/17.
 */

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;
public class Challenge {
    @SerializedName ("_idChallenge")
    private int _idChallenge;
    @SerializedName("_name")
    private String _name;
    @SerializedName("_initDate")
    private Calendar _initDate;
    @SerializedName("_endDate")
    private Calendar _end_date;
    @SerializedName("_kilometers")
    private int _kilometers;
    @SerializedName("_tipoChallenge")
    private String _tipo_challenge;

    public int get_idChallenge() {
        return _idChallenge;
    }

    public void set_idChallenge(int _idChallenge) {
        this._idChallenge = _idChallenge;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public Calendar get_initDate() {
        return _initDate;
    }

    public void set_initDate(Calendar _initDate) {
        this._initDate = _initDate;
    }

    public Calendar get_end_date() {
        return _end_date;
    }

    public void set_end_date(Calendar _end_date) {
        this._end_date = _end_date;
    }

    public int get_kilometers() {
        return _kilometers;
    }

    public void set_kilometers(int _kilometers) {
        this._kilometers = _kilometers;
    }

    public String get_tipo_challenge() {
        return _tipo_challenge;
    }

    public void set_tipo_challenge(String _tipo_challenge) {
        this._tipo_challenge = _tipo_challenge;
    }



}
