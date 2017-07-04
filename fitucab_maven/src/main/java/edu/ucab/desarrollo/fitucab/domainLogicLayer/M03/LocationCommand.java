package edu.ucab.desarrollo.fitucab.domainLogicLayer.M03;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M03.DaoNearMe;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

/**
 * Created by danie on 3/7/2017.
 */
public class LocationCommand extends Command {
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(LocationCommand.class);
    private Dao _dao;
    private String _id;
    private String _latitud;
    private String _longitud;

    public LocationCommand(Dao dao, String id, String longitud, String latitud) {
        _dao = dao;
        _id = id;
        _latitud = latitud;
        _longitud = longitud;
    }

    public void execute() throws NoSuchMethodException {
        try {
            ((DaoNearMe) _dao).setLocation(_id, _longitud, _latitud);
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
        }
    }

    public Entity Return(){
        return null;
    }


}
