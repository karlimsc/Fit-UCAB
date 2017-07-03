package com.fitucab.ds1617b.fitucab.Model;

import java.util.ArrayList;

/**
 * Created by marvian on 27/05/17.
 */

public class Moment {

    private  int _iD;
    private String _description;
    public ArrayList<Moment> jsonArray;



    public Moment() {
        // Required empty public constructor
    }

    public int get_id() {
        return _iD;
    }

    public void set_id(int _id) {
        this._iD = _id;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String _description) {
        this._description = _description;
    }
}
