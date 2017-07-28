package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ActiveTrainingException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.IDaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

/**
 * Created by root on 28/07/17.
 */
public class ActiveTrainingCommand extends Command
{
    private Entity _training;
    private Entity _user;
    private boolean _output;

    public ActiveTrainingCommand(Entity training, Entity user)
    {
        _training = training;
        _user = user;
    }

    public void execute() throws ActiveTrainingException
    {
        IDaoTraining dao;
        dao = DaoFactory.instanceDaoTraining(_training);
        _output = dao.activateTraining(_training,_user);

    }

    public boolean get_output()
    {
        return _output;
    }

    public Entity Return(){
        return null;
    }
}
