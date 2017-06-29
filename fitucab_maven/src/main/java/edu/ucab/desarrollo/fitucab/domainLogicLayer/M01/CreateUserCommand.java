package edu.ucab.desarrollo.fitucab.domainLogicLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

/**
 * Created by karo on 24/06/17.
 */
public class CreateUserCommand extends Command {

    Entity _user;
    private static String DEFAULT_ENCODING1="UTF-8";

    public CreateUserCommand(Entity _user) {
        this._user = _user;
    }


    public void execute() {

        try{
            //instanciacion del dao
            DaoUser createUserDao = DaoFactory.instanciateDaoUser(_user);
            createUserDao.create(_user);

        }
        catch(Exception e){
            //lanzar exception
        }
    }
}
