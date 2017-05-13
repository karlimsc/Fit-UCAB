package com.fitucab.ds1617b.fitucab.Model;

import android.graphics.drawable.Icon;

/**
 * Created by Colmenares on 26/03/2017.
 */

public class User {

    private String Nombre;
    private Icon avatar;

    private int puntaje;


    public User(String nombre, Icon avatar, int puntaje) {
        Nombre = nombre;
        this.avatar = avatar;
        this.puntaje = puntaje;
    }

    public User(String nombre, int puntaje) {
        Nombre = nombre;
        this.puntaje = puntaje;
    }


    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Icon getAvatar() {
        return avatar;
    }

    public void setAvatar(Icon avatar) {
        this.avatar = avatar;
    }

    public int getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(int puntaje) {
        this.puntaje = puntaje;
    }
}
