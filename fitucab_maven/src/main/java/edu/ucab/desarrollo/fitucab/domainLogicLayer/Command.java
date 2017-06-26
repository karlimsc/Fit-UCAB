package edu.ucab.desarrollo.fitucab.domainLogicLayer;


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
    public abstract void execute() throws ListAllException, ListByIdException;

}