package edu.ucab.desarrollo.fitucab.dataAccessLayer.M10;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;
import java.util.ArrayList;


public interface IDaoWater extends IDao{


    public Entity create(Entity e) throws SQLException;
    public ArrayList<Water> getList (Entity e)  throws  SQLException;
    public Entity getWater(Entity e)  throws  SQLException;
    public Entity deleteLast(Entity e)  throws  SQLException;


}
