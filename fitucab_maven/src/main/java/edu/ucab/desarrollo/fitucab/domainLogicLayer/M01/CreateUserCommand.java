package edu.ucab.desarrollo.fitucab.domainLogicLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

/**
 * Created by karo on 24/06/17.
 */
public class CreateUserCommand extends Command {

    Entity _user;
    private static String DEFAULT_ENCODING1="UTF-8";

    public CreateUserCommand(Entity _user) {
        this._user = _user;
    }


    public void execute() {

        try{
            //instanciacion del dao
        }
        catch(Exception e){
            //lanzar exception
        }
    }

    public Entity Return() {
        return null;
    }
}
