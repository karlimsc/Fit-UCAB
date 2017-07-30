package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M01.CreateUserException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.IDaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class CreateTrainingCommand extends Command {


    Entity _newTraining;
    Entity _result;

    private Entity result;

    public CreateTrainingCommand(Entity newTraining){

        _newTraining = newTraining;

    }

    public void execute() throws AddException, SQLException, BdConnectException, CreateUserException
    {
        IDaoTraining dao;
        dao = DaoFactory.instanceDaoTraining(_newTraining);
        result = dao.create(_newTraining);
    }

    public Entity getResult()
    {
        return result;
    }

    public Entity Return(){
        return null;
    }

}
