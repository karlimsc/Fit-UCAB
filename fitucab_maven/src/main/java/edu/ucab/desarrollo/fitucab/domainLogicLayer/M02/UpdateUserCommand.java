package edu.ucab.desarrollo.fitucab.domainLogicLayer.M02;

import edu.ucab.desarrollo.fitucab.common.M02Cache;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.*;

/**
 * Clase UpdateUserCommand para el manejo del comando de actualizar la entidad Usuario
 * @author Juan Macedo, Cesar Boza, Bryan Teixeira
 * @version 3.0
 */
public class UpdateUserCommand extends Command {

    int _id;
    String _username;
    String _phone;



    String _email;
    boolean _update;
    private Entity _userC;
    final static org.slf4j.Logger _logger = LoggerFactory.getLogger(HomeCommand.class);

    public UpdateUserCommand(int id,String username,String phone,String email){
        this._id=id;
        this._username=username;
        this._phone=phone;
        this._email=email;
    }

    public int get_id() {
        return _id;
    }

    public String get_username() {
        return _username;
    }

    public String get_phone() {
        return _phone;
    }

    public String get_email() {
        return _email;
    }



    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException {

        M02Cache _mapUser = new M02Cache();
        IDaoUser update = DaoFactory.instanceDaoUpdateUser(_id,_username,_phone,_email);
        IDaoUser _user = DaoFactory.instanceDaoUser(_id);
        try {
            _userC = _user.read(_id);
            _update = update.update();
            _mapUser.update(_id,_username,_phone,_email,_userC);
        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            _logger.debug("Debug: ", error);
            _logger.error("Error: ", error);
        }
    }


    public Entity Return() {
        return null;
    }

    public Boolean ReturnUpdate() {
        return _update;
    }
}
