package edu.ucab.desarrollo.fitucab.common.entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase para el manejo de la Entidad Home del modulo 2
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 1.0
 */

@XmlRootElement
public class Home extends Entity {

    private float _totalCalories;
    private int _totalWater;

    /**
     * Constructor vacio
     */
    public Home() {
    }

    /**
     * Constructor con todos los atributos
     * @param _totalCalories Total de calorias consumidas
     * @param _totalWater Total de vasos de agua consumidos
     */
    public Home(float _totalCalories, int _totalWater) {
        this._totalCalories = _totalCalories;
        this._totalWater = _totalWater;
    }

    /**
     * Metodo que devuelte el valor de calorias float
     * @return
     */
    public float getTotalCalories() {
        return _totalCalories;
    }

    /**
     * Metodo que asigna el valor de calorias float
     * @param _totalCalories
     */
    public void setTotalCalories(float _totalCalories) {
        this._totalCalories = _totalCalories;
    }

    /**
     * Metodo que devuelve el valor de agua int
     * @return
     */
    public int getTotalWater() {
        return _totalWater;
    }

    /**
     * Metodo que asigna un valor agua int
     * @param _totalWater
     */
    public void setTotalWater(int _totalWater) {
        this._totalWater = _totalWater;
    }

}
