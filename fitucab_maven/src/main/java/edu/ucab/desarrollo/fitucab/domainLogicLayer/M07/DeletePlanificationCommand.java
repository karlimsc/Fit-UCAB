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
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Clase DeletePlanificationCommand para el manejo del Patron Comando
 * Este comando se encarga de eliminar un registro
 */
public class DeletePlanificationCommand extends Command {

    private Entity _planificationEntity;
    private static org.slf4j.Logger _logger = LoggerFactory.getLogger(DeletePlanificationCommand.class);

    public DeletePlanificationCommand(Entity _planificationEntity) {

        this._planificationEntity = _planificationEntity;
    }

    /**
     * Metodo que se encarga de ejecutar las acciones correspondiente para
     * eliminar un registro en la base de datos
     */
    public void execute() {
        // invocar metodo delete

        DaoPlanification dao = DaoFactory.instanciateDaoPlanification();
        try {
            _planificationEntity = dao.delete(_planificationEntity);
        } catch (Exception e) {
            _logger.error("Error en el comando para realizar una eliminacion en planification" +
                    ": " + e.toString());
            _planificationEntity.set_errorCode(500);
            _planificationEntity.set_errorMsg("Error durante la eliminacion");
        }
    }

    @Override
    public Entity Return() throws SQLException, CreateHomeException, BdConnectException {
        return null;
    }
}
