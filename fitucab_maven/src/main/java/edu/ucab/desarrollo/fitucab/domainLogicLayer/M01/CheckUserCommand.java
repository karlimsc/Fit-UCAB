package edu.ucab.desarrollo.fitucab.domainLogicLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

/**
 * Created by estefania on 25/06/2017.
 */
public class CheckUserCommand extends Command{


    String _password;
    String _userS;
    Entity _user, _userReturn;


    /**
     * Contrunctor de la clase
     * @param password String
     * @param userS String
     */

    public CheckUserCommand(String password, String userS){
        this._password= password;
        this._userS = userS;
    }

    public Entity getUserLogin(){
        return _userReturn;
    }

    public CheckUserCommand(Entity user){
        this._user = user;
    }

    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException, Exception {
        try{
            //instanciacion del dao
            Dao LoginUserDao = DaoFactory.instanciateDaoUser(_user);
            _userReturn = LoginUserDao.read(_user);
           

        }
        catch(Exception e){

        }
    }

}
