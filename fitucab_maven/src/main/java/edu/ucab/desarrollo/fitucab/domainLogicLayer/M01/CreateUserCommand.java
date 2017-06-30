package edu.ucab.desarrollo.fitucab.domainLogicLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import org.slf4j.LoggerFactory;

/**
 * Created by karo on 24/06/17.
 */
public class CreateUserCommand extends Command {

    Entity _user;
    Boolean _retorn;
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);


    /**
     * Constructor de la clase
     * @param _user Entity
     */
    public CreateUserCommand(Entity _user) {
        this._user = _user;
    }


    @Override
    public void execute()  throws NullPointerException, InstantiationException{

        try{

            DaoUser createUserDao = DaoFactory.instanciateDaoUser(_user);
            createUserDao.create(_user);

            logger.debug("Debug: ", "Realizó el Try en CreateUserCommand");


        }
        catch (NullPointerException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error);
        }catch (InstantiationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error);
        }
        catch(Exception e){
            //lanzar exception
            logger.error("Error", "La excepción es: " + e.getMessage());
        }
    }
}
