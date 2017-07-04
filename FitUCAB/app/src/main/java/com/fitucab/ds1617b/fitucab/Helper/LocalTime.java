package com.fitucab.ds1617b.fitucab.Helper;

/**
 * Created by zedin on 02/07/17.
 */

public class LocalTime {
    private int minute;
    private int hour;
    private int nano;
    private int second;

    public LocalTime(int minute, int hour) {
        this.minute = minute;
        this.hour = hour;
    }

    public LocalTime(int minute, int hour, int nano, int second) {
        this.minute = minute;
        this.hour = hour;
        this.nano = nano;
        this.second = second;
    }
    public LocalTime() {

    }


    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getNano() {
        return nano;
    }

    public void setNano(int nano) {
        this.nano = nano;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }
}
