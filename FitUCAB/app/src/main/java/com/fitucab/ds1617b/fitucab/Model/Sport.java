package com.fitucab.ds1617b.fitucab.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by karo on 26/05/17.
 */

@SuppressWarnings("serial")
public class Sport implements Serializable {

    @SerializedName("_id")
    private int id;
    @SerializedName("_name")
    private String name;
    @SerializedName("_met")
    private float _met;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float get_met() {
        return _met;
    }

    public void set_met(float _met) {
        this._met = _met;
    }
}

