package edu.ucab.desarrollo.fitucab.domainLogicLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

/**
 * Created by estefania on 25/06/2017.
 */
public class CheckUserCommand extends Command{


    String _password;
    String _userS;
    Entity _user, _userRetorn;


    /**
     * Contrunctor de la clase
     * @param password String
     * @param userS String
     */

    public CheckUserCommand(String password, String userS){
        this._password= password;
        this._userS = userS;
    }

    public CheckUserCommand(Entity user){
        this._user = user;
    }

    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException, Exception {

    }

    public Entity run() throws NoSuchMethodException, Exception {
        try{
            //instanciacion del dao
            DaoUser LoginUserDao = DaoFactory.instanciateDaoUser(_user);
            _userRetorn = LoginUserDao.create(_user);
            // Si el usuario que retorna es null es que hubo un error en la insercion
            return _userRetorn;

        }
        catch(Exception e){
            //lanzar exception
           // logger.error("Error", "La excepción es: " + e.getMessage());
            return null;
        }
    }
}
