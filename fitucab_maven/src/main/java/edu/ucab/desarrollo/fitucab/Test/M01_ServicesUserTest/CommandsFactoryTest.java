import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CheckUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CreateUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.RecoverPasswordCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by elberg on 03/07/17.
 */
class CommandsFactoryTest {
    Command _command;

    @Test
    void instanciateCreateUserCmd() {
        User u = new User(60,"naomi","123",
                "murcielagoEmpalagoso@malo.com","f","00000",
                java.sql.Date.valueOf("2010-12-12"),
                12,12);
        CreateUserCommand _command = (CreateUserCommand) CommandsFactory.instanciateCreateUserCmd(u);
        Boolean result = _command.get_response();
        assertNotNull(_command);
        assertEquals(false,result);
    }

    @Test
    void instanciateRecoverPasswordCmd() {
        RecoverPasswordCommand _command = (RecoverPasswordCommand) CommandsFactory
                .instanciateRecoverPasswordCmd("murcielagoEmpalagoso@malo.com");
        String result = _command.get_response();
        assertNotNull(_command);
        assertNull(result);
    }

    @Test
    void instanciateCheckUserCmd() {
        User u = new User(60,"naomi","123");
        CheckUserCommand _command = (CheckUserCommand) CommandsFactory.instanciateCheckUserCmd(u);
        Entity result = _command.Return();
        assertNotNull(_command);
        assertNull(result);
    }

}