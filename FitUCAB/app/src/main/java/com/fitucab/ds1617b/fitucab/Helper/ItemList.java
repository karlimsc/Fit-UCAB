package com.fitucab.ds1617b.fitucab.Helper;

/**
 * Created by Ubiipagos on 2/7/2017.
 */

public class ItemList {

    private String award;
    private String score;
    private String archievements;
    private String description;


    public ItemList(String score, String archievements, String description) {
        this.score = score;
        this.archievements = archievements;
        this.description = description;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getArchievements() {
        return archievements;
    }

    public void setArchievements(String archievements) {
        this.archievements = archievements;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
