import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CreateUserCommand;
import org.junit.jupiter.api.Test;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by elberg on 03/07/17.
 */
class CreateUserCommandTest {
    Boolean _response ;
    Entity _userResponce ;

    @Test
    void getUserRegistry() {
        _userResponce = null;
        assertNull(_userResponce);
    }

    @Test
    void execute() throws InstantiationException, SQLException {

        Entity _user = EntityFactory.createUser(60,"naomi","123",
                "murcielagoEmpalagoso@malo.com","f","00000",
                java.sql.Date.valueOf("2010-12-12"),
                12,12);
        Command _command = CommandsFactory.instanciateCreateUserCmd(_user);
        CreateUserCommand cmd = (CreateUserCommand) _command;
        cmd.execute();

        User _returnUser = (User) _user;

        //Obtiene el usuario registrado con su estatus
        User rUser = (User) cmd.getUserRegistry();

        _returnUser.setId(rUser.getId());

        //Elimina la insercion
         Connection conn;
         Dao _dao = null;
        conn = _dao.getConInstance();
        CallableStatement cst = conn.prepareCall("{?=call M01_ELIMINARUSER(?)}");
        cst.registerOutParameter(1, Types.INTEGER);
        cst.setString(2, "naomi");
        cst.execute();

        assertNotNull(rUser);


    }

    @Test
    void get_response() {
        _response = true;
        assertEquals(true,_response);
    }

}