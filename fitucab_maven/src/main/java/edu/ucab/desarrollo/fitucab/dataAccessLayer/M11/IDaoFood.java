package edu.ucab.desarrollo.fitucab.dataAccessLayer.M11;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;

/**
 * Created by charbel on 29/06/2017.
 */
public interface IDaoFood extends IDao {

    void Create(Entity e);

    public Entity getFoodPer(Entity e) throws SQLException, BdConnectException;

    public  Entity getFoodAll (Entity e) throws SQLException, BdConnectException;

    public Entity getSugge (Entity e ) throws SQLException, BdConnectException;

    public Entity getFoodAuto (Entity e ) throws SQLException, BdConnectException;

    public Entity DeletPerFood (Entity e ) throws SQLException, BdConnectException;

    public Entity updatePerso (Entity e ) throws SQLException, BdConnectException;

    public  Entity InsertarAlimen (Entity e)  throws SQLException, BdConnectException;

    public Entity insertarPersoFood (Entity e)  throws SQLException, BdConnectException;

    public Entity getPersonalizedLis (Entity e ) throws BdConnectException, SQLException;


}
