package edu.ucab.desarrollo.fitucab.domainLogicLayer.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.Exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

/**
 * Comando para mostrar el aumento de nivel del jugador
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class LevelUpCommand extends Command {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(FillChartCommand.class);
    private Entity _level;

    public LevelUpCommand(Entity level) {
        _level = level;
    }

    //TODO: Falta execute
    public void execute() throws NoSuchMethodException {
        try{

        } catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error);
            logger.error("Error: ", error);
        }
    }
}
