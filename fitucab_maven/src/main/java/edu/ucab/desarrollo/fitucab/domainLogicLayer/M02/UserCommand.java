package edu.ucab.desarrollo.fitucab.domainLogicLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.GetUserException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.IDaoUser;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.common.M02Cache;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static edu.ucab.desarrollo.fitucab.common.M02Cache._mapUser;

/**
 * Clase UserCommand que hereda de clase Command para el manejo de la Entidad User
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 4.0
 */
public class UserCommand extends Command {
    Entity _home;
    int _id;


    final static org.slf4j.Logger _logger = LoggerFactory.getLogger(UserCommand.class);

    /**
     * Constructor de la clase que solo obtiene el parametro _id
     * @param _id valor para identificar al usuario en BD
     */
    public UserCommand(int _id){
        this._id =_id;
    }

    /**
     * Sobreescritura del metodo execute, heredado de la clase Command
     * para el manejo de la Entidad User
     * @throws MessageException
     */
    @Override
    public void execute() {
        M02Cache _mapUser = new M02Cache();
        IDaoUser _user = DaoFactory.instanceDaoUser(_id);
        try {
            if (_mapUser.searchUser(_id)==true){
                _home= (_mapUser.getUser(_id));
            }
            else {
                _home = _user.read(_id);
                _mapUser.llenar(_id, _home);
            }
        } catch (GetUserException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            _logger.debug("Debug: ", error);
            _logger.error("Error: ", error);
        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            _logger.debug("Debug: ", error);
            _logger.error("Error: ", error);
        }
    }

    /**
     *Metodo que regresa un objeto del tipo Home
     * @return _home
     */
    public Entity Return(){
        return _home;
    }

    public int get_id() {
        return _id;
    }
}
