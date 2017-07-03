package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;

/**
 * Interfaz de la clase DAO Home para el manejo de la Entidad Home.
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 1.0
 */
public interface IDaoHome {



    /**
     * Metodo que implementa el DaoHome que lee una entidad
     * @param user
     * @return
     */
    public Entity read(Entity user) throws CreateHomeException;

    /**
     * Metodo que implementa el DaoHome que recibe un String
     * @param usuario
     */
    public void buscarCalorias(String usuario) throws CreateHomeException;

}