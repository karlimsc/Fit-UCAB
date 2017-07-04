package edu.ucab.desarrollo.fitucab.dataAccessLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M02.DaoHome;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M03.DaoFriendship;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M03.DaoNearMe;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M09.DaoGaming;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M10.DaoWater;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M11.DaoDiet;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M11.DaoFood;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M11.DaoMoment;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M07.DaoPlanification;

/**
 * Fabrica para instanciar los DAO
 */

public class DaoFactory
{

    //MODULO 1

    /**
     * Metodo para instanciar DaoUser
     * @param user
     * @return
     */

    static public Dao instanciateDaoUser(Entity user) {
        return new DaoUser(user);
    }

    static public Dao instanciateDaoUser() {
        return new DaoUser();
    }
    //MODULO 1

    /**
     * metodo para instanciar el dao de entrenamiento
     * @param entity
     * @return dao de entrenamiento
     */

    public static DaoTraining instanceDaoTraining( Entity entity )
    {
        return new DaoTraining(entity);
    }

    //MODULO 09

    /**
     * Metodo para instanciar el dao de gamification
     * @return Dao Gaming
     */
    public static Dao instanceDaoGaming() { return  new DaoGaming(); }
    //FIN MODULO 09

    //MODULO 2

    /**
     * Metodo que instancia el DaoHome con una entity
     * @param entity
     * @return
     */
    public static DaoHome instanceDaoHome(Entity entity) {
        return new DaoHome(entity);
    }

    /**
     * Metodo que instancia el DaoUser con un id
     * @param id
     * @return
     */
    public static DaoUser instanceDaoUser(int id) {

        return new DaoUser(id);
    }

    //MODULO 10

    public static DaoWater instanceDaoWater(Entity entidad )
    {
        return new DaoWater( entidad );
    }

    //FIN MODULO 10
    //Modulo11

    public static DaoFood iniciarDaoFood(){return new DaoFood();}

    public static DaoDiet iniciarDaoDiet(){return new DaoDiet();}

    public static DaoMoment iniciarDaoMoment(){return  new DaoMoment();}

    /**
     * Metodo que instancia el DaoUpdateUser
     * @param id
     * @param username
     * @param phone
     * @param email
     * @return DaoUpdatePerfil
     */
    public static DaoUser instanceDaoUpdateUser(int id, String username, String phone, String email){
        return new DaoUser (id,username,phone,email);
    }
    //modulo 3

    public static Dao instanceDaoFriendship() { return  new DaoFriendship(); }

    public static DaoFriendship instanceDaoFriendship(Entity entidad )
    {
        return new DaoFriendship( entidad );
    }

    public static Dao instanceDaoNearMe(){ return new DaoNearMe();}

    public static DaoNearMe instanceDaoNearMe(Entity entidad){ return new DaoNearMe(entidad);}
    //fin modulo 3


     //MODULO 07

    public static DaoPlanification instanciateDaoPlanification() {
        return new DaoPlanification();
    }

    //FIN MODULO 07

}

