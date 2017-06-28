package edu.ucab.desarrollo.fitucab.domainLogicLayer.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.exception.M09Exception;
import edu.ucab.desarrollo.fitucab.webService.M09_ServicesGamification;
import org.slf4j.LoggerFactory;

/**
 * Comando para traer la puntuacion de todos los retos
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class ScoreCommand extends Command{
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(ScoreCommand.class);
    private Entity _score;

    public ScoreCommand(Entity score) {
        _score = score;
    }

    //TODO: Falta execute
    public void execute() {
        try{

        }
        catch (Exception e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.debug("Debug: ", error);
            logger.error("Error: ", error);
        }
    }
}
