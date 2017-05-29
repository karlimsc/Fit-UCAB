package Domain;

/**
 * Created by estefania on 14/05/2017.
 */
public class Sport {
    private int    _id;
    private String _name;
    private float  _met;

    /**
     * Constructor de todos los atributos de la clase Sport
     * @param _id
     * @param _name
     * @param _met
     */
    public Sport(int _id, String _name, float _met) {
        this._id = _id;
        this._name = _name;
        this._met = _met;
    }

    /**
     * Constructor que de nombre y unidades met del deporte
     * @param _name
     * @param _met
     */
    public Sport(String _name, float _met) {
        this._name = _name;
        this._met = _met;
    }

    /**
     * Constructor id+ nombre del deporte
     * @param _id
     * @param _name
     */
    public Sport(int _id, String _name) {
        this._id = _id;
        this._name = _name;

    }

    /**
     * Constructor del id del deporte
     * @param _id
     */

    public Sport(int _id) {
        this._id = _id;
    }

    /**
     * Constructor del met del deporte
     * @param _met
     */
    public Sport(float _met) {
        this._met = _met;
    }
    public Sport() {

    }

    public int getId() {
        return _id;
    }
    public void setId(int _id) {
        this._id = _id;
    }

    public String getName() {
        return _name;
    }
    public void setName(String _name) {
        this._name = _name;
    }

    public float getMet() {
        return _met;
    }
    public void setMet(float _met) {
        this._met = _met;
    }

}
