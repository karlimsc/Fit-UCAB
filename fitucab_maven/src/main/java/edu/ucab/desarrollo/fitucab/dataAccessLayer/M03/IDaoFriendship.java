package edu.ucab.desarrollo.fitucab.dataAccessLayer.M03;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Friendship;
import edu.ucab.desarrollo.fitucab.common.entities.Water;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by danie on 2/7/2017.
 */
public interface IDaoFriendship extends IDao{

    public Entity create(Entity e) throws SQLException;
    public ArrayList<Friendship> getList (Entity e)  throws  SQLException;
    public Entity getFriendship(Entity e)  throws  SQLException;
    public Entity deleteFriendship(Entity e)  throws  SQLException;
}
