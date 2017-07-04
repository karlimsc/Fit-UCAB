package edu.ucab.desarrollo.fitucab.common.entities;

/**
 * Created by root on 24/06/17.
 */

/**
 * Clase actividad para ser usada por Entrenamiento
 */
public class Activity extends Entity {
    private String _name;
    private int _duration;
    /**
     * Constructor con id y nombre
     * @param _id
     * @param _name
     */
    public Activity(int _id, String _name, int _duration) {
        super(_id);
        this._name = _name;
        this._duration = _duration;
    }

    /**
     * Constructor vacio
     */
    public Activity() {
    }

    public Activity(int id, String name)
    {
        super(id);
        this._name = _name;
    }

    /**
     * Get del nombre de la actividad
     * @return
     */
    public String get_name() {
        return _name;
    }

    /**
     * Set del nombre de la actividad
     * @param _name
     */
    public void set_name(String _name) {
        this._name = _name;
    }

    /**
     * Get del duracion de la actividad
     * @return
     */

    public int get_duration() {
        return _duration;
    }

    /**
     * Set del nombre de la actividad
     * @param _duration
     */
    public void set_duration(int _duration) {
        this._duration = _duration;
    }

}
