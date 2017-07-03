package edu.ucab.desarrollo.fitucab.domainLogicLayer.M03;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.UserAuxiliar;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M03.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * Created by danie on 3/7/2017.
 */
public class NearMeCommand extends Command {
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(NearMeCommand.class);
    Entity _nearMe;
    public String returned;
    private String _id;
    private String _latitud;
    private String _longitud;
    private String _rango;

    public NearMeCommand(String id, String longitud, String latitud, String rango) {
        _id = id;
        _latitud = latitud;
        _longitud = longitud;
        _rango = rango;
    }

    public void execute() throws NoSuchMethodException {
        IDaoNearMe daoNearMe = DaoFactory.instanceDaoNearMe(_nearMe);
        try {

            ArrayList<UserAuxiliar> resultado = daoNearMe.getNearMe(_id, _longitud, _latitud, _rango);
            Gson gson = new Gson();
            returned = gson.toJson(resultado);
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
