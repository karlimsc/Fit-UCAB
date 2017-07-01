package edu.ucab.desarrollo.fitucab.domainLogicLayer.M11;

import edu.ucab.desarrollo.fitucab.common.Exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.Exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.Exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M11.IDaoFood;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.sql.SQLException;

/**
 * Created by charbel on 30/06/2017.
 */
public class getFoodallCommand extends Command {

    Entity _food;
    public String Respuesta ;

    public getFoodallCommand(Entity food) {_food =food; }


    @Override
    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException, SQLException, BdConnectException {

        IDaoFood Daofood = DaoFactory.iniciarDaoFood();
        Respuesta = Daofood.getFoodAll(_food);

    }
}
