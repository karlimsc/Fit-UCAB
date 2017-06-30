package edu.ucab.desarrollo.fitucab.domainLogicLayer.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Comando para traer la informacion de todos los retos
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class AchieveChallengeCommand extends Command {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);

    private List<Entity> _challenges;
    private int _id;

    public AchieveChallengeCommand(int id) {
        _challenges = CommandsFactory.getChallenges();
        _id = id;
    }

    public List<Entity> getChallenges() {
        return _challenges;
    }

    //TODO: Falta execute
    public void execute() throws NoSuchMethodException {
        try {
            //Instancio Dao.
            //Ejecuto el AchieveChallengeCommandDao con el id
            Entity challenge1 = EntityFactory.createChallenge(1,"nombre1","descripcion",3);
            Entity challenge2 = EntityFactory.createChallenge(2,"nombre1","descripcion",4);
            Entity challenge3 = EntityFactory.createChallenge(3,"nombre1","descripcion",5);
            Entity challenge4 = EntityFactory.createChallenge(4,"nombre1","descripcion",6);
            _challenges.add(challenge1);
            _challenges.add(challenge2);
            _challenges.add(challenge3);
            _challenges.add(challenge4);
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
        }
    }

    public boolean run() throws NoSuchMethodException, Exception {
        return false;
    }
}