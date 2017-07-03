package com.fitucab.ds1617b.fitucab.Helper;

/**
 * Created by zedin on 02/07/17.
 */

public class LocalTime {
    private int minute;
    private int hour;

    public LocalTime(int minute, int hour) {
        this.minute = minute;
        this.hour = hour;
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
}
