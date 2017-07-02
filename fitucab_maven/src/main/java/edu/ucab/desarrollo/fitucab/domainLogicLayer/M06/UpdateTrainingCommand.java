package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;

public class UpdateTrainingCommand extends Command{

    Entity _updatedTraining;

    public UpdateTrainingCommand (Entity updatedTraining){
        this._updatedTraining = updatedTraining;
    }

    public void execute() {
        try{
            //instancio el DAO y ejecuto el metodo modificar
        }
        catch(Exception e){
            //atajo si existe algun error
        }
    }

    public Entity Return(){
        return null;
    }

}
