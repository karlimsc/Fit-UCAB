package edu.ucab.desarrollo.fitucab.dataAccessLayer.M11;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Food;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import javax.xml.stream.events.EntityDeclaration;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by charbel on 29/06/2017.
 */
public interface IDaoFood extends IDao {

    void Create(Entity e);

    public ArrayList<Food> getFoodPer(Entity e) throws SQLException;

}
