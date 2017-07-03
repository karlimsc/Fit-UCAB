package com.fitucab.ds1617b.fitucab.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 01/07/17.
 */

public class Training implements Parcelable {

    @SerializedName("_trainingid")
    private int _trainingId;
    @SerializedName("_trainingname")
    private String _trainingName;
    @SerializedName("_activities")
    private ArrayList<Activit> _activities;

    private int _trainingOrderInView;


    public Training() {
    }

    public Training(int _trainingId, String _trainingName, ArrayList<Activit> _activities) {
        this._trainingId = _trainingId;
        this._trainingName = _trainingName;
        this._activities = _activities;
    }

    public Training(int _trainingId, String _trainingName) {
        this._trainingId = _trainingId;
        this._trainingName = _trainingName;
    }

    protected Training(Parcel in) {
        _trainingId = in.readInt();
        _trainingName = in.readString();
        _trainingOrderInView = in.readInt();
    }

    public static final Creator<Training> CREATOR = new Creator<Training>() {
        @Override
        public Training createFromParcel(Parcel in) {
            return new Training(in);
        }

        @Override
        public Training[] newArray(int size) {
            return new Training[size];
        }
    };

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

    public ArrayList<Activit> get_activities() {
        return _activities;
    }

    public void set_activities(ArrayList<Activit> _activities) {
        this._activities = _activities;
    }

    public int get_trainingOrderInView() {
        return _trainingOrderInView;
    }

    public void set_trainingOrderInView(int _trainingOrderInView) {
        this._trainingOrderInView = _trainingOrderInView;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel target, int i) {
        try {
            target.writeInt(get_trainingId());
            target.writeString(get_trainingName());
            target.writeList(get_activities());
        }catch (Exception e){
            ///TODO RECIBE EXCEPTION
            e.printStackTrace();
        }
    }

    private void readFromParcel(Parcel in) {
        try{
            _trainingId = in.readInt();
            _trainingName = in.readString();
            _activities = in.readArrayList(null);
        }catch (Exception e){
            ///TODO RECIBE EXCEPTION
            e.printStackTrace();
        }

    }


}
