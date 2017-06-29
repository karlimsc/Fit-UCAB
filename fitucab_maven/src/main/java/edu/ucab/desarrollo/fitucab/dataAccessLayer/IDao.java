package edu.ucab.desarrollo.fitucab.dataAccessLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;

import java.util.List;

/**
 * Interface que implementa el DAO
 * que actua como contrato con los metodos comunes
 */
public interface IDao
{
    public Entity create(Entity e) throws AddException, Exception;

    public Entity read(Entity e);

    public Entity update(Entity e);
}