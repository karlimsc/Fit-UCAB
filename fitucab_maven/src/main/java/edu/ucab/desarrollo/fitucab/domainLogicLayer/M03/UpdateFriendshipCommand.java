package edu.ucab.desarrollo.fitucab.domainLogicLayer.M03;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M03.DaoFriendship;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by danie on 2/7/2017.
 */
public class UpdateFriendshipCommand extends Command {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);

    private Dao _dao;
    private String _idUpdater;
    private String _action;
    private String _idUpdated;

    public UpdateFriendshipCommand(Dao dao, String updater, String updated, String action) {
        _dao = dao;
        _idUpdated = updated;
        _idUpdater = updater;
        _action = action;
    }

    @Override
    public void execute() throws NoSuchMethodException {
        try {
            ((DaoFriendship) _dao).updateFriendship(_idUpdater, _idUpdated, _action);
        }
        catch (Exception e){
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
