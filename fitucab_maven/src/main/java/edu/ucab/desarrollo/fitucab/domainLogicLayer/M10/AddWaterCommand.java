package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;

import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M10.IDaoWater;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Comando para agregar agua que extiende de command
 */

public class AddWaterCommand extends Command {

    Entity _water;
    public String returned;

    /**
     * constructor de agregar agua
     * @param water
     */
    public AddWaterCommand (Entity water){ _water = water; };

    public void execute() {

        IDaoWater daoWater = DaoFactory.instanceDaoWater(_water);
        try {
            Water water = (Water) daoWater.addWater(_water);
            returned = water.get_fkPerson().toString();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //returned = "HOLA BEBE";
    }
}
