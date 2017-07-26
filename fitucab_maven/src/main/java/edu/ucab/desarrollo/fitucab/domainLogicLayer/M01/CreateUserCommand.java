package edu.ucab.desarrollo.fitucab.domainLogicLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.M01.CreateUserException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created by karo on 24/06/17.
 */
public class CreateUserCommand extends Command {

    private Entity _user;
    private Boolean _response;
    private static Entity _userResponse;
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(CreateUserCommand.class);


    /**
     * Constructor de la clase
     * @param _user Entity
     */
    public CreateUserCommand(Entity _user) {
        this._user = _user;
        this._response = false;
    }

    public static Entity getUserRegistry(){
        return  _userResponse;
    }


    @Override
    public void execute()  throws NullPointerException, InstantiationException, CreateUserException, SQLException{

            Dao  _dao = DaoFactory.instanciateDaoUser(_user);
            DaoUser createUserDao;
            createUserDao = (DaoUser)_dao;

            _userResponse =  createUserDao.create(_user);

            this._response=true;

            logger.debug("Debug: ", "Realizó el Try en CreateUserCommand");

    }

    public Boolean get_response() {
        return _response;
    }

    public Entity Return(){
        return null;
    }
}
