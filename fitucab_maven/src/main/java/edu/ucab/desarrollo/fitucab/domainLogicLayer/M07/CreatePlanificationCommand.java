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
 * Clase CreatePlanificationCommand para el manejo del Patron Comando
 * Este comando se encarga de crear un nuevo de registro
 */
public class CreatePlanificationCommand extends Command {

    private Entity _planificationEntity;

    public CreatePlanificationCommand(Entity planificationEntity) {

        this._planificationEntity = planificationEntity;
    }


    /**
     * Metodo que se encarga de ejecutar las acciones correspondiente para
     * insertar en la base de datos
     */
    public void execute() {
        DaoPlanification dao = DaoFactory.instanciateDaoPlanification();
        try {
            _planificationEntity = dao.create(_planificationEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public Entity Return() throws SQLException, CreateHomeException, BdConnectException {
        return null;
    }
}
