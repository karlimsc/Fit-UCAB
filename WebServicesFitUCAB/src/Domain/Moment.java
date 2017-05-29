package Domain;

/**
 * Created by jaorr on 22/05/17.
 */
public class Moment {
    private int _id;
    private String _description;

    public Moment() {}

    public Moment(int id, String description) {
        this._id = id;
        this._description = description;
    }

    public Moment(String description) {
        this._description = description;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int id) {
        this._id = id;
    }

    public String get_description() {
        return _description;
    }

    public void set_description(String description) {
        this._description = description;
    }
}
