package edu.ucab.desarrollo.fitucab.dataAccessLayer.M07;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

/**
 * Created by jaorr on 30/06/17.
 */
public interface IDaoPlanification extends IDao {

    public Entity delete(Entity id);
}
