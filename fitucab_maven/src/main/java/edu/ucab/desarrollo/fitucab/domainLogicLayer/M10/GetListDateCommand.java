package edu.ucab.desarrollo.fitucab.domainLogicLayer.M10;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M10.IDaoWater;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 */
public class GetListDateCommand extends Command {

    Entity _water;
    public String returned;

    /**
     * constructor de getlistdate
     * @param water
     */
    public GetListDateCommand (Entity water){ _water = water; };

    public void execute() {
        Gson gson = new Gson();
        IDaoWater daoWater = DaoFactory.instanceDaoWater(_water);

        try {




            ArrayList<Water> waterList = daoWater.getList(_water);
            returned = gson.toJson(waterList) ;

        } catch (SQLException e) {

            e.printStackTrace();

        }



    }
}

