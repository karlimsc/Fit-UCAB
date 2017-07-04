package edu.ucab.desarrollo.fitucab.domainLogicLayer.M09;

import edu.ucab.desarrollo.fitucab.common.Registry;
import edu.ucab.desarrollo.fitucab.common.entities.Challenge;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M09.DaoGaming;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

import static edu.ucab.desarrollo.fitucab.common.Registry.*;

/**
 * Comando para traer la puntuacion de todos los retos
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class ScoreCommand extends Command{

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(ScoreCommand.class);

    private Dao _dao;
    private static Entity _score;
    private int _userId;
    private int _level;

    /**
     * Constructor que inicializa el dao, el id de un usario y la entidad challenge.
     * @param id Id del usuario.
     * @param dao Clase DaoGaming.
     * @see DaoGaming
     * @see edu.ucab.desarrollo.fitucab.common.entities.Challenge
     */
    public ScoreCommand(int id, Dao dao) {
        _dao = dao;
        _score = EntityFactory.createChallenge();
        _userId = id;
        _level = 0;
    }

    /**
     * Metodo estatico que retorna la clase Challenge con el nivel de un reto.
     * @return
     */
    public static Entity getChallenge() {
        return _score;
    }

    /**
     * Metodo ejecutar heredado de commad.
     * @throws NoSuchMethodException
     * @throws Exception
     * @see DaoGaming
     */
    public void execute() throws NoSuchMethodException {
        try{
            _score = ((DaoGaming) _dao).score(_userId);
            _level = ((Challenge) _score).getScore();
            if (_level < _level1){
                ((Challenge) _score).setLevel(1);
            }
            else if (_level <  _level2 ){
                ((Challenge) _score).setLevel(2);
            }
            else if (_level <  _level3 ){
                ((Challenge) _score).setLevel(3);
            }
            else if (_level <  _level4 ){
                ((Challenge) _score).setLevel(4);
            }
            else if (_level <  _level5 ){
                ((Challenge) _score).setLevel(5);
            }
            else if (_level <  _level6 ){
                ((Challenge) _score).setLevel(6);
            }
            else if (_level <  _level7 ){
                ((Challenge) _score).setLevel(7);
            }
            else if (_level <  _level8 ){
                ((Challenge) _score).setLevel(8);
            }
            else if (_level <  _level9 ){
                ((Challenge) _score).setLevel(9);
            }
            else {
                ((Challenge) _score).setLevel(10);
            }
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
