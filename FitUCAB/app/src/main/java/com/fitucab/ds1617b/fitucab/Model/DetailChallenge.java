package com.fitucab.ds1617b.fitucab.Model;

import com.google.gson.annotations.SerializedName;

import java.util.Calendar;

/**
 * Created by jorgelinux on 26/05/17.
 */

public class DetailChallenge {

    @SerializedName("_idDetail")
    private int _idDetail;
    @SerializedName("_initDay")
    private Calendar _initDay;
    @SerializedName("endDay")
    private Calendar _endDay;
    @SerializedName("_posicion")
    private int _posicion;
    @SerializedName("_active")
    private boolean _active;
    @SerializedName("_idChallenge")
    private int _idChallenge;
    @SerializedName("_idUser")
    private int _idUser;

    public int get_idDetail() {
        return _idDetail;
    }

    public void set_idDetail(int _idDetail) {
        this._idDetail = _idDetail;
    }

    public Calendar get_initDay() {
        return _initDay;
    }

    public void set_initDay(Calendar _initDay) {
        this._initDay = _initDay;
    }

    public Calendar get_endDay() {
        return _endDay;
    }

    public void set_endDay(Calendar _endDay) {
        this._endDay = _endDay;
    }

    public int get_posicion() {
        return _posicion;
    }

    public void set_posicion(int _posicion) {
        this._posicion = _posicion;
    }

    public boolean is_active() {
        return _active;
    }

    public void set_active(boolean _active) {
        this._active = _active;
    }

    public int get_idChallenge() {
        return _idChallenge;
    }

    public void set_idChallenge(int _idChallenge) {
        this._idChallenge = _idChallenge;
    }

    public int get_idUser() {
        return _idUser;
    }

    public void set_idUser(int _idUser) {
        this._idUser = _idUser;
    }

}
