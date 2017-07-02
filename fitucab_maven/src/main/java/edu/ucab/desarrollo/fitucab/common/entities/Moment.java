package edu.ucab.desarrollo.fitucab.common.entities;

import java.util.ArrayList;

/**
 * Created by jaorr on 22/05/17.
 */
public class Moment extends Entity{
    private int _iD;
    private String _description;
    public ArrayList<Moment> jsonArray;

    public Moment() {}

    public Moment(int id, String description) {
        this._iD = id;
        this._description = description;
    }

    public Moment(String description) {
        this._description = description;
    }

    public int get_id() {
        return _iD;
    }

    public void set_id(int id) {
        this._iD = id;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String description) {
        this._description = description;
    }
}
