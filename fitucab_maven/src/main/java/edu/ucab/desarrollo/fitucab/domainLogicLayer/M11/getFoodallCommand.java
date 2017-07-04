package edu.ucab.desarrollo.fitucab.domainLogicLayer.M11;

import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M11.IDaoFood;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created by charbel on 30/06/2017.
 */
public class getFoodallCommand extends Command {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(getFoodallCommand.class);

    Entity _food;
    public Entity Respuesta ;
    boolean ans;

    public getFoodallCommand(Entity food) {_food =food; }


    @Override
    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException, SQLException, BdConnectException {

        try{
        IDaoFood Daofood = DaoFactory.iniciarDaoFood();
        Respuesta = Daofood.getFoodAll(_food);

    } catch (Exception e){
        MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                Thread.currentThread().getStackTrace()[1].getMethodName());
        logger.debug("Debug: ", error.toString());
        logger.error("Error: ", error.toString());
    }
    }

    public boolean answer(){
        return this.ans;
    }

    @Override
    public Entity Return() {
        return null;
    }
}
