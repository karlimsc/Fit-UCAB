package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M10.IDaoWater;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.sql.SQLException;


public class GetWaterCommand extends Command {

    Entity _water;
    public String returned;

    public GetWaterCommand (Entity water){ _water = water; };

    public void execute() {



        IDaoWater daoWater = DaoFactory.instanceDaoWater(_water);

        try {

            Water water = (Water) daoWater.getWater(_water);

            returned = water.get_suma()+","+water.get_cantidad();

        } catch (SQLException e) {

            e.printStackTrace();

        }




    }
}