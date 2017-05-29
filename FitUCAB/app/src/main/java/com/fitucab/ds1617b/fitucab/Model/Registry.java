package com.fitucab.ds1617b.fitucab.Model;

import com.google.gson.annotations.SerializedName;




public class Registry {

    @SerializedName("_weight")
    private float _weight;
    @SerializedName("_height")
    private float _height;

    public Registry(float weight,float height){
        _weight = weight;
        _height = height;

    }

    public float get_weight() {
        return _weight;
    }

    public void set_weight(float _weight) {
        this._weight = _weight;
    }

    public float get_height() {
        return _height;
    }

    public void set_height(float _height) {
        this._height = _height;
    }

}
