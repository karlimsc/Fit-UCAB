package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M10.IDaoWater;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.sql.SQLException;


public class DeletWaterTmCommand extends Command {

    Entity _water;
    public String returned;

    public DeletWaterTmCommand (Entity water){ _water = water; };

    public void execute() {

        IDaoWater daoWater = DaoFactory.instanceDaoWater(_water);

        try {

            Water water = (Water) daoWater.deleteWaterTm(_water);
            Gson gson = new Gson();
            returned = gson.toJson(water);

        } catch (SQLException e) {

            e.printStackTrace();

        }

    }
    public Entity Return(){
        return null;
    }
}