package com.fitucab.ds1617b.fitucab.Model;

import java.io.Serializable;

/**
 * Created by karo on 26/05/17.
 */

@SuppressWarnings("serial")
public class Sport implements Serializable {
    private int id;
    private String name;
    private float mets;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMets() {
        return mets;
    }

    public void setMets(float mets) {
        this.mets = mets;
    }
}

