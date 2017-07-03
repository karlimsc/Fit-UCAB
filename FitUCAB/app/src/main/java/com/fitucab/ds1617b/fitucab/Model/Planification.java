package com.fitucab.ds1617b.fitucab.Model;

import com.fitucab.ds1617b.fitucab.Helper.LocalDate;
import com.fitucab.ds1617b.fitucab.Helper.LocalTime;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by zedin on 02/07/17.
 */

public class Planification{
    private int _planificationId;
    private Date _startDate;
    private Date _endDate;
    private Time _startTime;
    private Time _duration;
    private boolean[] _days = new boolean[7]; // 0 es lunes, 6 es domingo
    private int _user;
    private int _sport;

    public Planification(int _planificationId, Date _startDate, Date _endDate,
                         Time _startTime, Time _duration, boolean[] _days,
                         int _user, int _sport) {
        this._planificationId = _planificationId;
        this._startDate = _startDate;
        this._endDate = _endDate;
        this._startTime = _startTime;
        this._duration = _duration;
        this._days = _days;
        this._user = _user;
        this._sport = _sport;
    }

    public int get_planificationId() {
        return _planificationId;
    }

    public void set_planificationId(int _planificationId) {
        this._planificationId = _planificationId;
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

    public void set_startDate(Date _startDate) {
        this._startDate = _startDate;
    }

    public void set_endDate(Date _endDate) {
        this._endDate = _endDate;
    }

    public void set_startTime(Time _startTime) {
        this._startTime = _startTime;
    }

    public void set_duration(Time _duration) {
        this._duration = _duration;
    }

    public Date get_startDate() {
        return _startDate;
    }

    public Date get_endDate() {
        return _endDate;
    }

    public Time get_startTime() {
        return _startTime;
    }

    public Time get_duration() {
        return _duration;
    }
}
