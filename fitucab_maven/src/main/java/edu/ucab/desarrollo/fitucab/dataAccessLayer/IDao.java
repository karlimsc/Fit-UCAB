package edu.ucab.desarrollo.fitucab.dataAccessLayer;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;

import java.util.List;

public interface IDao
{

    abstract Entity create(Entity e) throws AddException;

    abstract Entity read(Entity e);

    abstract Entity update(Entity e);



}