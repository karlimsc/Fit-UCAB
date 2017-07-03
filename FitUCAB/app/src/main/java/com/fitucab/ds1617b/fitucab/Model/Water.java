package com.fitucab.ds1617b.fitucab.Model;

import com.fitucab.ds1617b.fitucab.Model.Entity;

public class Water extends Entity {
    private     String _time;
    private     String  hora;
    private     Integer _glasstype;
    private     Integer  _fkPerson;
    private     Integer _cantidad;
    private     Integer _suma;
    private     String _error;


    /**
     * Constructor solo para id person
     * @param fkPerson
     */

    public  Water(Integer fkPerson)
    {
        _fkPerson=fkPerson;
    }

    /**
     * Constructor vacio
     */
    public Water(){}

    /**
     * Constructor error
     * @param error
     */

    public  Water (String error)
    {
        _error=error;
    }


    /**
     * Constructor para solo el time y la cantidad de agua
     * @param time
     * @param glasstype
     */

    public Water(String time, Integer glasstype)
    {
        _time = time;
        _glasstype = glasstype;
    }


    /**
     * Constructor para solo el time y el ID usuario
     * @param time
     * @param fkPerson
     */

    public Water( Integer fkPerson,String time)
    {
        _time=time;
        _fkPerson=fkPerson;
    }


    /**
     * Constructor para time, el id usuario  y tamaño de vaso
     * @param time
     * @param fkPerson
     * @param glasstype
     */

    public Water( Integer glasstype,Integer fkPerson,String time )
    {
        _glasstype= glasstype;
        _time=time;
        _fkPerson=fkPerson;
    }


    /**
     * Constructor para  suma de agua diaria  y cantidad  de vaso
     * diarios
     * @param suma
     * @param cantidad
     */

    public Water(Integer suma , Integer cantidad)
    {
        _suma = suma;
        _cantidad=cantidad;
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
        _time= time;
        _suma=suma;
        _cantidad=cantidad;

    }


    //Getters y Setters de toddos los atributos.

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

    public void set_error(String error){
        _error=error;
    }

    public String get_error(){
        return _error;
    }


    public void set_hora(String hora){
        this.hora=hora;
    }

    public String get_get(){
        return hora;
    }
}
