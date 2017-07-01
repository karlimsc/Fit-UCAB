package edu.ucab.desarrollo.fitucab.dataAccessLayer.M09;

import ch.qos.logback.core.db.dialect.PostgreSQLDialect;
import edu.ucab.desarrollo.fitucab.common.entities.Challenge;
import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.CommandsFactory;
import edu.ucab.desarrollo.fitucab.domainLogicLayer.M09.ScoreCommand;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;

/**
 * Clase de Dao para el modulo M09 de gamificacion
 * @author David Garcia, Juan Mendez, Mario Salazar
 * @version 2.0
 */
public class DaoGaming extends Dao{

    final static org.slf4j.Logger logger = LoggerFactory.getLogger(DaoGaming.class);

    private Connection _conn;

    public DaoGaming() {
        try {
            _conn = Dao.getBdConnect();
        } catch (BdConnectException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
    }

    public Entity create(Entity e) throws AddException {
        return null;
    }

    public Entity read(Entity e) {
        return null;
    }

    public Entity update(Entity e) {
        return null;
    }


    public List<Entity> achieveChallenge(int id, List<Entity> challenges) {
        try {
            Statement st = _conn.createStatement();
            ResultSet rs = st.executeQuery("select * from m09_getachievechallengebyid("+id+")");
//            CallableStatement cs = _conn.prepareCall("{? = call m09_getachievechallengebyid(?)}");
//            cs.setInt(2,id);
//            cs.execute();
//            ResultSet rs = (ResultSet) cs.getObject(1);
            while (rs.next()){
                Entity challege = EntityFactory.createChallenge(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("score"));
                challenges.add(challege);
            }
            return challenges;
        } catch (SQLException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
        return null;
    }

    public Entity fillChart(int id) {
        try {
            Statement st = _conn.createStatement();
            ResultSet rs = st.executeQuery("m09_getachieveanduanchievechallengebyid("+id+")");
            Entity challege = null;
            while (rs.next()){
                challege = EntityFactory.createChallenge(rs.getInt("achieve"), rs.getInt("unachieve"));
            }
            return challege;
        } catch (SQLException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
        return null;
    }

    public Entity score(int id) {
        return null;
    }

    public Entity levelUp(int id) {
        return null;
    }
}
