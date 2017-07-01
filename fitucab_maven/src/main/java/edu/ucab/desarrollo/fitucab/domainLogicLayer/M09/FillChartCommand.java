package edu.ucab.desarrollo.fitucab.domainLogicLayer.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

/**
 * Comando para traer informacion que llena la grafica con los retos logrados y no logrados.
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class FillChartCommand extends Command{

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(FillChartCommand.class);

    private Dao _dao;
    private Entity _challenge;
    private int _userId;


    public FillChartCommand(int userId, Dao dao) {
        _dao = dao;
        _challenge = EntityFactory.createChallenge();
        _userId = userId;
    }

    public Entity getChallenge() {
        return _challenge;
    }

    //TODO: Falta execute
    public void execute() throws NoSuchMethodException {
        try{
            _challenge = _dao.fillChart(_userId);
        } catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
        }
    }



}
