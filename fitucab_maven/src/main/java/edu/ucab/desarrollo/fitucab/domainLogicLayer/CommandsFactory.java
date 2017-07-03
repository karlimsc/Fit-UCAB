package edu.ucab.desarrollo.fitucab.domainLogicLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M03.DaoFriendship;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CheckUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CreateUserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.RecoverPasswordCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M02.HomeCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M02.UserCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M06.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.FillChartCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.LevelUpCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.ScoreCommand;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M10.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M11.*;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M03.*;
import sun.misc.Request;

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

    //FIN MODULO 2

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

    //Modulo 3

    /**
     * Metodo para instanciar el comando RequestFriendship con todos sus atributos

     * @param userOne
     * @param userTwo
     * @return el comando RequestFriendship
     */
    static public Command instanciateRequestFriendshipCmd(Dao friendship, String userOne, String userTwo){ return new RequestFriendshipCommand(friendship, userOne, userTwo); }

    /**
     * Metodo para instanciar el comando RequestFriendship con todos sus atributos
     * @param friendship
     * @param userOne
     * @param userTwo
     * @return el comando RequestFriendship
     */
    static public Command instanciateUpdateFriendshipCmd(Dao friendship, String userOne, String userTwo){ return new RequestFriendshipCommand(friendship, userOne, userTwo); }

    /**
     * Metodo para instanciar el comando GetFriends
     * @param id es el id del usuario
     * @param action identifica si son amigos o no
     * @return el comando GetFriends
     */

    public static GetFriendsCommand instatiateGetFriendsCmd(String id, String action){ return new GetFriendsCommand(id, action); }

    public static GetContactsCommand instatiateGetContactsCmd(String id, String contacts) { return new GetContactsCommand(id, contacts);}

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


}




