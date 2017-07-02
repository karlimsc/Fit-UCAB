package edu.ucab.desarrollo.fitucab.domainLogicLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import org.slf4j.LoggerFactory;


public class CheckUserCommand extends Command{


    private static Entity _userReturn;
    private Entity _user;

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);



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
    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException, Exception {
        try{
            //instanciacion del dao

            DaoUser LoginUserDao = (DaoUser) DaoFactory.instanciateDaoUser(_user);
            _userReturn = LoginUserDao.read(_user);

        }
        catch(Exception e){

        }
    }

    public Entity Return(){
        return null;
    }

}
