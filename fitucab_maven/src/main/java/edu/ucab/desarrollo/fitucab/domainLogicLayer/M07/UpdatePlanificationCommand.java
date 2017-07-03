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
 * Clase UpdatePlanificationCommand para el manejo del Patron Comando
 * Este comando se encarga de actualizar las planificaciones asocidad a un usuario
 */
public class UpdatePlanificationCommand extends Command {

    private Entity _planificationEntity;
    private static org.slf4j.Logger _logger = LoggerFactory.getLogger(UpdatePlanificationCommand.class);

    public UpdatePlanificationCommand(Entity _planificationEntity) {
        this._planificationEntity = _planificationEntity;
    }

    /**
     * Metodo que se encarga de ejecutar las acciones correspondiente para
     * actualizar un registro en la base de datos
     */
    public void execute() {
        DaoPlanification dao = DaoFactory.instanciateDaoPlanification();
        try {
            _planificationEntity = dao.update(_planificationEntity);
        } catch (Exception e) {
            e.printStackTrace();
            _logger.error("Error en el comando para realizar una actualizacion en planification" +
                    ": " + e.toString());
            _planificationEntity.set_errorCode(500);
            _planificationEntity.set_errorMsg("Error durante la actualizacion");
        }
    }

    @Override
    public Entity Return() throws SQLException, CreateHomeException, BdConnectException {
        return null;
    }
}
