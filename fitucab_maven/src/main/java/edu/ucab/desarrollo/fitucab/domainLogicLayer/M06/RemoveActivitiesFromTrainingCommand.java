package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import jdk.nashorn.internal.runtime.regexp.joni.constants.EncloseType;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.LinkedList;

public class RemoveActivitiesFromTrainingCommand extends Command {
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);
    private Entity training;
    private boolean _result;

    public RemoveActivitiesFromTrainingCommand(Entity training){
        this.training = training;
    }
    public boolean getResult()
    {
        return this._result;
    }
    public void execute() throws DeleteException {
        try {
            DaoTraining dao = DaoFactory.instanceDaoTraining(training);
            _result = dao.removeActivities(training);
        }catch (DeleteException ex){
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw ex;
        }
    }

    @Override
    public Entity Return() throws SQLException, CreateHomeException, BdConnectException
    {
        return null;
    }
}
