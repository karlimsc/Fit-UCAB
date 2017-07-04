package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.IDaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import org.slf4j.LoggerFactory;

/**
 * Created by root on 25/06/17.
 */
public class GetTrainingDetailCommand extends Command
{
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);

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

    public Entity Return(){
        return null;
    }

}
