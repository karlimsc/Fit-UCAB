package edu.ucab.desarrollo.fitucab.domainLogicLayer.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.exception.M09Exception;
import edu.ucab.desarrollo.fitucab.webService.M09_ServicesGamification;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Comando para traer la informacion de todos los retos
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class AchieveChallengeCommand extends Command {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);
    private List<Entity> _challenges;

    public AchieveChallengeCommand(List<Entity> challenges) {
        logger.error("_challenges1.1: ",_challenges);
        Entity challenge1 = EntityFactory.createChallenge(3,"name3","descripcion",5);
        _challenges = challenges;
        _challenges.add(challenge1);
        logger.error("_challenges1.2: ",_challenges);
    }

    //TODO: Falta execute
    public void execute() {
        try {
            Entity challenge1 = EntityFactory.createChallenge(1,"name1","descripcion",5);
            Entity challenge2 = EntityFactory.createChallenge(2,"name2","descripcion",2);
            _challenges.add(challenge1);
            _challenges.add(challenge2);
            logger.error("_challenges2: ",_challenges);
        }
        catch (Exception e){
            M09Exception error = new M09Exception(e.getMessage());
            logger.debug("Debug: ", error);
            logger.error("Error: ", error);
        }
    }

    @Override
    public List<Entity> getChallenges() {
        logger.error("_challenges3: ",_challenges);
        return _challenges;
    }
}
