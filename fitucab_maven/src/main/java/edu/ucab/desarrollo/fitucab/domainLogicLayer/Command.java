package edu.ucab.desarrollo.fitucab.domainLogicLayer;


import edu.ucab.desarrollo.fitucab.common.entities.Challenge;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;

import java.util.List;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;

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

    public List<Entity> getChallenges(){
        return null;
    }

    public Entity getChallenge(){
        return null;
    }

    public abstract Entity Return();
}