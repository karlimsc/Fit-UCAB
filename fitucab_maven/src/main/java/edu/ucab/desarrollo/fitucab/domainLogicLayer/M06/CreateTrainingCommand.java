package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import org.slf4j.LoggerFactory;

public class CreateTrainingCommand extends Command {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);
    Entity _newTraining;

    private Entity result;

    public CreateTrainingCommand(Entity newTraining){

        this._newTraining = newTraining;

    }
    public Entity getResult()
    {
        return this.result;
    }

    public void execute() throws AddException {
        try{
            DaoTraining dao = DaoFactory.instanceDaoTraining( _newTraining);
            this.result = dao.create(_newTraining);//nuevo
        }
        catch (AddException ex){
            MessageException error = new MessageException(ex, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug(error.toString());
            logger.error(error.toString());
            throw ex;
        }
    }

    public Entity Return(){
        return null;
    }

}
