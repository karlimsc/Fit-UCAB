package edu.ucab.desarrollo.fitucab.domainLogicLayer.M07;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Planification;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M07.DaoPlanification;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.util.ArrayList;

/**
 * Created by jaorr on 30/06/17.
 */
public class GetPlanificationByIdCommand extends Command {

    private Entity _planificationEntity;
    private ArrayList<Planification> _listPlanification;

    public GetPlanificationByIdCommand(Entity planificationEntity) {
        this._planificationEntity = planificationEntity;
    }

    public ArrayList<Planification> get_listPlanification() {
        return _listPlanification;
    }

    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException {
        //invocar metodo que busca por id
        DaoPlanification dao = DaoFactory.instanciateDaoPlanification();
        try {
            _listPlanification = dao.getPlanificationByUser(_planificationEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
