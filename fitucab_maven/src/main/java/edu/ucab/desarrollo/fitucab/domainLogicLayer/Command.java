package edu.ucab.desarrollo.fitucab.domainLogicLayer;


import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;

public abstract class Command
{

    public abstract void execute() throws ListAllException, ListByIdException;

}