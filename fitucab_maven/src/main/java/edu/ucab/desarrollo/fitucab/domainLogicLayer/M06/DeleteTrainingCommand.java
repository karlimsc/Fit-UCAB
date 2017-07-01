package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

public class DeleteTrainingCommand extends Command {
    private Entity deleteTrainingObject;

    public DeleteTrainingCommand(Entity _training){
        this.deleteTrainingObject = _training;
    }
    public void execute() {
        try{
            DaoTraining dao = DaoFactory.instanceDaoTraining(deleteTrainingObject);
            dao.delete(deleteTrainingObject);
        }
        catch(Exception e){
            //lanzar exception
        }
    }
}
