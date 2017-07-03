package edu.ucab.desarrollo.fitucab.common.entities;

import java.time.LocalTime;
import java.time.LocalDate;

/**
 * Created by jaorr on 30/06/17.
 */
public class Planification extends Entity {

    private LocalDate _startDate;
    private LocalDate _endDate;
    private LocalTime _startTime;
    private LocalTime _duration;
    private boolean[] _days = new boolean[7]; // 0 es lunes, 6 es domingo
    private int _user;
    private int _sport;


    public Planification(int id, LocalDate startDate, LocalDate endDate, LocalTime startTime,
                         LocalTime duration, boolean[] days, int user, int sport) {

        super(id);
        this._startDate = startDate;
        this._endDate = endDate;
        this._startTime = startTime;
        this._duration = duration;
        this._days = days;
        this._user = user;
        this._sport = sport;

    }

    public Planification(LocalDate startDate, LocalDate endDate, LocalTime startTime,
                         LocalTime duration, boolean[] days, int user, int sport) {
        this._startDate = startDate;
        this._endDate = endDate;
        this._startTime = startTime;
        this._duration = duration;
        this._days = days;
        this._user = user;
        this._sport = sport;
    }

    public Planification(int _id, int user) {
        super(_id);
        this._user = user;
    }

    public Planification(int user) {
        this._user = user;
    }

    public Planification() {
    }


    public LocalDate get_startDate() {
        return _startDate;
    }

    public void set_startDate(LocalDate startDate) {
        this._startDate = startDate;
    }

    public LocalDate get_endDate() {
        return _endDate;
    }

    public void set_endDate(LocalDate endDate) {
        this._endDate = endDate;
    }

    public LocalTime get_startTime() {
        return _startTime;
    }

    public void set_startTime(LocalTime startTime) {
        this._startTime = startTime;
    }

    public LocalTime get_duration() {
        return _duration;
    }

    public void set_duration(LocalTime duration) {
        this._duration = duration;
    }

    public boolean[] get_days() {
        return _days;
    }

    public void set_days(boolean[] days) {
        this._days = days;
    }

    public int get_user() {
        return _user;
    }

    public void set_user(int user) {
        this._user = user;
    }

    public int get_sport() {
        return _sport;
    }

    public void set_sport(int sport) {
        this._sport = sport;
    }
}
