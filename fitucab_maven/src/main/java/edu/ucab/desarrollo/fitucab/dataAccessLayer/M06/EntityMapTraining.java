package edu.ucab.desarrollo.fitucab.dataAccessLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Training;

import java.util.HashMap;

/**
 * Created by root on 01/07/17.
 */
public class EntityMapTraining
{
    private static EntityMapTraining _instance = null;

    private HashMap<Integer, Training> _map;

    /**
     * Constructor privado
     */
    private EntityMapTraining()
    {
        _map = new HashMap<Integer, Training>();
    }


    public static EntityMapTraining getInstance()
    {
        if ( _instance == null )
            _instance = new EntityMapTraining();

        return _instance;
    }


    public void put( Integer key, Training value )
    {
        _map.put( key, value );
    }


    public Training get( Integer key )
    {
        return _map.get( key );
    }


    public Training replace( Integer key, Training value )
    {
        return _map.replace( key, value );
    }

}
