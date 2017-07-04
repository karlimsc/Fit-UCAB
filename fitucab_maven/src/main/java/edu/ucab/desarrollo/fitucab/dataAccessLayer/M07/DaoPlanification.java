package edu.ucab.desarrollo.fitucab.dataAccessLayer.M07;

import edu.ucab.desarrollo.fitucab.common.entities.Entity;
import edu.ucab.desarrollo.fitucab.common.entities.Planification;
import edu.ucab.desarrollo.fitucab.common.exceptions.AddException;
import edu.ucab.desarrollo.fitucab.common.exceptions.BdConnectException;
import edu.ucab.desarrollo.fitucab.dataAccessLayer.Dao;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Dao para el manejo de la base datos correspondiete a la tabla de planificacion
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
    private static org.slf4j.Logger _logger = LoggerFactory.getLogger(DaoPlanification.class);



    /**
     * Metodo para eliminar un registro en la base de datos
     * @param planificationEntity Objeto que contiene el id de la
     *                           planificacion y el id del usuario
     * @return El objeto de entrada con el estatus de ejecucion(200 si la ejecucion
     * fue correcta)
     * @see Planification
     * @see Entity
     */
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
                _logger.debug("Se elimino el registro: "+ planification.get_id() +"de forma exitosa. planification");
            }
            else {
                planificationEntity.set_errorCode(NOT_FOUND);
                planificationEntity.set_errorMsg("No se encontro el registro el registro a eliminar");
                _logger.debug("No se encontro el registro :" + planification.get_id()+ ". planification");
            }
        } catch (BdConnectException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(e.ERROR_CODE);
            planificationEntity.set_errorMsg(e.toString());
            _logger.error("Error en la conexion a la base de datos" + e.ERROR_MSG + ". planification");
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(500);
            planificationEntity.set_errorMsg(e.toString());
            _logger.error("Error en la conexion a la base de datos" + e.toString() + ". planification");
        }
        catch (Exception e){
            e.printStackTrace();
            planificationEntity.set_errorCode(500);
            planificationEntity.set_errorMsg(e.toString());
            _logger.error("Error durante la eliminacion " + e.toString() + ". planification");
        }
        finally {
            if (!flag)
                Dao.closeConnection();
            return planificationEntity;
        }
    }

    /**
     * Metodo para la creacion de una planificacion
     * @param planificationEntity Objeto que contiene los datos que se insertaran
     * @return El objeto de entrada con el estatus de ejecucion(200 si la ejecucion
     * fue correcta)
     * @see Planification
     * @see Entity
     */
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
            _logger.debug("Se inserto el registro de forma exitosa. planification");
        } catch (BdConnectException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(e.ERROR_CODE);
            planificationEntity.set_errorMsg(e.toString());
            _logger.error("Error en la conexion a la base de datos planification" + e.ERROR_MSG);
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(500);
            planificationEntity.set_errorMsg(e.toString());
            _logger.error("Error en la conexion a la base de datos planification" + e.toString());
        }catch (Exception e){
            e.printStackTrace();
            planificationEntity.set_errorCode(500);
            planificationEntity.set_errorMsg(e.toString());
            _logger.error("Error durante la creacion del registro planificatio " + e.toString());
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

    /**
     * Metodo para obtener todas las planificaciones asociadas a un usuario
     * @param planificationEntity Objeto que contiene el id del usuario por el que
     *                            se desea realizar la busqueda
     * @return Lista de objetos Planification
     * @see Planification
     * @see Entity
     */
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
                _logger.debug("No se encontraron registros para el usuario: " + planification.get_user() + ". planification");
            } else{
                planificationEntity.set_errorCode(QUERY_OK);
                planification.set_errorMsg("Busqueda realizada exitosamente");
                respuesta.add(planification);
                _logger.debug("Busqueda realizada de forma exitosa. planification");
            }

        } catch (BdConnectException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(e.ERROR_CODE);
            planificationEntity.set_errorMsg(e.toString());
            respuesta.add((Planification)planificationEntity);
            _logger.error("Error en la conexion a la base de datos" + e.ERROR_MSG + ". planification");
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(500);
            planificationEntity.set_errorMsg(e.toString());
            respuesta.add((Planification)planificationEntity);
            _logger.error("Error en la conexion a la base de datos" + e.toString() + ". planification");
        } catch (Exception e){
            e.printStackTrace();
            planificationEntity.set_errorCode(500);
            planificationEntity.set_errorMsg(e.toString());
            _logger.error("Error durante la busqueda del registro planification " + e.toString());
        }

        finally {
            if (!flag)
                Dao.closeConnection();
            return respuesta;
        }
    }

    /**
     * Metodo para la actualizacion de una planificacion en la base de datos
     * @param planificationEntity Objeto que contiene los datos para actualizar
     *                            el registro
     * @return El objeto de entrada con el estatus de ejecucion(200 si la ejecucion
     * fue correcta)
     * @see  Planification
     * @see  Entity
     */
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
                _logger.debug("Se actualizo el registro: "+ planification.get_id() +" de forma exitosa. planification");
            }
            else {
                planificationEntity.set_errorCode(NOT_FOUND);
                planificationEntity.set_errorMsg("No se encontro el registro que desea actualizar");
                _logger.debug("No se encontro el registro para actualizar: "+ planification.get_id() +". planification");

            }

        } catch (BdConnectException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(e.ERROR_CODE);
            planificationEntity.set_errorMsg(e.toString());
            _logger.error("Error en la conexion a la base de datos" + e.ERROR_MSG + ". planification");
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
            planificationEntity.set_errorCode(500);
            planificationEntity.set_errorMsg(e.toString());
            _logger.error("Error en la conexion a la base de datos" + e.toString() + ". planification");
        }catch (Exception e){
            e.printStackTrace();
            planificationEntity.set_errorCode(500);
            planificationEntity.set_errorMsg(e.toString());
            _logger.error("Error durante la actualizacion del registro planification " + e.toString());
        }
        finally {
            if (!flag)
                Dao.closeConnection();
            return planificationEntity;
        }
    }

    /**
     * Metodo que arma la lista de objetos pertenecientes a la planificacion de un
     * usuario
     * @param rs Objeto que contiene los datos de la busqueda en la base de datos
     * @param userId Id del usuario al que pertenece la planificacion
     * @return Lista de planificacion
     * @throws SQLException
     * @see Planification
     */

    private ArrayList<Planification> armarRespuesta(ResultSet rs, int userId) throws SQLException{
        ArrayList<Planification> planificationList;
        Planification aux;
        boolean[] days = new boolean[7];
        planificationList = new ArrayList<Planification>();
        _logger.debug("Id usuario en armarRespuesta. Planification: " + userId);
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

    /**
     * Metodo que verifica la existencia de un registro en la base de datos
     * @param planificationId id de la planificacion
     * @param userId usuario al que pertene la planificacion
     * @param conn objeto de la conexion a la base de datos
     * @return true si existe, false si no existe
     * @throws SQLException
     */

    private boolean exists(int planificationId, int userId, Connection conn) throws SQLException {
        _logger.debug("Id usuario en exists. Planification: " + userId);
        _logger.debug("Id planification en exists. Planification: " + userId);
        final String query = "SELECT * FROM m7_get_actividad_por_id(?,?)";
        PreparedStatement stm = conn.prepareStatement(query);
        stm.setInt(1, planificationId);
        stm.setInt(2, userId);
        ResultSet rs = stm.executeQuery();
        if (rs.next()){
            _logger.debug("Si existen registros para los id suministrados");
            return true;
        }
        _logger.debug("No existen registros para los id suministrados");
        return false;
    }
}
