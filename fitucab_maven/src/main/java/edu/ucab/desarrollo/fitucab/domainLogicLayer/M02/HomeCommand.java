package edu.ucab.desarrollo.fitucab.domainLogicLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.*;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M02.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.*;

/**
 * Created by root on 29/06/17.
 */
public class HomeCommand extends Command {
    Entity _home, _usuario;
    int id;

    public HomeCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute() {
        IDaoUser usuario= DaoFactory.instanceDaoUser(id);
        _usuario = usuario.read(id);
        IDaoHome home = DaoFactory.instanceDaoHome(_usuario);
        _home = home.read(_usuario);
    }

    public Entity Return(){
        return _home;
    }
}