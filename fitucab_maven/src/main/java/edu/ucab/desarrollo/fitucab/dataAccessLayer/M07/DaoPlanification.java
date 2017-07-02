package edu.ucab.desarrollo.fitucab.dataAccessLayer.M07;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Planification;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by jaorr on 30/06/17.
 */
public class DaoPlanification extends Dao implements IDaoPlanification {

    private final int QUERY_OK = 0;
    private final int MONDAY = 0;
    private final int TUESDAY = 1 ;
    private final int WEDNESDAY = 2;
    private final int THURSDAY = 3;
    private final int FRIDAY = 4;
    private final int SATURDAY = 5;
    private final int SUNDAY = 6;
    private Connection _conn;
    private boolean flag = false;



    public Entity delete(Entity planificationEntity) {

        final String query = "SELECT * FROM m7_elimina_actividad(?, ?)";
        Planification planification = (Planification) planificationEntity;
        try {
            _conn = Dao.getBdConnect();
            PreparedStatement stm = _conn.prepareStatement(query);
            stm.setInt(1, planification.get_id());
            stm.setInt(2, planification.get_user());

            stm.executeQuery();
            planificationEntity.set_errorCode(QUERY_OK);
        } catch (BdConnectException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(e.ERROR_CODE);
            planificationEntity.set_errorMsg(e.toString());
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(500);
            planificationEntity.set_errorMsg(e.toString());
        }
        finally {
            if (!flag)
                Dao.closeConnection();
            return planificationEntity;
        }
    }

    public Entity create(Entity planificationEntity) throws AddException {

        final String query = "SELECT * FROM m7_inserta_actividad(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Planification planification = (Planification) planificationEntity;
        try {
            _conn = Dao.getBdConnect();
            PreparedStatement stm = _conn.prepareStatement(query);
            stm.setDate(1, Date.valueOf(planification.get_startDate()));
            stm.setDate(2, Date.valueOf(planification.get_endDate()));
            stm.setTime(3, Time.valueOf(planification.get_startTime()));
            stm.setTime(4, Time.valueOf(planification.get_duration()));
            stm.setBoolean(5, planification.get_days()[MONDAY]);
            stm.setBoolean(6, planification.get_days()[TUESDAY]);
            stm.setBoolean(7, planification.get_days()[WEDNESDAY]);
            stm.setBoolean(8, planification.get_days()[THURSDAY]);
            stm.setBoolean(9, planification.get_days()[FRIDAY]);
            stm.setBoolean(10, planification.get_days()[SATURDAY]);
            stm.setBoolean(11, planification.get_days()[SUNDAY]);
            stm.setInt(12, planification.get_user());
            stm.setInt(13, planification.get_sport());
            stm.executeQuery();
            planificationEntity.set_errorCode(QUERY_OK);
        } catch (BdConnectException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(e.ERROR_CODE);
            planificationEntity.set_errorMsg(e.toString());
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(500);
            planificationEntity.set_errorMsg(e.toString());
        }
        finally {
            if (!flag)
                Dao.closeConnection();
            return planificationEntity;
        }

    }

    public Entity read(Entity planificationEntity) {

        final String query = "SELECT * FROM m7_get_actividad(?)";
        Planification planification = (Planification) planificationEntity;
        ArrayList<Entity> planificationList;
        try {
            _conn = Dao.getBdConnect();
            PreparedStatement stm = _conn.prepareStatement(query);
            stm.setInt(1, planification.get_user());
            boolean[] days = new boolean[7];
            ResultSet rs = stm.executeQuery();
            planificationList = new ArrayList<Entity>();
            while (rs.next()) {
                Planification aux = new Planification();
                //armar respuesta


            }
            planificationEntity.set_errorCode(QUERY_OK);
        } catch (BdConnectException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(e.ERROR_CODE);
            planificationEntity.set_errorMsg(e.toString());
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(500);
            planificationEntity.set_errorMsg(e.toString());
        }
        finally {
            if (!flag)
                Dao.closeConnection();
            return planificationEntity;
        }
    }

    public Entity update(Entity planificationEntity) {

        final String query = "SELECT * FROM m7_edita_actividad(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Planification planification = (Planification) planificationEntity;
        try {
            _conn = Dao.getBdConnect();
            PreparedStatement stm = _conn.prepareStatement(query);
            stm.setInt(1, planification.get_id());
            stm.setDate(2, Date.valueOf(planification.get_startDate()));
            stm.setDate(3, Date.valueOf(planification.get_endDate()));
            stm.setTime(4, Time.valueOf(planification.get_startTime()));
            stm.setTime(5, Time.valueOf(planification.get_duration()));
            stm.setBoolean(6, planification.get_days()[MONDAY]);
            stm.setBoolean(7, planification.get_days()[TUESDAY]);
            stm.setBoolean(8, planification.get_days()[WEDNESDAY]);
            stm.setBoolean(9, planification.get_days()[THURSDAY]);
            stm.setBoolean(10, planification.get_days()[FRIDAY]);
            stm.setBoolean(11, planification.get_days()[SATURDAY]);
            stm.setBoolean(12, planification.get_days()[SUNDAY]);
            stm.setInt(13, planification.get_user());
            stm.setInt(14, planification.get_sport());
            stm.executeQuery();
            planificationEntity.set_errorCode(QUERY_OK);
        } catch (BdConnectException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(e.ERROR_CODE);
            planificationEntity.set_errorMsg(e.toString());
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(500);
            planificationEntity.set_errorMsg(e.toString());
        }
        finally {
            if (!flag)
                Dao.closeConnection();
            return planificationEntity;
        }
    }
}
