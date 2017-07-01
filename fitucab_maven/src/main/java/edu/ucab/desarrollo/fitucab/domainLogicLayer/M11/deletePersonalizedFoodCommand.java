package edu.ucab.desarrollo.fitucab.domainLogicLayer.M11;

import com.sun.org.apache.regexp.internal.RE;
import edu.ucab.desarrollo.fitucab.common.Exceptions.ListAllException;
import edu.ucab.desarrollo.fitucab.common.Exceptions.ListByIdException;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M11.IDaoFood;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

import java.sql.SQLException;

/**
 * Created by charbel on 01/07/2017.
 */
public class deletePersonalizedFoodCommand extends Command {

    Entity _food ;
    public String Respuesta ;
    public deletePersonalizedFoodCommand(Entity food){_food = food;}
    @Override
    public void execute() throws ListAllException, ListByIdException, NoSuchMethodException, SQLException {

        IDaoFood Daofood = DaoFactory.iniciarDaoFood();
        Respuesta = Daofood.DeletPerFood(_food);




    }
}
