package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.Exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.Exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.IDaoTraining;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.util.List;

/**
 * Created by root on 25/06/17.
 */
public class GetAllTrainingCommand extends Command
{
        Entity _training;
        public GetAllTrainingCommand(Entity training){
            this._training = training;

        }


    public void execute() throws ListAllException, ListByIdException
    {

    }
}
