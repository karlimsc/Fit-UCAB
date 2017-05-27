package com.fitucab.ds1617b.fitucab.Model;

/**
 * Created by marvian on 27/05/17.
 */



public class Diet {

    private int _id;
    private int _calorie;
    private String _food;
    private String _moment;
    private String _username;

    public Diet() {
        // Required empty public constructor
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int get_calorie() {
        return _calorie;
    }

    public void set_calorie(int _calorie) {
        this._calorie = _calorie;
    }

    public String get_food() {
        return _food;
    }

    public void set_food(String _food) {
        this._food = _food;
    }

    public String get_moment() {
        return _moment;
    }

    public void set_moment(String _moment) {
        this._moment = _moment;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }
}
