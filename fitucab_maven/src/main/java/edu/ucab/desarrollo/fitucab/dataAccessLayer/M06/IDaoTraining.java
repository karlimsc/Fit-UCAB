package edu.ucab.desarrollo.fitucab.dataAccessLayer.M06;

import edu.ucab.desarrollo.fitucab.common.exceptions.*;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;

import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by root on 24/06/17.
 */
public interface IDaoTraining extends IDao {


    public Boolean delete(Entity e) throws DeleteException;
    public LinkedList<Entity> listAll(Entity e) throws  ListAllException;
    public Entity trainingDetail(Entity e) throws ListByIdException;
    public Entity activateTraining(Entity e, Entity a) throws ActiveTrainingException;
    public Boolean shareTraining(Entity e) throws ShareException;
    public ArrayList<Entity> listTrainingsShared(Entity e);

}
