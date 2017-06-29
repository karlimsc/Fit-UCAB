package edu.ucab.desarrollo.fitucab.dataAccessLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M01.CreateUserCommand;

public class DaoFactory
{
    static public DaoUser instanciateDaoUser(Entity user) {
        return new DaoUser(user);
    }
}