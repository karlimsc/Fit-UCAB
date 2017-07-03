package edu.ucab.desarrollo.fitucab.domainLogicLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.*;

/**
 * Created by cesareduardo on 02/07/2017.
 */
public class UpdateUserCommand extends Command {

    int _id;
    String _username;
    String _phone;
    String _email;
    boolean _update;
    final static org.slf4j.Logger _logger = LoggerFactory.getLogger(HomeCommand.class);

    public UpdateUserCommand(int id,String username,String phone,String email){
        this._id=id;
        this._username=username;
        this._phone=phone;
        this._email=email;
    }

    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException {

        IDaoUser update = DaoFactory.instanceDaoUpdateUser(_id,_username,_phone,_email);
        try {
            _update = update.Update();
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
