package edu.ucab.desarrollo.fitucab.common.entities;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by jaorr on 22/05/17.
 */
public class Food extends Entity{

    private int _iD;

    private String _foodName;

    private String _foodWeight;

    private String _foodCalorie;

    private Boolean _foodPersonalized;

    private String _idname ;

    public Food() {
    }


    public Food (String foodname , int id )
    {
        _foodName=foodname ; _iD = id;
    }
    public Food(int id, String foodName, String foodWeight, String foodCalorie, Boolean _foodPersonalized) {
        this._iD = id;
        this._foodName = foodName;
        this._foodWeight = foodWeight;
        this._foodCalorie = foodCalorie;
        this._foodPersonalized = _foodPersonalized;
    }

    public Food(String idname) {
        _idname = idname;
    }

    public Food (String id , String foodCalorie){
        _foodName =id ; _foodCalorie=foodCalorie;
    }






    public String get_idname(){return _idname;}
    public int get_id() {
        return _iD;
    }

    public void set_id(int id) {
        this._iD = id;
    }

    public String get_foodName() {
        return _foodName;
    }

    public void set_foodName(String foodName) {
        this._foodName = foodName;
    }

    public String get_foodWeight() {
        return _foodWeight;
    }

    public void set_foodWeight(String foodWeight) {
        this._foodWeight = foodWeight;
    }

    public String get_foodCalorie() {
        return _foodCalorie;
    }

    public void set_foodCalorie(String foodCalorie) {
        this._foodCalorie = foodCalorie;
    }

    public Boolean get_foodPersonalized() {
        return _foodPersonalized;
    }

    public void set_foodPersonalized(Boolean foodPersonalized) {
        this._foodPersonalized = foodPersonalized;
    }
}
