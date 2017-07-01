package edu.ucab.desarrollo.fitucab.domainLogicLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.*;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M02.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.*;

/**
 * Created by root on 29/06/17.
 */
public class UserCommand extends Command {
    Entity _home;
    int id;

    public UserCommand(int id){
        this.id=id;
    }

    @Override
    public void execute() {
        IDaoUser usuario= DaoFactory.instanceDaoUser(id);
       _home=usuario.read(id);

    }

    public Entity Return(){
        return _home;
    }
}
