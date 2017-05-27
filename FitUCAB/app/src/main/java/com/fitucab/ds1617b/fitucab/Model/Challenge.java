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
    @SerializedName("_tipoChallenge")
    private String _tipo_challenge;
    @SerializedName("_descripcion")
    private String _descripcion;
    @SerializedName("_score")
    private int _score;
    @SerializedName("_level")
    private int _level;
    @SerializedName("_km")
    private int _km;

    public Challenge(){}

    public Challenge(int _idChallenge, String _name, String _tipo_challenge, String _descripcion,
                     int _score, int _level, int _km) {

        this._idChallenge = _idChallenge;
        this._name = _name;
        this._tipo_challenge = _tipo_challenge;
        this._descripcion = _descripcion;
        this._score = _score;
        this._level = _level;
        this._km = _km;

    }

    public Challenge(String _name, String _tipo_challenge, String _descripcion) {
        this._name = _name;
        this._tipo_challenge = _tipo_challenge;
        this._descripcion = _descripcion;
    }



    public int get_km() {
        return _km;
    }

    public void set_km(int _km) {
        this._km = _km;
    }

    public int get_score() {
        return _score;
    }

    public void set_score(int _score) {
        this._score = _score;
    }

    public int get_level() {
        return _level;
    }

    public void set_level(int _level) {
        this._level = _level;
    }

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

    public String get_tipo_challenge() {
        return _tipo_challenge;
    }

    public void set_tipo_challenge(String _tipo_challenge) {
        this._tipo_challenge = _tipo_challenge;
    }

    public String get_descripcion() {
        return _descripcion;
    }

    public void set_descripcion(String _descripcion) {
        this._descripcion = _descripcion;
    }

}
