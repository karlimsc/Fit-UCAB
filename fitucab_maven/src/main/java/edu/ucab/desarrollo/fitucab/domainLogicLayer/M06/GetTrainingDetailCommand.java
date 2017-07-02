package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Activity;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Training;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
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

    private Entity _input;
    private Entity _output;

    public GetTrainingDetailCommand(Entity training)
    {
        _input = training;
    }

    public void execute() throws ListAllException, ListByIdException
    {
        IDaoTraining dao;
        dao = DaoFactory.instanceDaoTraining(_input);
        _output = dao.trainingDetail(_input);

    }

    public Entity get_output()
    {
        return _output;
    }
}
