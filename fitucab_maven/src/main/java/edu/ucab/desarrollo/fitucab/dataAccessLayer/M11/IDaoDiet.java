package edu.ucab.desarrollo.fitucab.dataAccessLayer.M11;

import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;

/**
 * Created by JoseA2R on 2/7/17.
 */
public interface IDaoDiet extends IDao {

    void Create(Entity e);

    public Entity GetCaloriesDate (Entity e) throws SQLException, BdConnectException;

    public Entity deleteDiet (Entity e) throws SQLException, BdConnectException;

    public Entity getMomentFood (Entity e) throws SQLException, BdConnectException;

    public Entity getCaloriesConsumedDay (Entity e) throws SQLException, BdConnectException;

    public Entity getCaloriesConsumedWeek (Entity e) throws SQLException, BdConnectException;

    public Entity getCaloriesConsumedMonth (Entity e) throws SQLException, BdConnectException;

    public Entity insertDiet (Entity e) throws SQLException, BdConnectException;

    public Entity insertOneDiet (Entity e) throws SQLException, BdConnectException;

}
