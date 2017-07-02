package edu.ucab.desarrollo.fitucab.dataAccessLayer.M02;

import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.M02.CreateHomeException;

import java.sql.SQLException;

/**
 * Created by cesareduardo on 02/07/2017.
 */
public interface IDaoUpdatePerfil {

    public boolean read() throws CreateHomeException;

    public void UpdateName(String name) throws BdConnectException, SQLException;
    public void UpdateEmail(String email);
    public void UpdatePhone(String phone);

}
