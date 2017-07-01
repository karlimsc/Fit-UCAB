package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;

/**
 * Created by root on 29/06/17.
 */
public interface IDaoHome {

    public Entity read(Entity user);
    public void buscarCalorias(String usuario);

}