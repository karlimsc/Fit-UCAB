package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;

/**
 * Created by root on 29/06/17.
 */
public interface IDaoHome {

    public Entity read(Entity user) throws CreateHomeException;
    public void buscarCalorias(String usuario) throws CreateHomeException;

}