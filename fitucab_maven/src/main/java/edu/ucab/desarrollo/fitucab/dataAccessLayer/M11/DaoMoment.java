package edu.ucab.desarrollo.fitucab.dataAccessLayer.M11;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Moment;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;

import java.util.ArrayList;

/**
 * Created by charbel on 24/06/2017.
 */
public class DaoMoment extends Dao implements iDaoMoment {

    ArrayList<Moment> jsonArray;
    @Override
    public void Create(Entity e) {

    }

    @Override
    public Entity create(Entity e) throws AddException {
        return null;
    }

    @Override
    public Entity read(Entity e) {


        return e;
    }

    @Override
    public Entity update(Entity e) {
        return null;
    }



}
