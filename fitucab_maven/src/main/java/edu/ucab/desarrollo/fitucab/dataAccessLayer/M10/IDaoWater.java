package edu.ucab.desarrollo.fitucab.dataAccessLayer.M10;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;

/**
 * Created by Raul A on 6/28/2017.
 */
public interface IDaoWater extends IDao{

    public Water addWater(Entity e) throws SQLException;
}
