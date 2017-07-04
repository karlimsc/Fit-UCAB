package edu.ucab.desarrollo.fitucab.common.entities;

import com.google.gson.annotations.SerializedName;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Clase del Modulo 1 para registrar al usuario
 */

@XmlRootElement
public class Registry {

    @SerializedName("_idRegistry")
    private int _idRegistry;
    @SerializedName("_weight")
    private float _weight;
    @SerializedName("_height")
    private float _height;
    @SerializedName("_registryPoint")
    private int _registryPoint;

    /**
     * Constructor vacio
     */
    public Registry(){};

    /**
     * Constructor con todos los atributos
     * @param idRegistry
     * @param weight
     * @param height
     * @param registryPoint
     */
    public Registry(int idRegistry, float weight,float height,int registryPoint)
    {
     _idRegistry = idRegistry;
     _weight = weight;
     _height = height;
     _registryPoint = registryPoint;
    };

    public Registry(int idRegistry)
    {
        _idRegistry = idRegistry;

    };

    /**
     * Contructor sin el id
     * @param weight
     * @param height
     * @param registryPoint
     */
    public Registry( float weight,float height,int registryPoint)
    {

        _weight = weight;
        _height = height;
        _registryPoint = registryPoint;
    };

    /**
     * Constructor sin los puntos del usuario
     * @param weight
     * @param height
     */
    public Registry( float weight,float height)
    {

        _weight = weight;
        _height = height;
    };


    public int getIdRegistry()
    {
        return _idRegistry;
    }
    public void setIdRegistry( int idRegistry)
    {
        this._idRegistry = idRegistry;
    }

    public float getWeight()
    {
        return _weight;
    }
    public void setWeight( float weight)
    {
        this._weight = weight;
    }

    public float getHeight()
    {
        return _height;
    }
    public void setHeight( float height)
    {
        this._height = height;
    }

    public int getRegistryPoint()
    {
        return _registryPoint;
    }
    public void setregistryPoint( int registryPoint)
    {
        this._registryPoint = registryPoint;
    }
}
