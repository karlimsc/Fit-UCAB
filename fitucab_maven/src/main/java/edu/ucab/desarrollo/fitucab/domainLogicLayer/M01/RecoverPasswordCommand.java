package edu.ucab.desarrollo.fitucab.domainLogicLayer.M01;

import com.google.gson.Gson;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.exceptions.M01.RecoveryPassException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.DaoFactory;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.M01.DaoUser;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.Command;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.AchieveChallengeCommand;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created by estefania on 29/06/2017.
 */
public class RecoverPasswordCommand extends Command {

    private String _email;
    private String _response;
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(AchieveChallengeCommand.class);
    private Gson gson;


    /**
     * Constructor de la clase
     * @param _email String
     */
    public RecoverPasswordCommand(String _email) {
        this._email = _email;
    }

    public void execute() throws RecoveryPassException, NullPointerException,SQLException, InstantiationException{

            Dao _dao = DaoFactory.instanciateDaoUser();
            DaoUser testMailDao;
            testMailDao = (DaoUser)_dao;

            //TODO:La respuesta es un string del json del user con status ok.
            this._response=testMailDao.testEmail(_email);

            logger.debug("Debug: ", "Realizó el Try en RecoverPassCommand");
            System.out.print("Debug: Realizó el Try en RecoverPassCommand");
            System.out.print("Debug:Rsponse"+ this._response);

    }

    public String get_response() {
        return _response;
    }

    public Entity Return(){
        return null;
    }
}
