import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.common.exceptions.M01.LoginUserException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by elberg on 02/07/17.
 */
class CheckUserCommandTest {


    @Test
    void getUserLogin() {
        User _user = new User("karol","1234");
        _user.setId(1);
        assertNotEquals(2,_user.getId());
        assertEquals("karo",_user.getUser());

    }

    @Test
    @Ignore
    void execute() throws SQLException {
        User _user = new User("karol","1234");
        _user.setId(1);
        DaoUser LoginUserDao = (DaoUser) DaoFactory.instanciateDaoUser(_user);
        try {
            Entity _userReturn = LoginUserDao.login(_user);
            assertEquals(1,_userReturn.get_id());
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LoginUserException e) {
            e.printStackTrace();
        }

    }

}