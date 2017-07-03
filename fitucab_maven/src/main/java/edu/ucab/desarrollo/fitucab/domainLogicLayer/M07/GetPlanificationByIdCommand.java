package edu.ucab.desarrollo.fitucab.domainLogicLayer.M07;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Planification;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M07.DaoPlanification;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Clase GetPlanificationByIdCommand para el manejo del Patron Comando
 * Este comando se encarga de buscar las planificaciones asocidad a un usuario
 */
public class GetPlanificationByIdCommand extends Command {

    private Entity _planificationEntity;
    private ArrayList<Planification> _listPlanification;
    private static org.slf4j.Logger _logger = LoggerFactory.getLogger(GetPlanificationByIdCommand.class);

    public GetPlanificationByIdCommand(Entity planificationEntity) {

        this._planificationEntity = planificationEntity;

    }

    public ArrayList<Planification> get_listPlanification() {
        return _listPlanification;
    }

    /**
     * Metodo que se encarga de ejecutar las acciones correspondiente para
     * buscar un registro en la base de datos
     */
    public void execute() {
        DaoPlanification dao = DaoFactory.instanciateDaoPlanification();
        try {
            _listPlanification = dao.getPlanificationByUser(_planificationEntity);
        } catch (Exception e) {
            e.printStackTrace();
            _logger.error("Error en el comando para realizar una busqueda en planification" +
                    ": " + e.toString());
            _planificationEntity.set_errorCode(500);
            _planificationEntity.set_errorMsg("Error durante la busqueda");
        }
    }

    @Override
    public Entity Return() throws SQLException, CreateHomeException, BdConnectException {
        return null;
    }
}
