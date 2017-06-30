package edu.ucab.desarrollo.fitucab.domainLogicLayer.M11;

import edu.ucab.desarrollo.fitucab.common.Exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.Exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

/**
 * Created by charbel on 29/06/2017.
 */
public class getFoodPerCommand extends Command {
    Entity _food ;

    public getFoodPerCommand(Entity food){_food = food;}

    @Override
    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException {

    }
}
