package edu.ucab.desarrollo.fitucab.Test.M01_ServicesUserTest;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.common.exceptions.M01.LoginUserException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CheckUserCommand;
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
        User _user = new User("karo","1234");
        _user.setId(1);
        assertNotEquals(2,_user.getId());
        assertEquals("karo",_user.getUser());

    }

    @Test
    void execute() throws Exception {
        try {
        Entity userObject = EntityFactory.createUser("karo","1234");
        Command _command = CommandsFactory.instanciateCheckUserCmd(userObject);
        CheckUserCommand cmd = (CheckUserCommand) _command;
        cmd.execute();
        User result = (User) CheckUserCommand.getUserLogin();

            assertNotNull(result.get_id());

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (LoginUserException e) {
            e.printStackTrace();
        }

    }

}