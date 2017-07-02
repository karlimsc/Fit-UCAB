package edu.ucab.desarrollo.fitucab.domainLogicLayer.M11;

import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M11.IDaoFood;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.sql.SQLException;

/**
 * Created by charbel on 01/07/2017.
 */
public class insertUnAlimentoCommand extends Command {
    Entity _food;
    public Entity Respuesta;

    public  insertUnAlimentoCommand (Entity food)
    {
        _food = food;
    }

    @Override
    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException, SQLException, BdConnectException {
        IDaoFood Daofood = DaoFactory.iniciarDaoFood();
        Respuesta = Daofood.InsertarAlimen(_food);
    }

    @Override
    public Entity Return() {
        return null;
    }
}
