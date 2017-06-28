package edu.ucab.desarrollo.fitucab.domainLogicLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.CheckTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.CreateTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.UpdateTrainingCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.FillChartCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.LevelUpCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.ScoreCommand;
import edu.ucab.desarrollo.fitucab.webService.M09_ServicesGamification;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CommandsFactory {

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(M09_ServicesGamification.class);

    static public CreateTrainingCommand instanciateCreateTrainingCmd(Entity training, int userId){
        return new CreateTrainingCommand(training, userId);

    }

    static public UpdateTrainingCommand instanciateUpdateTrainingCmd(Entity training){
        return new UpdateTrainingCommand(training);

    }
    static public CheckTrainingCommand instanciateCheckTrainingCmd(int trainingId, int userId) {

        return new CheckTrainingCommand(trainingId, userId);
    }

    //Modulo 9
    static public AchieveChallengeCommand instanciateAchieveChallengeCmd(List<Entity> challenge){
        logger.error("_challenges: ",challenge);
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

}

