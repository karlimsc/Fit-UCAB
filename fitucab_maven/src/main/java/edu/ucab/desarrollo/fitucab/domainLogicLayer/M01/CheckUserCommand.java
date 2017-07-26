package edu.ucab.desarrollo.fitucab.domainLogicLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M01.LoginUserException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;


public class CheckUserCommand extends Command{


    private static Entity _userReturn;
    private Entity _user;

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(CheckUserCommand.class);



    /**
     * Metodo para obtener el id del  usuario
     * @return Entity con la id
     */
    public static Entity getUserLogin(){
        return _userReturn;
    }

    /**
     * Constructor de la clase
     * @param user
     */
    public CheckUserCommand(Entity user){
        this._user = user;
    }


    /**
     * Metodo abtracto sobreescrito para este caso particula de verificacion de usuario
     * @throws ListAllException
     * @throws ListByIdException
     * @throws NoSuchMethodException
     * @throws Exception
     */


    public void execute() throws InstantiationException, NullPointerException,LoginUserException, SQLException{

            //instanciacion del dao
            DaoUser LoginUserDao = (DaoUser) DaoFactory.instanciateDaoUser(_user);

            _userReturn = LoginUserDao.login(_user);

    }

    public Entity Return(){
        return _userReturn;
    }

}
