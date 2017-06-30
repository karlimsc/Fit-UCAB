package edu.ucab.desarrollo.fitucab.dataAccessLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M06.DaoTraining;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M10.DaoWater;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M11.DaoFood;

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

    //MODULO 10

    public static DaoWater instanceDaoWater(Entity entidad )
    {
        return new DaoWater( entidad );
    }

    //FIN MODULO 10

    //Modulo11

    public static DaoFood iniciarDaoFood(Entity entity){return new DaoFood(entity);}


    }

