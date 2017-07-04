package edu.ucab.desarrollo.fitucab.domainLogicLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CheckUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CreateUserCommand;

import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.CreateTrainingCommand;

import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.RecoverPasswordCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M02.HomeCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M02.UpdateUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M02.UserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.*;

import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.FillChartCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.LevelUpCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.ScoreCommand;


import java.util.ArrayList;
import java.util.List;

import edu.ucab.desarrollo.fitucab.domainLogicLayer.M10.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M03.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M07.CreatePlanificationCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M07.DeletePlanificationCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M07.GetPlanificationByIdCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M07.UpdatePlanificationCommand;


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
    static public Command instanciateCreateUserCmd(Entity user){
        return new CreateUserCommand(user);
    }

    /**
     * Intancia del RecoverPasswordCommand
     * @param email String
     * @return RecoverPasswordCommand con parametro email para la recuperacion de la cuenta
     */
    static public Command instanciateRecoverPasswordCmd(String email){
        return new RecoverPasswordCommand(email);
    }

    /**
     * Intancia del CheckUserCommand
     * @param user El usuario a Instanciar
     * @return CheckUserCommand para vericar el registro del usuario
     */
    static public Command instanciateCheckUserCmd(Entity user){
        return new CheckUserCommand(user);
    }


    //MODULO 2

    /**
     * Metodo que instancia el UserCommand con un id
     * @param id
     * @return
     */
    static public UserCommand instanciateUserCmd(int id){
        return new UserCommand(id);
    }

    /**
     * Metodo que instancia el HomeCommand con un id
     * @param id
     * @return
     */
    static public HomeCommand instanciateHomeCmd(int id){
        return new HomeCommand(id);
    }

    /**
     * Metodo de actualizar usuario
     * @param id
     * @param username
     * @param phone
     * @param email
     * @return
     */
    static public UpdateUserCommand instanciateUpdateUserCmd(int id,String username,String phone,String email){
        return new UpdateUserCommand(id,username,phone,email);
    }

    //FIN MODULO 2

    // Comandos M06

    static public CreateTrainingCommand instanciateCreateTrainingCmd(Entity training){
        return new CreateTrainingCommand(training);
    }

    static public DeleteTrainingCommand instanciateDeleteTrainingCmd(Entity training){
        return new DeleteTrainingCommand(training);
    }

    static public ShareTrainingCommand instanciateShareTrainingCmd(Entity training) {
        return new ShareTrainingCommand(training);
    }

    static public AddActivitiesToTrainingCommand instanciateAddActivitiesToTrainingCmd(Entity training) {
        return new AddActivitiesToTrainingCommand(training);
    }

    static public RemoveActivitiesFromTrainingCommand instanciateRemoveActivitiesFromTrainingCmd(Entity training) {
        return new RemoveActivitiesFromTrainingCommand(training);
    }

    static public ChangeTrainingNameCommand instanciateChangeTrainingNameCmd(Entity training) {
        return new ChangeTrainingNameCommand(training);
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

    /**
     * Metodo que instancia el comando AchieveChallengeCommand
     * @param id Id del usuario.
     * @param dao Dao que se usara el comando.
     * @return Clase Command
     * @see Command
     * @see AchieveChallengeCommand
     */
    static public Command instanciateAchieveChallengeCmd(int id, Dao dao){
        return new AchieveChallengeCommand(id, dao);
    }

    /**
     * Metodo que instancia el comando ScoreCommand
     * @param id Id del usuario.
     * @param dao Dao que se usara el comando.
     * @return Clase Command
     * @see Command
     * @see ScoreCommand
     */
    static public Command instanciateScoreCmd(int id, Dao dao){
        return new ScoreCommand(id, dao);
    }

    /**
     * Metodo que instancia el comando FillChartCommand
     * @param id Id del usuario.
     * @param dao Dao que se usara el comando.
     * @return Clase Command
     * @see Command
     * @see FillChartCommand
     */
    static public Command instanciateFillChartCmd(int id, Dao dao){
        return new FillChartCommand(id, dao);
    }

    /**
     * Metodo que instancia el comando LevelUpCommand
     * @param id Id del usuario.
     * @param dao Dao que se usara el comando.
     * @return Clase Command
     * @see Command
     * @see LevelUpCommand
     */
    static public Command instanciateLevelUpCmd(int id, Dao dao){
        return new LevelUpCommand(id, dao);
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
     * Metodo para instanciar el comando DeletLast
     * @param water
     * @return el comando DeletLast
     */
    static public DeletLastCommand instatiateDeletLastCmd(Entity water){ return new DeletLastCommand(water); }


    /**
     * Metodo para instanciar el comando GetFecha
     * @param water
     * @return el comando GetFecha
     */

    static public GetFechaCommand instatiateGetFechaCmd(Entity water){ return new GetFechaCommand(water); }

    //Fin Modulo 10

    //Modulo 3

    /**
     * Metodo para instanciar el comando RequestFriendship con todos sus atributos

     * @param userOne
     * @param userTwo
     * @return el comando RequestFriendship
     */
    static public Command instanciateRequestFriendshipCmd(Dao friendship, String userOne, String userTwo){ return new RequestFriendshipCommand(friendship, userOne, userTwo); }

    /**
     * Metodo para instanciar el comando UpdateFriendship con todos sus atributos

     * @param updater
     * @param updated
     * @return el comando RequestFriendship
     */
    static public Command instanciateUpdateFriendshipCmd(Dao friendship, String updater, String updated, String action){ return new UpdateFriendshipCommand(friendship, updater, updated, action); }


    /**
     * Metodo para instanciar el comando Location
     * @param id es el id del usuario
     * @param nearMe es la instancia del Dao
     * @longitud longitud de la ubicacion
     * @latitud latitud de la ubicacion
     * @return el comando GetFriends
     */
    static public Command instanciateLocationCmd(Dao nearMe, String id, String longitud, String latitud){ return new LocationCommand(nearMe, id, longitud, latitud); }

    /**
     * Metodo para instanciar el comando GetFriends
     * @param id es el id del usuario
     * @param action identifica si son amigos o no
     * @return el comando GetFriends
     */

    public static GetFriendsCommand instatiateGetFriendsCmd(String id, String action){ return new GetFriendsCommand(id, action); }

    public static GetContactsCommand instatiateGetContactsCmd(String id, String contacts) { return new GetContactsCommand(id, contacts);}

    public static NearMeCommand instatiateNearMeCmd(String id, String longitud, String latitud, String rango){ return new NearMeCommand(id, longitud, latitud, rango);}

    //fin modulo 3

    //Modulo11

    static public getFoodPerCommand getFoodPerCmd(Entity Food){ return new getFoodPerCommand(Food); }

    static  public getFoodallCommand getFoodallCmd (Entity Food ) {return  new getFoodallCommand(Food);}

    static public getSuggestionCommand getSuggestionCmd (Entity Food) {return new getSuggestionCommand(Food);}

    static public getFoodAutoCommand getFoodAutoCmd (Entity Food) {return new getFoodAutoCommand(Food);}

    static public deletePersonalizedFoodCommand deletPersFoodCmd (Entity Food)
    {return new deletePersonalizedFoodCommand(Food); }

    static public updatePersoCommand updatepersonCmd (Entity Food){return new updatePersoCommand(Food);}

    static  public insertUnAlimentoCommand insertarAlimentoCmd (Entity Food) {return new insertUnAlimentoCommand(Food);}

    static public insertPersoFoodCommand insertarPersoFoodCmd (Entity Food) {return  new insertPersoFoodCommand(Food);}

    static public getPersonalizedListCommand getPersoFoodCmd (Entity Food) {return new getPersonalizedListCommand(Food);}

    static public MomentCommand getMoment (Entity Moment) {return  new MomentCommand(Moment);}

    static public DeleteDietCommand deleteDietCmd (Entity Diet) {return new DeleteDietCommand(Diet);}

    static public GetCaloriesConsumedDayCommand getCaloriesDayCmd (Entity Diet)
    {return new GetCaloriesConsumedDayCommand(Diet);}

    static public GetCaloriesConsumedWeekCommand getCaloriesWeekCmd (Entity Diet)
    {return new GetCaloriesConsumedWeekCommand(Diet);}

    static public GetCaloriesConsumedMonthCommand getCaloriesMonthCmd (Entity Diet)
    {return new GetCaloriesConsumedMonthCommand(Diet);}

    static public GetCaloriesDateCommand getCaloriesDateCmd (Entity Diet) {return new GetCaloriesDateCommand(Diet);}

    static public GetMomentFoodCommand getMomentFoodCmd (Entity Diet) {return new GetMomentFoodCommand(Diet);}

    static public InsertOneDietCommand insertOneDietCmd (Entity Diet) {return new InsertOneDietCommand(Diet);}

 //fin modulo 11

    //Modulo 7

    /**
     * Metodo para instaciar el comando CreatePlanificacion
     * @param create Objeto Entity
     * @return Instancia del comando
     * @see CreatePlanificationCommand
     * @see Entity
     */
    public static CreatePlanificationCommand instanciateCreatePlanificationCmd(Entity create) {
        return new CreatePlanificationCommand(create);
    }

    /**
     * Metodo para instaciar el comando DeletePlanificacion
     * @param delete Objeto Entity
     * @return Instancia del comando
     * @see DeletePlanificationCommand
     * @see Entity
     */
    public static DeletePlanificationCommand instanciateDeletePlanificationCmd(Entity delete) {
        return new DeletePlanificationCommand(delete);
    }

    /**
     * Metodo para instaciar el comando GetPlanificacion
     * @param get Objeto Entity
     * @return Instancia del comando
     * @see GetPlanificationByIdCommand
     * @see Entity
     */
    public static GetPlanificationByIdCommand instanciateGetPlanificationByIdCmd(Entity get) {
        return new GetPlanificationByIdCommand(get);
    }

    /**
     * Metodo para instaciar el comando UpdatePlanificacion
     * @param update Objeto Entity
     * @return Instancia del comando
     * @see UpdatePlanificationCommand
     * @see Entity
     */
    public static UpdatePlanificationCommand instanciateUpdatePlanificationCmd(Entity update) {
        return new UpdatePlanificationCommand(update);
    }

    //fin modulo 7

}
