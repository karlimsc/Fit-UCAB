package edu.ucab.desarrollo.fitucab.domainLogicLayer.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M09.DaoGaming;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M09.IDaoGaming;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Comando para traer la informacion de todos los retos
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class AchieveChallengeCommand extends Command {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);

    private List<Entity> _challenges;
    private int _userId;

    public AchieveChallengeCommand(int id) {
        _challenges = CommandsFactory.getChallenges();
        _userId = id;
    }

    public List<Entity> getChallenges() {
        return _challenges;
    }

    //TODO: Falta execute
    public void execute() throws NoSuchMethodException {
        try {
            DaoGaming dao = DaoFactory.instanceDaoGaming();
            dao.achieveChallenge(_userId, _challenges);
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
        }
    }
}