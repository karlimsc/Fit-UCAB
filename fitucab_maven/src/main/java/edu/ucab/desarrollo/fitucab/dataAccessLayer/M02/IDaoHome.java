package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;

/**
 * Created by root on 29/06/17.
 */
public interface IDaoHome {
    /**
     * Metodo que implementa el DaoHome que lee una entidad
     * @param user
     * @return
     */
    public Entity read(Entity user);

    /**
     * Metodo que implementa el DaoHome que recibe un String
     * @param usuario
     */
    public void buscarCalorias(String usuario);

}