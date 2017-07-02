package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

public class CreateTrainingCommand extends Command {

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
            this.result = dao.createTraining(_newTraining);//nuevo
        }
        catch (AddException ex){
            throw ex;
        }
    }
}
