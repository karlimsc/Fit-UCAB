package edu.ucab.desarrollo.fitucab.dataAccessLayer.M11;

import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;

/**
 * Created by JoseA2R on 2/7/17.
 */
public interface IDaoDiet extends IDao {

    void Create(Entity e);

    public String GetCaloriesDate (Entity e) throws SQLException, BdConnectException;

    public String DeleteDiet (Entity e) throws SQLException, BdConnectException;

    public String GetMomentFood (Entity e) throws SQLException, BdConnectException;

    public String GetCaloriesConsumedDay (Entity e) throws SQLException, BdConnectException;

    public String GetCaloriesConsumedWeek (Entity e) throws SQLException, BdConnectException;

    public String GetCaloriesConsumedMonth (Entity e) throws SQLException, BdConnectException;

    public String InsertDiet (Entity e) throws SQLException, BdConnectException;

    public String InsertOneDiet (Entity e) throws SQLException, BdConnectException;

}
