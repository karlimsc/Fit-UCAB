package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.IDaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.util.List;

/**
 * Created by root on 25/06/17.
 */
public class GetAllTrainingCommand extends Command
{
    private Entity       _input;
    private List<Entity> _output;


    public GetAllTrainingCommand( Entity training )
    {
        super();

        _input = training;
    }


    public List<Entity> get_output()
    {
        return _output;
    }


    public void execute() throws ListAllException
    {

        IDaoTraining dao;

        dao = DaoFactory.instanceDaoTraining( _input );
        _output = dao.listAll( _input );

    }



    public Entity Return(){
        return null;
    }
}
