package edu.ucab.desarrollo.fitucab.domainLogicLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CheckPasswordEmailCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CheckUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CreateUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.CheckTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.CreateTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.UpdateTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;

/**
 * Fabrica de comandos
 */
public class CommandsFactory {

    // Comandos LOGIN M01
    static public CreateUserCommand instanciateCreateUserCmd(Entity user){
        return new CreateUserCommand(user);
    }
    static public CheckUserCommand instanciateCheckUserCmd(String user, String password){
        return new CheckUserCommand(user, password);
    }
    static public CheckPasswordEmailCommand instanciateCheckPasswordEmailCmd(String email){
        return new CheckPasswordEmailCommand(email);
    }

    // Comandos M06

    static public CreateTrainingCommand instanciateCreateTrainingCmd(Entity training, int userId){
        return new CreateTrainingCommand(training, userId);

    }

    static public UpdateTrainingCommand instanciateUpdateTrainingCmd(Entity training){
        return new UpdateTrainingCommand(training);

    }
    static public CheckTrainingCommand instanciateCheckTrainingCmd(int trainingId, int userId) {

        return new CheckTrainingCommand(trainingId, userId);
    }

    /**
     * Metodo para instanciar el comando GetAllTraining
     * @param training
     * @return el comando GetAllTraining
     */
    public static Command instanciateGetAllTrainingCmd( Entity training )
    {
        return new GetAllTrainingCommand(training);
    }

    /**
     * Metodo para instanciar el comando GetTrainingDetail
     * @param training
     * @return el comando GetTrainingDetail
     */
    public static Command instanciateGetTrainingDetailCmd (Entity training){
        return new GetTrainingDetailCommand(training);
    }

    //Modulo 9
    static public AchieveChallengeCommand instanciateAchieveChallengeCmd(Entity challenge){
        return new AchieveChallengeCommand(challenge);
    }
    //Fin Modulo 9


}

