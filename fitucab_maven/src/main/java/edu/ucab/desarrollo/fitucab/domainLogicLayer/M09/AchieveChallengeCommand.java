package edu.ucab.desarrollo.fitucab.domainLogicLayer.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M09.DaoGaming;
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

    private Dao _dao;
    private static List<Entity> _challenges;
    private int _userId;

    /**
     * Constructor que inicializa el dao, el id de un usario y la lista de retos.
     * @param id Id del usuario.
     * @param dao Clase DaoGaming.
     * @see DaoGaming
     * @see edu.ucab.desarrollo.fitucab.common.entities.Challenge
     */
    public AchieveChallengeCommand(int id, Dao dao) {
        _dao = dao;
        _challenges = EntityFactory.getChallenges();
        _userId = id;
    }

    /**
     * Metodo estatico que retorna la lista de retos hechos por un usuario.
     * @return
     * @see List
     * @see edu.ucab.desarrollo.fitucab.common.entities.Challenge
     */
    public static List<Entity> getChallenges() {
        return _challenges;
    }

    /**
     * Metodo ejecutar heredado de commad.
     * @throws NoSuchMethodException
     * @throws Exception
     * @see DaoGaming
     */
    public void execute() throws NoSuchMethodException {
        try {
            ((DaoGaming) _dao).achieveChallenge(_userId, _challenges);
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