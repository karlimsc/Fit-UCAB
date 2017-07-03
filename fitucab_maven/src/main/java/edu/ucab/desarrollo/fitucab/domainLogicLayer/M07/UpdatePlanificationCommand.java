package edu.ucab.desarrollo.fitucab.domainLogicLayer.M07;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M07.DaoPlanification;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.sql.SQLException;

/**
 * Created by jaorr on 30/06/17.
 */
public class UpdatePlanificationCommand extends Command {
    @Override
    public Entity Return() throws SQLException, CreateHomeException, BdConnectException {
        return null;
    }

    private Entity _planificationEntity;

    public UpdatePlanificationCommand(Entity _planificationEntity) {
        this._planificationEntity = _planificationEntity;
    }

    public void execute() {
     // invocar metodo para actualizar
        DaoPlanification dao = DaoFactory.instanciateDaoPlanification();
        try {
            _planificationEntity = dao.update(_planificationEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
