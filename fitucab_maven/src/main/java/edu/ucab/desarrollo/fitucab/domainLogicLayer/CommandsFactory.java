package edu.ucab.desarrollo.fitucab.domainLogicLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.RecoverPasswordCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CheckUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CreateUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.CheckTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.CreateTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.UpdateTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.FillChartCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.LevelUpCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.ScoreCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * Fabrica de comandos
 */
public class CommandsFactory {

    // Comandos LOGIN M01

    /**
     * Intancia del CreateUserCommad
     * @param user Objeto con todos los parametros de User
     * @return CreateUserCommad con parametro de devolucion user
     */

    static public CreateUserCommand instanciateCreateUserCmd(Entity user){
        return new CreateUserCommand(user);
    }

    /**
     *  Intancia del RecoverPasswordCommand
     * @param email String
     * @return RecoverPasswordCommand con parametro email para la recuperacion de la cuenta
     */

    static public RecoverPasswordCommand instanciateRecoverPasswordCmd(String email){
        return new RecoverPasswordCommand(email);
    }


    static public CheckUserCommand instanciateCheckUserCmd(Entity user){
        return new CheckUserCommand(user);
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
    static public Command instanciateAchieveChallengeCmd(int id, Dao dao){
        return new AchieveChallengeCommand(id, dao);
    }

    static public Command instanciateScoreCmd(int id, Dao dao){
        return new ScoreCommand(id, dao);
    }

    static public Command instanciateFillChartCmd(int id, Dao dao){
        return new FillChartCommand(id, dao);
    }

    static public Command instanciateLevelUpCmd(int id, Dao dao){
        return new LevelUpCommand(id, dao);
    }
    //Fin Modulo 9


}

