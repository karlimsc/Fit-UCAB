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

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(LevelUpCommand.class);

    private Dao _dao;
    private static Entity _level;
    private int _userId;

    /**
     * Constructor que inicializa el dao, el id de un usario y la entidad challenge.
     * @param id Id del usuario.
     * @param dao Clase DaoGaming.
     * @see DaoGaming
     * @see edu.ucab.desarrollo.fitucab.common.entities.Challenge
     */
    public LevelUpCommand(int id, Dao dao) {
        _dao = dao;
        _level = EntityFactory.createChallenge();
        _userId = id;
    }

    /**
     * Metodo estatico que retorna la clase Challenge con scores aumentados.
     * @return
     */
    public static Entity getChallenge() {
        return _level;
    }

    /**
     * Metodo ejecutar heredado de commad.
     * @throws NoSuchMethodException
     * @throws Exception
     * @see DaoGaming
     */
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
