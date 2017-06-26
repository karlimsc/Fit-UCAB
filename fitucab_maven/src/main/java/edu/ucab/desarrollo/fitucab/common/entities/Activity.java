package edu.ucab.desarrollo.fitucab.common.entities;

/**
 * Created by root on 24/06/17.
 */
public class Activity extends Entity {
    private String _name;

    public Activity(int _id, String _name) {
        super(_id);
        this._name = _name;
    }

    public Activity(int _id, String _name, int _expectedTime, int _targetTime) {
        super(_id);
        this._name = _name;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

}
