package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M10.IDaoWater;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 */
public class GetListDateCommand extends Command {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(GetListDateCommand.class);
    Entity _water;
    public String returned;

    /**
     * constructor de getlistdate
     * @param water
     */
    public GetListDateCommand (Entity water){ _water = water; };

    public void execute() {

        logger.debug("Debug: Obteniendo lista->Comando");

        Gson gson = new Gson();
        IDaoWater daoWater = DaoFactory.instanceDaoWater(_water);

        try {




            ArrayList<Water> waterList = daoWater.getList(_water);
            returned = gson.toJson(waterList) ;

        } catch (SQLException e) {

            e.printStackTrace();

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

