package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

/**
 * Created by root on 25/06/17.
 */
public class GetTrainingDetailCommand extends Command
{

    Entity _training;
    public GetTrainingDetailCommand(Entity training)
    {
        this._training = training;

    }
    public void execute() throws ListAllException, ListByIdException
    {

    }

    public Entity Return(){
        return null;
    }

}
