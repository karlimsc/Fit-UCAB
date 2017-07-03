package edu.ucab.desarrollo.fitucab.domainLogicLayer.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M09.DaoGaming;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

/**
 * Comando para mostrar el aumento de nivel del jugador
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class LevelUpCommand extends Command {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(FillChartCommand.class);

    private Dao _dao;
    private static Entity _level;
    private int _userId;

    public LevelUpCommand(int id, Dao dao) {
        _dao = dao;
        _level = EntityFactory.createChallenge();
        _userId = id;
    }

    public static Entity getChallenge() {
        return _level;
    }

    //TODO: Falta execute
    public void execute() throws NoSuchMethodException {
        try{
            _level = ((DaoGaming) _dao).levelUp(_userId);
        } catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
        }
    }

    public Entity Return(){
        return null;
    }

}
