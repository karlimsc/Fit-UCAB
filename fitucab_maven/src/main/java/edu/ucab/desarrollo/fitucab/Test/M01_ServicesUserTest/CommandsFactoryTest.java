import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
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
        _command = CommandsFactory.instanciateCreateUserCmd(u);
        assertNotNull(_command);
    }

    @Test
    void instanciateRecoverPasswordCmd() {
        _command = CommandsFactory
                .instanciateRecoverPasswordCmd("murcielagoEmpalagoso@malo.com");
        assertNotNull(_command);
    }

    @Test
    void instanciateCheckUserCmd() {
        User u = new User(60,"naomi","123");
        _command = CommandsFactory.instanciateCheckUserCmd(u);
        assertNotNull(_command);
    }

}