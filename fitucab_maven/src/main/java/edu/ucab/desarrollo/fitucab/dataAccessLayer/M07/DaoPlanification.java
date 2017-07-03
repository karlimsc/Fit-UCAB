package edu.ucab.desarrollo.fitucab.dataAccessLayer.M07;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Planification;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import javafx.print.PageLayout;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by jaorr on 30/06/17.
 */
public class DaoPlanification extends Dao implements IDaoPlanification {

    private final int QUERY_OK = 200;
    private final int NOT_FOUND = 404;
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
            if (exists(planification.get_id(), planification.get_user(), _conn)) {
                PreparedStatement stm = _conn.prepareStatement(query);
                stm.setInt(1, planification.get_id());
                stm.setInt(2, planification.get_user());

                stm.executeQuery();
                planificationEntity.set_errorCode(QUERY_OK);
                planificationEntity.set_errorMsg("Data eliminada exitosamente");
            }
            else {
                planificationEntity.set_errorCode(NOT_FOUND);
                planificationEntity.set_errorMsg("No se encontro el registro el registro a eliminar");
            }
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


    public Entity create(Entity planificationEntity) {

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
            planificationEntity.set_errorMsg("Data insertada exitosamente");
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

    @Override
    public Entity read(Entity e) {
        return null;
    }

    @Override
    public ArrayList<Planification> getPlanificationByUser(Entity planificationEntity) {

        final String query = "SELECT * FROM m7_get_actividad(?)";
        Planification planification = (Planification) planificationEntity;
        ArrayList<Planification> respuesta = new ArrayList<>();

        try {
            _conn = Dao.getBdConnect();
            PreparedStatement stm = _conn.prepareStatement(query);
            stm.setInt(1, planification.get_user());
            ResultSet rs = stm.executeQuery();

            respuesta = armarRespuesta(rs, planification.get_user());
            if (respuesta.isEmpty()) {
                planification.set_errorCode(NOT_FOUND);
                planification.set_errorMsg("El usuario no tiene registros asociados");
                respuesta.add(planification);
            } else{
                planificationEntity.set_errorCode(QUERY_OK);
                planification.set_errorMsg("Busqueda realizada exitosamente");
                respuesta.add(planification);
            }

        } catch (BdConnectException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(e.ERROR_CODE);
            planificationEntity.set_errorMsg(e.toString());
            respuesta.add((Planification)planificationEntity);
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(500);
            planificationEntity.set_errorMsg(e.toString());
            respuesta.add((Planification)planificationEntity);
        }
        finally {
            if (!flag)
                Dao.closeConnection();
            return respuesta;
        }
    }

    public Entity update(Entity planificationEntity) {

        final String query = "SELECT * FROM m7_edita_actividad(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Planification planification = (Planification) planificationEntity;
        try {
            _conn = Dao.getBdConnect();
            if (exists(planification.get_id(), planification.get_user(), _conn)){
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
                planificationEntity.set_errorMsg("Se actualizo el registro exitosamente");
            }
            else {
                planificationEntity.set_errorCode(NOT_FOUND);
                planificationEntity.set_errorMsg("No se encontro el registro que desea actualizar");
            }

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

    private ArrayList<Planification> armarRespuesta(ResultSet rs, int userId) throws SQLException{
        ArrayList<Planification> planificationList;
        Planification aux;
        boolean[] days = new boolean[7];
        planificationList = new ArrayList<Planification>();
        while(rs.next()){
            aux  = new Planification();
            aux.set_id(rs.getInt("id_planificacion"));
            aux.set_startDate(rs.getDate("fecha_inicio").toLocalDate());
            aux.set_endDate(rs.getDate("fecha_fin").toLocalDate());
            aux.set_startTime(rs.getTime("hora_inicio").toLocalTime());
            aux.set_duration(rs.getTime("duracion").toLocalTime());
            days[MONDAY] = rs.getBoolean("lunes");
            days[TUESDAY] = rs.getBoolean("martes");
            days[WEDNESDAY] = rs.getBoolean("miercoles");
            days[THURSDAY] = rs.getBoolean("jueves");
            days[FRIDAY] = rs.getBoolean("viernes");
            days[SATURDAY] = rs.getBoolean("sabado");
            days[SUNDAY] = rs.getBoolean("domingo");
            aux.set_days(days);
            aux.set_sport(rs.getInt("deporte"));
            aux.set_user(userId);
            planificationList.add(aux);
        }

        return planificationList;
    }

    private boolean exists(int planificationId, int userId, Connection conn) throws SQLException {
        final String query = "SELECT * FROM m7_get_actividad_por_id(?,?)";
        PreparedStatement stm = conn.prepareStatement(query);
        stm.setInt(1, planificationId);
        stm.setInt(2, userId);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            return true;
        }
        return false;
    }
}
