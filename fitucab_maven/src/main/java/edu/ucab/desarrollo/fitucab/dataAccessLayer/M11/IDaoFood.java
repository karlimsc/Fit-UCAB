package edu.ucab.desarrollo.fitucab.dataAccessLayer.M11;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;

/**
 * Created by charbel on 29/06/2017.
 */
public interface IDaoFood extends IDao {

    void Create(Entity e);

    public String getFoodPer(Entity e) throws SQLException;

    public  String getFoodAll (Entity e) throws SQLException;
}
