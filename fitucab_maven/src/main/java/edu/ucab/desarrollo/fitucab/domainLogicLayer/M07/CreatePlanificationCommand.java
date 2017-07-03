package edu.ucab.desarrollo.fitucab.domainLogicLayer.M07;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M07.DaoPlanification;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

/**
 * Created by jaorr on 30/06/17.
 */
public class CreatePlanificationCommand extends Command {

    private Entity _planificationEntity;

    public CreatePlanificationCommand(Entity planificationEntity) {

        this._planificationEntity = planificationEntity;
    }



    public void execute() {
        DaoPlanification dao = DaoFactory.instanciateDaoPlanification();
        try {
            _planificationEntity = dao.create(_planificationEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
