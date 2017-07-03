package edu.ucab.desarrollo.fitucab.dataAccessLayer.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.IDao;

import java.util.List;

/**
 *
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public interface IDaoGaming extends IDao {

    public void achieveChallenge();

    public Entity fillChart(int id);

    public Entity score(int id);

    public Entity levelUp(int id);

}
