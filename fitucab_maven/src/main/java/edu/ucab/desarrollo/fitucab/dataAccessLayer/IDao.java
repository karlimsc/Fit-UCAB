package edu.ucab.desarrollo.fitucab.dataAccessLayer;

import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;

import java.sql.SQLException;

/**
 * Interface que implementa el DAO
 * que actua como contrato con los metodos comunes
 */
public interface IDao
{
    public Entity create(Entity e) throws AddException, AddException, AddException, SQLException, Exception;

    public Entity read(Entity e) throws CreateHomeException, SQLException, BdConnectException;

    public Entity update(Entity e);
}