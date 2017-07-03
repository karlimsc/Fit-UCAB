package edu.ucab.desarrollo.fitucab.domainLogicLayer.M03;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.UserAuxiliar;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M03.IDaoFriendship;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by danie on 2/7/2017.
 */
public class GetContactsCommand extends Command{
    Entity _friendship;
    public String returned;
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(GetFriendsCommand.class);
    private String _contacts;
    private String _id;

    public GetContactsCommand(String id, String contacts) {
        _id = id;
        _contacts = contacts;
    }

    @Override
    public void execute() throws NoSuchMethodException{
        IDaoFriendship daoFriendship = DaoFactory.instanceDaoFriendship(_friendship);
        try {

            ArrayList<UserAuxiliar> resultado = daoFriendship.getContacts(_id, _contacts);
            Gson gson = new Gson();
            returned = gson.toJson(resultado);

        }catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());

        }
    }

    @Override
    public Entity Return() throws SQLException, CreateHomeException, BdConnectException {
        return null;
    }
}
