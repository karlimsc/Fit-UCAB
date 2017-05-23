package Domain;

import java.sql.Date;

/**
 * Created by jaorr on 22/05/17.
 */
public class Diet {
    private int _id;
    private int _calorie;
    private Date _dateTime;
    private Food _food;


    public Diet() {}

    public Diet(int id, int calorie, Date dateTime, Food food) {
        this._id = id;
        this._calorie = calorie;
        this._dateTime = dateTime;
        this._food = food;
    }

    public Diet(int _id, int calorie, Date dateTime) {
        this._id = _id;
        this._calorie = calorie;
        this._dateTime = dateTime;
    }

    public Diet(int calorie, Date dateTime) {
        this._calorie = calorie;
        this._dateTime = dateTime;
    }

    public Diet(Date dateTime) {
        this._dateTime = dateTime;
    }

    public Diet(int calorie) {
        this._calorie = calorie;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public int get_calorie() {
        return _calorie;
    }

    public void set_calorie(int calorie) {
        this._calorie = calorie;
    }

    public Date get_dateTime() {
        return _dateTime;
    }

    public void set_dateTime(Date dateTime) {
        this._dateTime = dateTime;
    }

    public Food get_food() {
        return _food;
    }

    public void set_food(Food food) {
        this._food = food;
    }
}
