package edu.ucab.desarrollo.fitucab.dataAccessLayer.M07;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Planification;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.util.ArrayList;

/**
 * Created by jaorr on 30/06/17.
 */
public interface IDaoPlanification extends IDao {

    public Entity delete(Entity id);
    public ArrayList<Planification> getPlanificationByUser(Entity id);
}
