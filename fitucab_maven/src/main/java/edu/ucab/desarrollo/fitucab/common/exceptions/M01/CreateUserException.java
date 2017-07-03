package edu.ucab.desarrollo.fitucab.common.exceptions.M01;

import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import org.slf4j.LoggerFactory;

/**
 * Created by estefania on 02/07/2017.
 */
public class CreateUserException extends M01_UserException {

    private int _code = 554;
    private String _class;
    private String _specificException;
    private static org.slf4j.Logger _logger = LoggerFactory
            .getLogger(CreateUserException.class);

    /**
     * Metodo contructor para lanzar excepcion al crear usuario
     * @param _class
     * @param _specificException
     */
    public CreateUserException(String _class, String _specificException, User userFail) {
        super(userFail);
        this._class = _class;
        this._specificException = _specificException;

        MessageException error = new MessageException(this, _class,
                _specificException);
        _logger.error("Error: ", error.toString());
        System.out.print("EL USER STATUS ES " + userFail.get_status());
        _logger.debug("EL USER STATUS ES " + userFail.get_status());
    }

    /**
     * Sobreescritura del Metodo toString heredado de la clase Exception
     * @return super.toString();
     */
    @Override
    public String toString() {
        StringBuilder _strB = new StringBuilder(_code);
        _strB.append(_class);
        _strB.append(_specificException);
        _strB.append(super.toString());
        return super.toString();
    }
}
