package edu.ucab.desarrollo.fitucab.common.entities;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase para el manejo de la Entidad Home del modulo 2
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 1.0
 */

@XmlRootElement
public class Home extends Entity {

    private float _totalCaloria;
    private int _totalAgua;

    /**
     * Constructor vacio
     */
    public Home() {
    }

    /**
     * Constructor con todos los atributos
     * @param totalCaloria Total de calorias consumidas
     * @param totalAgua Total de vasos de agua consumidos
     */
    public Home(float totalCaloria, int totalAgua) {
        _totalCaloria = totalCaloria;
        _totalAgua = totalAgua;
    }

    /**
     * Metodo que devuelte el valor de calorias float
     * @return
     */
    public float getTotalCaloria() {
        return _totalCaloria;
    }

    /**
     * Metodo que asigna el valor de calorias float
     * @param totalCaloria
     */
    public void setTotalCaloria(float totalCaloria) {
        _totalCaloria = totalCaloria;
    }

    /**
     * Metodo que devuelve el valor de agua int
     * @return
     */
    public int getTotalAgua() {
        return _totalAgua;
    }

    /**
     * Metodo que asigna un valor agua int
     * @param totalAgua
     */
    public void setTotalAgua(int totalAgua) {
        _totalAgua = totalAgua;
    }

}
