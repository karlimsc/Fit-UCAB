package edu.ucab.desarrollo.fitucab.dataAccessLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M02.*;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;

/**
 * Fabrica para instanciar los DAO
 */
public class DaoFactory
{

    /**
     * metodo para instanciar el dao de entrenamiento
     * @param entidad
     * @return dao de entrenamiento
     */
    public static DaoTraining instanceDaoTraining( Entity entidad )
    {
        return new DaoTraining( entidad );
    }

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

}
