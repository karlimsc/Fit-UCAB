package edu.ucab.desarrollo.fitucab.Test.M03_FriendshipTest;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Friendship;
import edu.ucab.desarrollo.fitucab.common.entities.UserAuxiliar;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M03.IDaoFriendship;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M03.IDaoNearMe;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * Created by Andy on 3/7/2017.
 */
public class FriendshipTest {
    static Connection conn;
    static Dao _dao;
    static Entity _user;

    /**
     * Prueba unitaria para comprobar que el array usado en el metodo getList no sea nulo
     * @throws SQLException
     */

    @Test
    public void getListFriendstest() throws SQLException {

        ArrayList<UserAuxiliar> ap = new ArrayList<UserAuxiliar>();
        Friendship _friend = EntityFactory.createFriendship();
        IDaoFriendship dapfriend = DaoFactory.instanceDaoFriendship(_friend);
        ap = dapfriend.getList("1","Friends");
        assertNotNull(ap);

    }

    /**
     * Prueba unitaria para comprobar que el array usado en el metodo getList no sea nulo
     * @throws SQLException
     */
    @Test
    public void getListRequeststest() throws SQLException {

        ArrayList<UserAuxiliar> ap = new ArrayList<UserAuxiliar>();

        Friendship _friend = EntityFactory.createFriendship();
        IDaoFriendship daofriend = DaoFactory.instanceDaoFriendship(_friend);
        ap = daofriend.getList("1","Requests");
        assertNotNull(ap);

    }
    /**
     * Prueba unitaria para comprobar que el array usado en el metodo getNearme no sea nulo
     * @throws SQLException
     */
    @Test
    public  void getNearMetest() throws SQLException {

        ArrayList<UserAuxiliar> ap = new ArrayList<UserAuxiliar>();

        Friendship _friend = EntityFactory.createFriendship();
        IDaoNearMe daofriend = DaoFactory.instanceDaoNearMe(_friend);
        ap = daofriend.getNearMe("1","2","3","4");
        assertNotNull(ap);

    }

    /**
     * Prueba unitaria para comprobar que un numero es mayor que el otro del metodo mayor
     * @throws SQLException
     */

    @Test
    public void mayortest() {
        Friendship _friend = EntityFactory.createFriendship();
        IDaoFriendship daofriend = DaoFactory.instanceDaoFriendship(_friend);
        int x = daofriend.mayor("1","2");
        assertEquals(2,x);

    }


}
