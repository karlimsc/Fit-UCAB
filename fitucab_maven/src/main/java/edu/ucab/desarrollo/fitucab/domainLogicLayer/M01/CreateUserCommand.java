package edu.ucab.desarrollo.fitucab.domainLogicLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
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

    Entity _user;
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);

    private static String DEFAULT_ENCODING1="UTF-8";

    public CreateUserCommand(Entity _user) {
        this._user = _user;
    }


    public void execute() throws Exception{

        try{
            //instanciacion del dao
            DaoUser createUserDao = DaoFactory.instanciateDaoUser(_user);
            createUserDao.create(_user);

        }
        catch(Exception e){
            //lanzar exception
            logger.error("Error", "La excepción es: " + e.getMessage());
        }
    }
}
