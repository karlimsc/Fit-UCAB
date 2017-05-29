package com.fitucab.ds1617b.fitucab.Model;

import java.io.Serializable;

/**
 * Created by karo on 26/05/17.
 */

@SuppressWarnings("serial")
public class Sport implements Serializable {

        private int    _id;
        private String _name;
        private float  _met;

    public Sport(int _id, String _name, float _met) {
        this._id = _id;
        this._name = _name;
        this._met = _met;
    }

    public Sport() {
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public float get_met() {
        return _met;
    }

    public void set_met(float _met) {
        this._met = _met;
    }
}

