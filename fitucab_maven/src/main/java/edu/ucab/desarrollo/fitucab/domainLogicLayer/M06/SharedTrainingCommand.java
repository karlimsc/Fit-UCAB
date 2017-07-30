package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class SharedTrainingCommand extends Command {

    private Entity _training;
    private Entity _result;

    public SharedTrainingCommand(Entity training){

        _training = training;
    }

    public void execute() throws SharedException
    {

            DaoTraining dao = DaoFactory.instanceDaoTraining(_training);
            _result = dao.sharedTraining(_training);
    }

    public Entity get_result()
    {
        return _result;
    }

    @Override
    public Entity Return()
    {
        return null;
    }
}
