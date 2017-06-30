package edu.ucab.desarrollo.fitucab.domainLogicLayer;


import edu.ucab.desarrollo.fitucab.common.entities.Challenge;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;

import java.sql.SQLException;
import java.util.List;
import edu.ucab.desarrollo.fitucab.common.Exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.Exceptions.ListByIdException;

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
    public abstract void execute() throws ListAllException, ListByIdException, NoSuchMethodException, SQLException;

    public List<Entity> getChallenges(){
        return null;
    }

    public Entity getChallenge(){
        return null;
    }

}