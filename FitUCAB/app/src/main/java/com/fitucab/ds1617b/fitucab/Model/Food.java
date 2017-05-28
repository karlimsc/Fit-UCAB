package com.fitucab.ds1617b.fitucab.Model;

/**
 * Created by zedin on 25/05/17.
 */
import java.io.Serializable;

public class Food implements Serializable{
    private int id;
    private String foodName;
    private String foodWeight;
    private String foodCalorie;
    private Boolean foodPersonalized;
    private Boolean foodDinner;

    public Food() {
    }

    public Food(int id, String foodName, String foodWeight, String foodCalorie
            , Boolean foodPersonalized, Boolean foodDinner) {
        this.id = id;
        this.foodName = foodName;
        this.foodWeight = foodWeight;
        this.foodCalorie = foodCalorie;
        this.foodPersonalized = foodPersonalized;
        this.foodDinner = foodDinner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodWeight() {
        return foodWeight;
    }

    public void setFoodWeight(String foodWeight) {
        this.foodWeight = foodWeight;
    }

    public String getFoodCalorie() {
        return foodCalorie;
    }

    public void setFoodCalorie(String foodCalorie) {
        this.foodCalorie = foodCalorie;
    }

    public Boolean getFoodPersonalized() {
        return foodPersonalized;
    }

    public void setFoodPersonalized(Boolean foodPersonalized) {
        this.foodPersonalized = foodPersonalized;
    }

    public Boolean getFoodDinner() {
        return foodDinner;
    }

    public void setFoodDinner(Boolean foodDinner) {
        this.foodDinner = foodDinner;
    }
}
