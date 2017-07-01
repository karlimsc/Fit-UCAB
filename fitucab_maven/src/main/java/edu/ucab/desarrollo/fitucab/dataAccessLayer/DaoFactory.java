package edu.ucab.desarrollo.fitucab.dataAccessLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M09.DaoGaming;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M10.DaoWater;

/**
 * Fabrica para instanciar los DAO
 */
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CreateUserCommand;

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
     * @param entidad
     * @return dao de entrenamiento
     */

    public static DaoTraining instanceDaoTraining( Entity entidad )
    {
        return new DaoTraining( entidad );
    }

    public static Dao instanceDaoGaming() { return  new DaoGaming(); }

    //MODULO 10

    public static DaoWater instanceDaoWater(Entity entidad )
    {
        return new DaoWater( entidad );
    }

    //FIN MODULO 10
}
