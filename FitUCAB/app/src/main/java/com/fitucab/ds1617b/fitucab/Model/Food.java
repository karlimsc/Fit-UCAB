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

    public int get_Id() {
        return _id;
    }

    public void set_Id(int id) {
        this._id = id;
    }

    public String get_FoodName() {
        return _foodName;
    }

    public void set_FoodName(String foodName) {
        this._foodName = foodName;
    }

    public String get_FoodWeight() {
        return _foodWeight;
    }

    public void set_FoodWeight(String foodWeight) {
        this._foodWeight = foodWeight;
    }

    public String get_FoodCalorie() {
        return _foodCalorie;
    }

    public void set_FoodCalorie(String foodCalorie) {
        this._foodCalorie = foodCalorie;
    }

    public Boolean get_FoodPersonalized() {
        return _foodPersonalized;
    }

    public void set_FoodPersonalized(Boolean foodPersonalized) {
        this._foodPersonalized = foodPersonalized;
    }

    public Boolean get_FoodDinner() {
        return _foodDinner;
    }

    public void set_FoodDinner(Boolean foodDinner) {
        this._foodDinner = foodDinner;
    }
}
