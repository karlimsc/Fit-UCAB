package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Activity;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Training;
import edu.ucab.desarrollo.fitucab.common.Exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.Exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.IDaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.util.LinkedList;
import java.util.List;

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
}
