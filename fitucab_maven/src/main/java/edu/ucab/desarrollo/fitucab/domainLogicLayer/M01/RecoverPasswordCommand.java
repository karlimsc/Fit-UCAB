package edu.ucab.desarrollo.fitucab.domainLogicLayer.M01;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

/**
 * Created by estefania on 29/06/2017.
 */
public class RecoverPasswordCommand extends Command {

    String _email;

    /**
     * Constructor de la clase
     * @param _email String
     */
    public RecoverPasswordCommand(String _email) {
        this._email = _email;
    }

    public void execute(){
        try{

        }
        catch (Exception e){

        }

    }

    public Entity run() throws NoSuchMethodException, Exception {
        return null;
    }

}
