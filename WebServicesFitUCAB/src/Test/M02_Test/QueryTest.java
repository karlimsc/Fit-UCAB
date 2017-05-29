import Domain.Query;
import Domain.Sql;
import Domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;

public class QueryTest {

    Sql sql = new Sql();
    Query query;
    User user;

    @Before
    public void setUp() {
        try {
            String insert = "INSERT INTO person VALUES (500, 'user1', 'pass', 'email', 's', 'phone', '1993-06-04')";
            sql.sqlConn(insert);
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Codigo de erro: " + e.getErrorCode());
            System.err.println("Estado de SQL: " + e.getSQLState());
            System.err.println("Mensaje: " + e.getMessage());
        }
        catch (Exception e){
            e.printStackTrace();
            System.err.println("Mensaje: " + e.getMessage());
        }
    }

    @After
    public void tearDown() throws SQLException {
        try {
            String delete = "DELETE FROM person WHERE personid = 500";
            sql.sql(delete);
        } catch (SQLException e) {
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Mensaje: " + e.getMessage());
        } finally {
            sql.closeConnection(sql.getConn());
        }
    }

    @Test
    public void testQueryVoidCreated() {
        query = new Query();
        assertTrue(query instanceof  Query);
    }

    @Test
    public void testQueryNotCreated() throws Exception {
        assertFalse(query instanceof Query);
    }

    @Test
    public void testGetUserSuccess() {
        User user = query.getUser(500);
        assertTrue(user.getId() > 0);
    }

    @Test(expected = SQLException.class)
    public void testGetUserFail() {
        User user = new User();
        user = query.getUser(501);
        assertTrue(user.getId() == 0);
    }

    @Test
    public void updateUser() throws Exception {

    }

    @Test
    public void getHome() throws Exception {

    }

}