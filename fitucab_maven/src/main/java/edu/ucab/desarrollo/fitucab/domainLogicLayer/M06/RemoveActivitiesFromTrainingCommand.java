package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.DeleteException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import jdk.nashorn.internal.runtime.regexp.joni.constants.EncloseType;
import org.slf4j.LoggerFactory;

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
            logger.debug(error.toString());
            throw ex;
        }
    }
}
