package edu.ucab.desarrollo.fitucab.domainLogicLayer.M06;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Training;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;


public class CheckTrainingCommand extends Command {

    int _trainingId;
    int _userId;
    public Training returnedTraining;

    public CheckTrainingCommand(int trainingId, int userId) {

        this._trainingId = trainingId;
        this._userId = userId;

    }
    public void execute() {

            try{
                //instanciacion del dao
                /*DAOTraining dao = DAOFActory.instanciateDAOTraining();
                returnedTraining = DAOTraining.Read(_trainingId, _userId);*/
            }
            catch(Exception e){
                //lanzar exception
            }

    }

    public Entity Return(){
        return null;
    }

}
