package edu.ucab.desarrollo.fitucab.domainLogicLayer.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.exception.M09Exception;
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
    public void execute() {
        try{

        } catch (Exception e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.debug("Debug: ", error);
            logger.error("Error: ", error);
        }
    }
}
