package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ShareException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

public class ShareTrainingCommand extends Command {
    private Entity shareTrainingObject;
    private boolean result;

    public ShareTrainingCommand(Entity e){
        this.shareTrainingObject = e;
    }
    public boolean getResult()
    {
        return this.result;
    }
    public void execute() throws ShareException {
        try {
            DaoTraining dao = DaoFactory.instanceDaoTraining(shareTrainingObject);
            result = dao.shareTraining(shareTrainingObject);
        }catch (ShareException ex){
            throw ex;
        }
    }
}
