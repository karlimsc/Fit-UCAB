package edu.ucab.desarrollo.fitucab.dataAccessLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;

public class DaoFactory
{

    /**
     * metodo para instanciar Dao Usuario
     * @param
     * @return Dao Usuario
     */
    public static DaoUser instanceDaoUser( )
    {
        return new DaoUser(  );
    }
}