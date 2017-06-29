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
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.FillChartCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.LevelUpCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.ScoreCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M10.*;
import edu.ucab.desarrollo.fitucab.webService.M09_ServicesGamification;
import org.slf4j.LoggerFactory;

import java.util.List;

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
    static public AchieveChallengeCommand instanciateAchieveChallengeCmd(List<Entity> challenge){
        return new AchieveChallengeCommand(challenge);
    }

    static public ScoreCommand instanciateScoreCmd(Entity score){
        return new ScoreCommand(score);
    }

    static public FillChartCommand instanciateFillChartCmd(Entity challengeAchieve){
        return new FillChartCommand(challengeAchieve);
    }

    static public LevelUpCommand instanciateLevelUpCmd(Entity level){
        return new LevelUpCommand(level);
    }
    //Fin Modulo 9

    //Inicio Modulo 10
    /**
     * Metodo para instanciar el comando AddWater
     * @param water
     * @return el comando AddWater
     */
        static public AddWaterCommand instatiateAddWaterCmd(Entity water){ return new AddWaterCommand(water); }
    /**
     * Metodo para instanciar el comando GetListDate
     * @param water
     * @return el comando GetListDate
     */
        static public GetListDateCommand instatiateGetListDateCmd(Entity water){ return new GetListDateCommand(water); }

    /**
     * Metodo para instanciar el comando GetWater
     * @param water
     * @return el comando GetWater
     */
    static public GetWaterCommand instatiateGetWaterCmd(Entity water){ return new GetWaterCommand(water); }

    /**
     * Metodo para instanciar el comando GetFechaInt
     * @param water
     * @return el comando GetFechaInt
     */
    static public GetFechaIntCommand instatiateGetFechaIntCmd(Entity water){ return new GetFechaIntCommand(water); }
    /**
     * Metodo para instanciar el comando DeletLast
     * @param water
     * @return el comando DeletLast
     */
    static public DeletLastCommand instatiateDeletLastCmd(Entity water){ return new DeletLastCommand(water); }

    /**
     * Metodo para instanciar el comando DeletWaterTm
     * @param water
     * @return el comando DeletWaterTm
     */
    static public DeletWaterTmCommand instatiateDeletWaterTmCmd(Entity water){ return new DeletWaterTmCommand(water); }

    /**
     * Metodo para instanciar el comando GetFecha
     * @param water
     * @return el comando GetFecha
     */

    static public GetFechaCommand instatiateGetFechaCmd(Entity water){ return new GetFechaCommand(water); }

    //Fin Modulo 10


}

