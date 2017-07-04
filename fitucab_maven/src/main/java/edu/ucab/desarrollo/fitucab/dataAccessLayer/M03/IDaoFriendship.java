package edu.ucab.desarrollo.fitucab.dataAccessLayer.M03;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Friendship;
import edu.ucab.desarrollo.fitucab.common.entities.UserAuxiliar;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by danie on 2/7/2017.
 */
public interface IDaoFriendship extends IDao{

    public Entity create(Entity e) throws SQLException;
    public void requestFriendship(String userOne, String userTwo);
    public void updateFriendship(String updater, String updated, String action);
    public ArrayList<UserAuxiliar> getList (String id, String Action)  throws  SQLException;
    public ArrayList<UserAuxiliar> getContacts (String id, String contacts)  throws  SQLException;
    public int mayor(String idRequested, String idRequester);
}
