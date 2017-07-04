package com.fitucab.ds1617b.fitucab.Model;

import com.fitucab.ds1617b.fitucab.Helper.LocalDate;
import com.fitucab.ds1617b.fitucab.Helper.LocalTime;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by zedin on 02/07/17.
 */

public class Planification {
    private int _id;
    private LocalDate _startDate;
    private LocalDate _endDate;
    private LocalTime _startTime;
    private LocalTime _duration;
    private boolean[] _days = new boolean[7]; // 0 es lunes, 6 es domingo
    private int _user;
    private int _sport;
    private int _errorCode;
    private String _errorMsg;


    public Planification() {

    }

    public Planification(int _id, LocalDate _startDate, LocalDate _endDate, LocalTime _startTime,
                         LocalTime _duration, boolean[] _days, int _user,
                         int _sport, int _errorCode, String _errorMsg) {
        this._id = _id;
        this._startDate = _startDate;
        this._endDate = _endDate;
        this._startTime = _startTime;
        this._duration = _duration;
        this._days = _days;
        this._user = _user;
        this._sport = _sport;
        this._errorCode = _errorCode;
        this._errorMsg = _errorMsg;
    }


    public boolean[] get_days() {
        return _days;
    }

    public void set_days(boolean[] _days) {
        this._days = _days;
    }

    public int get_user() {
        return _user;
    }

    public void set_user(int _user) {
        this._user = _user;
    }

    public int get_sport() {
        return _sport;
    }

    public void set_sport(int _sport) {
        this._sport = _sport;
    }

    public LocalDate get_startDate() {
        return _startDate;
    }

    public void set_startDate(LocalDate _startDate) {
        this._startDate = _startDate;
    }

    public LocalDate get_endDate() {
        return _endDate;
    }

    public void set_endDate(LocalDate _endDate) {
        this._endDate = _endDate;
    }

    public LocalTime get_startTime() {
        return _startTime;
    }

    public void set_startTime(LocalTime _startTime) {
        this._startTime = _startTime;
    }

    public LocalTime get_duration() {
        return _duration;
    }

    public void set_duration(LocalTime _duration) {
        this._duration = _duration;
    }

    public int get_errorCode() {
        return _errorCode;
    }

    public void set_errorCode(int _errorCode) {
        this._errorCode = _errorCode;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_errorMsg() {
        return _errorMsg;
    }

    public void set_errorMsg(String _errorMsg) {
        this._errorMsg = _errorMsg;
    }

    @Override
    public String toString() {
        return  this._startDate.getYear() +"/" +this._startDate.getMonth()+"/"+ this._startDate.getDay() +
                "-" + this._endDate.getYear() +"/" +this._endDate.getMonth()+"/"+ this._endDate.getDay();
    }
}

