package edu.ucab.desarrollo.fitucab.domainLogicLayer.M11;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M11.iDaoMoment;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created by charbel on 02/07/2017.
 */
public class MomentCommand extends Command {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(MomentCommand.class);

    Entity Moment;
    public Entity Respuesta;

    public MomentCommand (Entity e )
    {
        Moment = e ;
    }
    @Override
    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException, Exception {
       try{
        iDaoMoment DaoMoment= DaoFactory.iniciarDaoMoment();
        Respuesta=DaoMoment.read(Moment);
       } catch (Exception e){
           MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                   Thread.currentThread().getStackTrace()[1].getMethodName());
           logger.debug("Debug: ", error.toString());
           logger.error("Error: ", error.toString());
       }

    }


    @Override
    public Entity Return() throws SQLException, CreateHomeException, BdConnectException {



        return   null;
    }
}
