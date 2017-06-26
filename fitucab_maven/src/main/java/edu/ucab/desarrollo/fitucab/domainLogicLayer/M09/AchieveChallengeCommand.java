package edu.ucab.desarrollo.fitucab.domainLogicLayer.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.exception.M09Exception;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Comando para traer la informacion de todos los retos
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class AchieveChallengeCommand extends Command {

    private static Logger logger = Logger.getLogger("AchieveChallengeCommand.class");
    private Entity _challenge;

    public AchieveChallengeCommand(Entity challenge) {
        _challenge = challenge;
    }

    public void execute() {
        try {

        }
        catch (Exception e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.log(Level.ALL, "AchieveChallengesCommandExceltion!",error);
        }
    }
}
