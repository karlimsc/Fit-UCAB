package edu.ucab.desarrollo.fitucab.dataAccessLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;

/**
 * Fabrica para instanciar los DAO
 */
public class DaoFactory
{

    /**
     * metodo para instanciar el dao de entrenamiento
     * @param
     * @return dao de entrenamiento
     */
    public static DaoTraining instanceDaoTraining( Entity entity )
    {
        return new DaoTraining(entity);
    }

}
