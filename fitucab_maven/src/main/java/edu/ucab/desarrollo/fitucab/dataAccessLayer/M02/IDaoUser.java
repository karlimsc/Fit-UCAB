package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.entities.*;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.GetUserException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.*;

import java.util.*;

/**
 * Created by root on 29/06/17.
 */
public interface IDaoUser extends IDao {

    public Entity read(int id) throws GetUserException;
    public Date Fecha(int id) ;

}
