package edu.ucab.desarrollo.fitucab.Test.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Challenge;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M09.DaoGaming;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.FillChartCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.LevelUpCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.ScoreCommand;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

/**
 * Created by JuanMendez on 3/7/2017.
 */
public class CommandM09Test {

    @Test
    public void AchieveChallengeCommandConstructorTestPUE() {
        Dao dao = DaoFactory.instanceDaoGaming();
        int userId = 1;
        AchieveChallengeCommand cmd = (AchieveChallengeCommand) CommandsFactory.instanciateAchieveChallengeCmd(userId,dao);
        assertNotNull(cmd);
    }

    @Test(expected = AssertionError.class)
    public void AchieveChallengeCommandConstructorTestPUFE() {
        Dao dao = null;
        int userId = 0;
        AchieveChallengeCommand cmd = (AchieveChallengeCommand) CommandsFactory.instanciateAchieveChallengeCmd(userId,dao);
        assertNull(cmd);
    }

    @Test
    public void AchieveChallengeCommandGetChallengeTestPUE() {
        List<Entity> lista = AchieveChallengeCommand.getChallenges();
        assertNull(lista);
    }

    @Test(expected = AssertionError.class)
    public void AchieveChallengeCommandGetChallengeTestPUFE() {
        List<Entity> lista = AchieveChallengeCommand.getChallenges();
        assertNotNull(lista);
    }

    /*
    @Test
    public void AchieveChallengeCommandExecuteTestPUE() {
        int id=1;
        Dao dao = DaoFactory.instanceDaoGaming();
        List<Entity> lista = EntityFactory.getChallenges();
        //((DaoGaming) dao).achieveChallenge(id, lista);
        assertTrue(((DaoGaming) dao).achieveChallenge(id, lista));
    }

    @Test(expected = AssertionError.class)
    public void AchieveChallengeCommandExecuteTestPUFE() {
        int id=0;
        Dao dao = DaoFactory.instanceDaoGaming();
        List<Entity> lista = EntityFactory.getChallenges();
        ((DaoGaming) dao).achieveChallenge(id, lista);
        assertNull(challenge);
    }
    */

    @Test
    public void FillChartCommandConstructorTestPUE() {
        Dao dao = DaoFactory.instanceDaoGaming();
        int userId = 1;
        FillChartCommand cmd = (FillChartCommand) CommandsFactory.instanciateFillChartCmd(userId,dao);
        assertNotNull(cmd);
    }

    @Test(expected = AssertionError.class)
    public void FillChartCommandConstructorTestPUFE() {
        Dao dao = null;
        int userId = 0;
        FillChartCommand cmd = (FillChartCommand) CommandsFactory.instanciateFillChartCmd(userId,dao);
        assertNull(cmd);
    }

    @Test
    public void FillChartCommandGetChallengeTestPUE() {
        Entity entidad = FillChartCommand.getChallenge();
        assertNull(entidad);
    }

    @Test(expected = AssertionError.class)
    public void FillChartCommandGetChallengeTestPUFE() {
        Entity entidad = FillChartCommand.getChallenge();
        assertNotNull(entidad);
    }

    @Test
    public void FillChartCommandExecuteTestPUE() {
        int id=1;
        Dao dao = DaoFactory.instanceDaoGaming();
        Entity challenge = ((DaoGaming) dao).fillChart(id);
        assertNotNull(challenge);
    }

    @Test(expected = AssertionError.class)
    public void FillChartCommandExecuteTestPUFE() {
        int id=0;
        Dao dao = DaoFactory.instanceDaoGaming();
        Entity challenge = ((DaoGaming) dao).fillChart(id);
        assertNull(challenge);
    }

    @Test
    public void LevelUpCommandConstructorTestPUE() {
        Dao dao = DaoFactory.instanceDaoGaming();
        int userId = 1;
        LevelUpCommand cmd = (LevelUpCommand) CommandsFactory.instanciateLevelUpCmd(userId,dao);
        assertNotNull(cmd);
    }

    @Test(expected = AssertionError.class)
    public void LevelUpCommandConstructorTestPUFE() {
        Dao dao = null;
        int userId = 0;
        LevelUpCommand cmd = (LevelUpCommand) CommandsFactory.instanciateLevelUpCmd(userId,dao);
        assertNull(cmd);
    }

    @Test
    public void LevelUpCommandGetChallengeTestPUE() {
        Entity entidad = LevelUpCommand.getChallenge();
        assertNull(entidad);
    }

    @Test(expected = AssertionError.class)
    public void LevelUpCommandGetChallengeTestPUFE() {
        Entity entidad = LevelUpCommand.getChallenge();
        assertNotNull(entidad);
    }

    @Test
    public void LevelUpCommandExecuteTestPUE() {
        int id=1;
        Dao dao = DaoFactory.instanceDaoGaming();
        Entity challenge = ((DaoGaming) dao).levelUp(id);
        assertNotNull(challenge);
    }

    @Test(expected = AssertionError.class)
    public void LevelUpCommandExecuteTestPUFE() {
        int id=0;
        Dao dao = DaoFactory.instanceDaoGaming();
        Entity challenge = ((DaoGaming) dao).levelUp(id);
        assertNull(challenge);
    }

    @Test
    public void ScoreCommandConstructorTestPUE() {
        Dao dao = DaoFactory.instanceDaoGaming();
        int userId = 1;
        ScoreCommand cmd = (ScoreCommand) CommandsFactory.instanciateScoreCmd(userId,dao);
        assertNotNull(cmd);
    }

    @Test(expected = AssertionError.class)
    public void ScoreCommandConstructorTestPUFE() {
        Dao dao = null;
        int userId = 0;
        ScoreCommand cmd = (ScoreCommand) CommandsFactory.instanciateScoreCmd(userId,dao);
        assertNull(cmd);
    }

    @Test
    public void ScoreCommandGetChallengeTestPUE() {
        Entity entidad = ScoreCommand.getChallenge();
        assertNull(entidad);
    }

    @Test(expected = AssertionError.class)
    public void ScoreCommandGetChallengeTestPUFE() {
        Entity entidad = ScoreCommand.getChallenge();
        assertNotNull(entidad);
    }

    @Test
    public void ScoreCommandExecuteTestPUE() {
        int id=1;
        Dao dao = DaoFactory.instanceDaoGaming();
        Entity challenge = ((DaoGaming) dao).score(id);
        assertNotNull(challenge);
    }

    @Test(expected = AssertionError.class)
    public void ScoreCommandExecuteTestPUFE() {
        int id=0;
        Dao dao = DaoFactory.instanceDaoGaming();
        Entity challenge = ((DaoGaming) dao).score(id);
        assertNull(challenge);
    }

    @Test
    public void ScoreCommandExecuteGetTestPUE() {
        Entity entidad = EntityFactory.createChallenge();
        int id = ((Challenge) entidad).getScore();
        assertNotNull(id);
    }

    @Test(expected = AssertionError.class)
    public void ScoreCommandExecuteGetTestPUFE() {
        Entity entidad = EntityFactory.createChallenge();
        int id = ((Challenge) entidad).getScore();
        assertNull(id);
    }

}
