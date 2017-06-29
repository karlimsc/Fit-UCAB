package edu.ucab.desarrollo.fitucab.dataAccessLayer.M10;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Raul A on 6/28/2017.
 */
public interface IDaoWater extends IDao{

    public Entity addWater(Entity e) throws SQLException;
    public ArrayList<Water> getList (Entity e);
    public Entity getWater(Entity e);

}
