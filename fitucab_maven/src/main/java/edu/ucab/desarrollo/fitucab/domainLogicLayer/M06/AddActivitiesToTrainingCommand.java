package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Activity;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.LinkedList;

public class AddActivitiesToTrainingCommand extends Command {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);
    private Entity training;
    private boolean result;

    public AddActivitiesToTrainingCommand(Entity training){
        this.training = training;
    }

    public boolean getResult()
    {
        return this.result;
    }
    public void execute() throws AddException {
        try {
            DaoTraining dao = DaoFactory.instanceDaoTraining(training);
            this.result = dao.addActivities(training);
        }catch (AddException ex){
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
