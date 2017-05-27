package com.fitucab.ds1617b.fitucab.Model;

/**
 * Created by zedin on 25/05/17.
 */
import java.io.Serializable;

public class Food implements Serializable{
    private int _id;
    private String _foodName;
    private String _foodWeight;
    private String _foodCalorie;
    private Boolean _foodPersonalized;
    private Boolean _foodDinner;

    public Food() {
    }

    public Food(int id, String foodName, String foodWeight, String foodCalorie
            , Boolean foodPersonalized, Boolean foodDinner) {
        this._id = id;
        this._foodName = foodName;
        this._foodWeight = foodWeight;
        this._foodCalorie = foodCalorie;
        this._foodPersonalized = foodPersonalized;
        this._foodDinner = foodDinner;
    }

    public int getId() {
        return _id;
    }

    public void setId(int id) {
        this._id = id;
    }

    public String getFoodName() {
        return _foodName;
    }

    public void setFoodName(String foodName) {
        this._foodName = foodName;
    }

    public String getFoodWeight() {
        return _foodWeight;
    }

    public void setFoodWeight(String foodWeight) {
        this._foodWeight = foodWeight;
    }

    public String getFoodCalorie() {
        return _foodCalorie;
    }

    public void setFoodCalorie(String foodCalorie) {
        this._foodCalorie = foodCalorie;
    }

    public Boolean getFoodPersonalized() {
        return _foodPersonalized;
    }

    public void setFoodPersonalized(Boolean foodPersonalized) {
        this._foodPersonalized = foodPersonalized;
    }

    public Boolean getFoodDinner() {
        return _foodDinner;
    }

    public void setFoodDinner(Boolean foodDinner) {
        this._foodDinner = foodDinner;
    }
}
