package edu.ucab.desarrollo.fitucab.common.entities;


public class Sport extends Entity {

    private int _id;
    private String _name;

    public Sport(int id) {
        this._id = id;
    }

    public Sport(int id, String name) {
        this._id = id;
        this._name = name;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String name) {
        this._name = name;
    }
}
