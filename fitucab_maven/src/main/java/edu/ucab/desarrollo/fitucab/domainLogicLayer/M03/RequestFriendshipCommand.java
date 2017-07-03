package edu.ucab.desarrollo.fitucab.domainLogicLayer.M03;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.entities.Friendship;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M03.DaoFriendship;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M09.DaoGaming;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by danie on 2/7/2017.
 */
public class RequestFriendshipCommand extends Command {
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(RequestFriendshipCommand.class);

    private Dao _dao;
    private List<Entity> _friends;
    private String _idRequester;
    private String _idRequested;

    public RequestFriendshipCommand(Dao dao, String requester, String requested) {
        _dao = dao;
        _idRequested = requested;
        _idRequester = requester;
    }

    public List<Entity> getFriends() {
        return _friends;
    }

    public void execute() throws NoSuchMethodException {
        try {
            ((DaoFriendship) _dao).requestFriendship(_idRequester, _idRequested);
        }
        catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.debug("Debug: ", error.toString());
            logger.error("Error: ", error.toString());
        }
    }

    public Entity Return(){
        return null;
    }
}
