package edu.ucab.desarrollo.fitucab.domainLogicLayer.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

/**
 * Comando para traer la puntuacion de todos los retos
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class ScoreCommand extends Command{

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(ScoreCommand.class);

    private Dao _dao;
    private Entity _score;
    private int _userId;

    public ScoreCommand(int id, Dao dao) {
        _dao = dao;
        _score = EntityFactory.createChallenge();
        _userId = id;
    }

    public Entity getChallenge() {
        return _score;
    }

    //TODO: Falta execute
    public void execute() throws NoSuchMethodException {
        try{
            _score = _dao.score(_userId);
        }
        catch (Exception e){
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
