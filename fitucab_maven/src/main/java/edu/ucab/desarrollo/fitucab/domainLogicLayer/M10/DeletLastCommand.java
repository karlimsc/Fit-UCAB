package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M10.IDaoWater;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.sql.SQLException;

/**
 * Comando para eliminar al ultimo que extiende de command
 */
public class DeletLastCommand extends Command {

    Entity _water;
    public String returned;

    /**
     * constructor de deletcommand
     * @param water
     */
    public DeletLastCommand (Entity water){ _water = water; };

    public void execute() {

        IDaoWater daoWater = DaoFactory.instanceDaoWater(_water);

        try {

            Water water = (Water) daoWater.deleteLast(_water);
            Gson gson = new Gson();
            returned = gson.toJson(water);

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
}

