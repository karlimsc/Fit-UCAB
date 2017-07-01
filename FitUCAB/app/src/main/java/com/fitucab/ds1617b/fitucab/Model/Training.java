package com.fitucab.ds1617b.fitucab.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by root on 01/07/17.
 */

public class Training {

    @SerializedName("_trainingid")
    private int _trainingId;
    @SerializedName("_trainingname")
    private String _trainingName;
    @SerializedName("_activities")
    private List<Activit> _activities;


    public Training() {
    }

    public Training(int _trainingId, String _trainingName, List<Activit> _activities) {
        this._trainingId = _trainingId;
        this._trainingName = _trainingName;
        this._activities = _activities;
    }

    public Training(int _trainingId, String _trainingName) {
        this._trainingId = _trainingId;
        this._trainingName = _trainingName;
    }

    public int get_trainingId() {
        return _trainingId;
    }

    public void set_trainingId(int _trainingId) {
        this._trainingId = _trainingId;
    }

    public String get_trainingName() {
        return _trainingName;
    }

    public void set_trainingName(String _trainingName) {
        this._trainingName = _trainingName;
    }

    public List<Activit> get_activities() {
        return _activities;
    }

    public void set_activities(List<Activit> _activities) {
        this._activities = _activities;
    }
}
