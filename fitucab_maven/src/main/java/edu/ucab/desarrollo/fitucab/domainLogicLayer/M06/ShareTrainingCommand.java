package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

public class ShareTrainingCommand extends Command {
    private Entity shareTrainingObject;

    public ShareTrainingCommand(Entity e){
        this.shareTrainingObject = e;
    }
    public void execute() {
        DaoTraining dao = DaoFactory.instanceDaoTraining(shareTrainingObject);
        dao.shareTraining(shareTrainingObject);
    }
}
