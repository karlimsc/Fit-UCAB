package edu.ucab.desarrollo.fitucab.common.exceptions.M01;

import edu.ucab.desarrollo.fitucab.common.entities.User;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import org.slf4j.LoggerFactory;

/**
 * Created by karo on 03/07/17.
 */
public class RecoveryPassException extends M01_UserException {
    private int _code = 555;
    private String _class;
    private String _specificException;
    private static org.slf4j.Logger _logger = LoggerFactory
            .getLogger(LoginUserException.class);

     /* Metodo Constructor para lanzar excepcion en el Recovery
     * @param _class
     * @param _specificException
     */
    public RecoveryPassException (String _class, String _specificException, User userFail) {
        super(userFail);
        this._class = _class;
        this._specificException = _specificException;

        MessageException error = new MessageException(RecoveryPassException .this, this.getClass().getSimpleName(),
                _specificException);
        _logger.debug("Estatus Usuario " + super.userFail.get_status());
        _logger.debug("Constructor de LOGINEXCEPTION: ", _specificException + " " + error.toString());
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
