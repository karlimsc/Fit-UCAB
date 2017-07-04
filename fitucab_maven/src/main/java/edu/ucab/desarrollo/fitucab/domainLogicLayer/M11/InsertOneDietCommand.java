package edu.ucab.desarrollo.fitucab.domainLogicLayer.M11;

import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M11.IDaoDiet;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created by JoseA2R on 2/7/17.
 */
public class InsertOneDietCommand extends Command {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(InsertOneDietCommand.class);

    Entity _diet;
    public Entity Respuesta;

    public InsertOneDietCommand(Entity diet) {
        _diet = diet;
    }

    @Override
    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException, SQLException, BdConnectException {
        /*Instancias DAO*/

        try{
        IDaoDiet Daodiet = DaoFactory.iniciarDaoDiet();
        Respuesta = Daodiet.insertOneDiet(_diet);
        } catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
        }

    }

    @Override
    public Entity Return() {
        return null;
    }
}
