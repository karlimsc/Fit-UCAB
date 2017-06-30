package edu.ucab.desarrollo.fitucab.domainLogicLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import org.slf4j.LoggerFactory;

/**
 * Created by karo on 24/06/17.
 */
public class CreateUserCommand extends Command {

    Entity _user, _userRetorn;
    Boolean _retorn;
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);


    /**
     * Constructor de la clase
     * @param _user Entity
     */
    public CreateUserCommand(Entity _user) {
        this._user = _user;
    }


    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException, Exception {

    }

    public Entity run() throws Exception{

        try{
            //instanciacion del dao
            DaoUser createUserDao = DaoFactory.instanciateDaoUser(_user);
            _userRetorn = createUserDao.create(_user);
            // Si el usuario que retorna es null es que hubo un error en la insercion
             return _userRetorn;

        }
        catch(Exception e){
            //lanzar exception
            logger.error("Error", "La excepción es: " + e.getMessage());
            return null;
        }
    }
}
