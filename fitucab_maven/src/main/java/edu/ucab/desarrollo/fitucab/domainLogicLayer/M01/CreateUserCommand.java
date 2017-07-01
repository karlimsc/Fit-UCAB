package edu.ucab.desarrollo.fitucab.domainLogicLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
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
    Boolean _response;
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);


    /**
     * Constructor de la clase
     * @param _user Entity
     */
    public CreateUserCommand(Entity _user) {
        this._user = _user;
        this._response = false;
    }


    @Override
    public void execute()  throws NullPointerException, InstantiationException{

        try{

            Dao  _dao = DaoFactory.instanciateDaoUser(_user);
            DaoUser createUserDao;
            createUserDao = (DaoUser)_dao;

            createUserDao.create(_user);
            this._response=true;

            logger.debug("Debug: ", "Realizó el Try en CreateUserCommand");


        }
        catch (NullPointerException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error);
            this._response = false;
        }catch (InstantiationException e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error);
            this._response = false;
        }
        catch(Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error);
            this._response = false;
        }
    }

    public Boolean get_response() {
        return _response;
    }
}
