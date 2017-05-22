package Domain;

/**
 * Created by charbel on 21/05/2017.
 */
public class Water {
    private     String _time;
    private     Integer _glasstype;
    private     Integer  _fkPerson;
    private     Integer _cantidad;
    private     Integer _suma;

    public  Water ()
    {

    }

    /**
     * Constructor para solo el time y la cantidad de agua
     * @param time
     * @param glasstype
     */
    public Water(String time, Integer glasstype)
    {
        set_time(time);
        set_glasstype(glasstype);
    }

    /**
     * Constructor para solo el time y el ID usuario
     * @param time
     * @param fkPerson
     */
    public Water( Integer fkPerson,String time)
    {
        set_time(time);
        set_fkPerson(fkPerson);
    }

    /**
     * Constructor para time, el id usuario  y tamaño de vaso
     * @param time
     * @param fkPerson
     * @param glasstype
     */
    public Water( Integer glasstype,Integer fkPerson,String time )
    {
        set_glasstype(glasstype);
        set_time(time);
        set_fkPerson(fkPerson);
    }

    /**
     * Constructor para  suma de agua diaria  y cantidad  de vaso
     * diarios
     * @param suma
     * @param cantidad
     */
    public Water(Integer suma , Integer cantidad)
    {
        set_suma(suma);
        set_cantidad(cantidad);
    }

    /**
     * Constructor para time el suma de agua diaria  y cantidad  de vaso
     * diarios
     * @param time
     * @param suma
     * @param cantidad
     */

    public Water(String time ,Integer suma , Integer cantidad)
    {
        set_time(time);
        set_suma(suma);
        set_cantidad(cantidad);

    }

    public String get_time() {
        return _time;
    }

    public void set_time(String _time) {
        this._time = _time;
    }

    public Integer get_glasstype() {
        return _glasstype;
    }

    public void set_glasstype(Integer _glasstype) {
        this._glasstype = _glasstype;
    }

    public Integer get_fkPerson() {
        return _fkPerson;
    }

    public void set_fkPerson(Integer _fkPerson) {
        this._fkPerson = _fkPerson;
    }

    public Integer get_cantidad() {
        return _cantidad;
    }

    public void set_cantidad(Integer _cantidad) {
        this._cantidad = _cantidad;
    }

    public Integer get_suma() {
        return _suma;
    }

    public void set_suma(Integer _suma) {
        this._suma = _suma;
    }
}
