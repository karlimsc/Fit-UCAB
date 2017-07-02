package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M10.IDaoWater;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;


public class GetFechaIntCommand extends Command {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(GetFechaIntCommand.class);
    Entity _water;
    public String returned;

    public GetFechaIntCommand (Entity water){ _water = water; };

    public void execute() {

        IDaoWater daoWater = DaoFactory.instanceDaoWater(_water);

        try {

            Water water = (Water) daoWater.getFechaInt(_water);

            returned = water.get_suma()+","+water.get_cantidad();

        } catch (SQLException e) {

            e.printStackTrace();

            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());

        }


    }
}