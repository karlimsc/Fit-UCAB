package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Activity;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.util.LinkedList;

public class AddActivitiesToTrainingCommand extends Command {
    private Entity training;

    public AddActivitiesToTrainingCommand(Entity training){
        this.training = training;
    }
    public void execute()  {
        DaoTraining dao = DaoFactory.instanceDaoTraining(training);
        dao.addActivities(training);
    }
}
