package com.fitucab.ds1617b.fitucab.Model;

/**
 * Created by JoseA2R on 21/5/17.
 */

public class InfoGlass {

    private int _id;
    private String _hour;
    private int _ml;

    public InfoGlass (int id, String hour, int ml){

        _id = id;
        _hour = hour;
        _ml = ml;

    }

    public int get_id(){
        return _id;
    }

    public void set_id(int _id){

        this._id = _id;
    }

    public String get_hour(){

        return _hour;
    }

    public void set_hour(String _hour){

        this._hour = _hour;
    }

    public int get_ml(){

        return _ml;
    }

    public void set_ml(int _ml){
        this._ml = _ml;
    }

}
