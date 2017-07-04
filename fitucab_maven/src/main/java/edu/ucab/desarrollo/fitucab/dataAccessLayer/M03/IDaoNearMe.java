package edu.ucab.desarrollo.fitucab.dataAccessLayer.M03;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.UserAuxiliar;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by danie on 3/7/2017.
 */
public interface IDaoNearMe extends IDao {
    public Entity create(Entity e) throws SQLException;
    public ArrayList<UserAuxiliar> getNearMe(String id, String longitud, String latitud, String rango)  throws  SQLException;
    public void setLocation(String id, String longitud, String latitud);

}
