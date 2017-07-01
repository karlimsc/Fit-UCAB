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
    /**
     * Metodo implementado por Dao que crea una entidad
     * @param e
     * @return
     * @throws AddException
     */
    public Entity create(Entity e) throws AddException;

    /**
     * Metodo implementado por Dao que lee una entidad
     * @param e
     * @return
     */
    public Entity read(Entity e);

    /**
     * Metodo implementado por Dao que actualiza una entidad
     * @param e
     * @return
     */
    public Entity update(Entity e);
}