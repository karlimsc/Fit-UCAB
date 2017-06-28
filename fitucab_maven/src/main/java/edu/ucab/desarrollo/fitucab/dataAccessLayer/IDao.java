package edu.ucab.desarrollo.fitucab.dataAccessLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;

public interface IDao
{

    abstract void Create(Entity e) throws Exception;
}