package edu.ucab.desarrollo.fitucab.domainLogicLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.GetUserException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M02.IDaoHome;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M02.IDaoUser;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

/**
 * Created by root on 29/06/17.
 */
public class HomeCommand extends Command {
    Entity _home, _usuario;
    int id;
  final static org.slf4j.Logger _logger = LoggerFactory.getLogger(HomeCommand.class);

    public HomeCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute() {
        try {
            IDaoUser usuario = DaoFactory.instanceDaoUser(id);
            _usuario = usuario.read(id);
            IDaoHome home = DaoFactory.instanceDaoHome(_usuario);
            _home = home.read(_usuario);
        } catch (CreateHomeException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            _logger.debug("Debug: ", error);
            _logger.error("Error: ", error);
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

    public Entity Return(){
            return _home;
    }
}
