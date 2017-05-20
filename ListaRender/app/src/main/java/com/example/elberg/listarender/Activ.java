package com.example.elberg.listarender;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.util.Date;

/**
 * Clase resferida a las actividades ejecutads por el usuario
 *
 */

public class Activ {

    private int uptime;
    private String sportName ;
    private double metersActivity;
    private double  speedActivity;
    private Date starTimeActivity;
    private Date endTimeAtivity;
    private String placeStartActivity;
    private String placeEndActivity;
    private int caloriesBurned;
    private String dateActivity;
    private ImageView imege;



    // Usado para llenar el ListView de la ventana principal del modulo 5

    public Activ(int uptime, String sportName, double metersActivity, double speedActivity,
                 String dateActivity) {
        this.uptime = uptime;
        this.sportName = sportName;
        this.metersActivity = metersActivity;
        this.speedActivity = speedActivity;
        this.dateActivity = dateActivity;

    }



    public int getUptime() {
        return uptime;
    }

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public void setUptime(int uptime) {
        this.uptime = uptime;
    }

    public double getMetersActivity() {
        return metersActivity;
    }

    public ImageView getImege() {
        return imege;
    }

    public void setImege(ImageView imege) {
        this.imege = imege;
    }

    public void setMetersActivity(double metersActivity) {
        this.metersActivity = metersActivity;
    }

    public double getSpeedActivity() {
        return speedActivity;
    }

    public void setSpeedActivity(double speedActivity) {
        this.speedActivity = speedActivity;
    }

    public Date getStarTimeActivity() {
        return starTimeActivity;
    }

    public void setStarTimeActivity(Date starTimeActivity) {
        this.starTimeActivity = starTimeActivity;
    }

    public Date getEndTimeAtivity() {
        return endTimeAtivity;
    }

    public void setEndTimeAtivity(Date endTimeAtivity) {
        this.endTimeAtivity = endTimeAtivity;
    }

    public String getPlaceStartActivity() {
        return placeStartActivity;
    }

    public void setPlaceStartActivity(String placeStartActivity) {
        this.placeStartActivity = placeStartActivity;
    }

    public String getPlaceEndActivity() {
        return placeEndActivity;
    }

    public void setPlaceEndActivity(String placeEndActivity) {
        this.placeEndActivity = placeEndActivity;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(int caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    public String getDateActivity() {
        return dateActivity;
    }

    public void setDateActivity(String dateActivity) {
        this.dateActivity = dateActivity;
    }
}
