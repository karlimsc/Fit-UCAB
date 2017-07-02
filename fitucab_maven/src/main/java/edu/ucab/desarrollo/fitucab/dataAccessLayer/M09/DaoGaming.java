package edu.ucab.desarrollo.fitucab.dataAccessLayer.M09;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.EntityFactory;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.common.exceptions.MessageException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
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


    public void achieveChallenge(int id, List<Entity> challenges) {
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
        } catch (SQLException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
    }

    public Entity fillChart(int id) {
        try {
            Statement st = _conn.createStatement();
            ResultSet rs = st.executeQuery("select * from m09_getachieveanduanchievechallengebyid("+id+")");
            Entity achieve = EntityFactory.createChallenge();
            while (rs.next()){
      //          achieve = EntityFactory.createChallenge(rs.getLong("achieve"), rs.getLong("unachieve"));
            }
            return achieve;
        } catch (SQLException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
        return null;
    }

    public Entity score(int id) {
        try {
            Statement st = _conn.createStatement();
            ResultSet rs = st.executeQuery("select m09_getscorechallengebyid("+id+")");
            Entity level = EntityFactory.createChallenge();
            while (rs.next()){
             //   level = EntityFactory.createChallenge(rs.getInt("m09_getscorechallengebyid"));
            }
            return level;
        } catch (SQLException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
        return null;
    }

    public Entity levelUp(int id) {
        try {
            Statement st = _conn.createStatement();
            ResultSet rs = st.executeQuery("select m09_getscorechallengebyid("+id+")");
            Entity level = EntityFactory.createChallenge();
            while (rs.next()){
             //   level = EntityFactory.createChallenge(rs.getInt("m09_getscorechallengebyid"));
            }
            return level;
        } catch (SQLException e) {
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        } catch (Exception e){
            MessageException error = new MessageException(e, this.getClass().getSimpleName(),
                    Thread.currentThread().getStackTrace()[1].getMethodName());
            logger.error("Error: ", error.toString());
        }
        return null;
    }

    @Override
    public void Create(Entity e) {

    }
}
