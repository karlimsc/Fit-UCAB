package edu.ucab.desarrollo.fitucab.domainLogicLayer;


import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;

import java.sql.SQLException;
import java.util.List;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;

/**
 * Patron Comando
 */
public abstract class Command
{

    /**
     * Metodo ejecutar
     * @throws ListAllException excepcion para listar todos
     * @throws ListByIdException excepcion para listar por id
     */

    public abstract void execute() throws ListAllException, ListByIdException, NoSuchMethodException, Exception;

    public List<Entity> getFriendships(){
        return null;
    }

    public Entity getFriendship(){
        return null;
    }

    public abstract Entity Return() throws SQLException, CreateHomeException, BdConnectException;

    public Boolean ReturnUpdate() {
        return null;
    }
}