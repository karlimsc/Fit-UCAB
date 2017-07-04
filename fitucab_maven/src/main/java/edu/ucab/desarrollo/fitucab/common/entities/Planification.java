package edu.ucab.desarrollo.fitucab.common.entities;

import java.time.LocalTime;
import java.time.LocalDate;

/**
 * Clase para el manejo de las planificaciones
 */
public class Planification extends Entity {

    private LocalDate _startDate;
    private LocalDate _endDate;
    private LocalTime _startTime;
    private LocalTime _duration;
    private boolean[] _days = new boolean[7]; // 0 es lunes, 6 es domingo
    private int _user;
    private int _sport;


    /**
     * Construstor con todos los parametros, se recomienda su uso cuando se desea
     * actualizar un registro en la base de datos
     * @param id Id del registro en la base de datos
     * @param startDate Fecha de inicio de la planificacion
     * @param endDate Fecha fin de la planificacion
     * @param startTime Hora del dia de inicio en la cual empezara la planificacion
     * @param duration Duracion a partir de startTime de la planificacion
     * @param days Array de 7 posiciones que respresenta los dias de la semanas
     * @param user Id del usuario al que pertenece la planificacion
     * @param sport Id del sport al que pertenece la planificacion
     */
    public Planification(int id, LocalDate startDate, LocalDate endDate, LocalTime startTime,
                         LocalTime duration, boolean[] days, int user, int sport) {

        super(id);
        this._startDate = startDate;
        this._endDate = endDate;
        this._startTime = startTime;
        this._duration = duration;
        this._days = days;
        this._user = user;
        this._sport = sport;

    }

    /**
     * Constructor sin id, se recomienda su uso cuando desee insertar en la
     * base de datos
     * @param startDate Fecha de inicio de la planificacion
     * @param endDate Fecha fin de la planificacion
     * @param startTime Hora del dia de inicio en la cual empezara la planificacion
     * @param duration Duracion a partir de startTime de la planificacion
     * @param days Array de 7 posiciones que respresenta los dias de la semanas
     * @param user Id del usuario al que pertenece la planificacion
     * @param sport Id del sport al que pertenece la planificacion
     */
    public Planification(LocalDate startDate, LocalDate endDate, LocalTime startTime,
                         LocalTime duration, boolean[] days, int user, int sport) {
        this._startDate = startDate;
        this._endDate = endDate;
        this._startTime = startTime;
        this._duration = duration;
        this._days = days;
        this._user = user;
        this._sport = sport;
    }

    /**
     * Contructor con dos parametros(id, usuario), se recomienda su uso cuando se
     * desea eliminar un registro en la base de datos
     * @param _id Id del registro en la base de datos
     * @param user Id del usuario al que pertenece la planificacion
     */

    public Planification(int _id, int user) {
        super(_id);
        this._user = user;
    }

    /**
     * Constructor con un parametro(usuario), se recomienda su uso cuando se
     * desse buscar registros pertenecientes a un usuario
     * @param user
     */
    public Planification(int user) {
        this._user = user;
    }

    /**
     * Constructor vacio
     */
    public Planification() {
    }

    /**
     * Getter de la fecha de inicio de la planificacion
     * @return _startDate
     */
    public LocalDate get_startDate() {
        return _startDate;
    }


    /**
     * Setter de la fecha de inicio de la planificacion
     * @param startDate
     */
    public void set_startDate(LocalDate startDate) {
        this._startDate = startDate;
    }

    /**
     * Getter de la fecha fin de la planificacion
     * @return _endDate
     */
    public LocalDate get_endDate() {
        return _endDate;
    }

    /**
     * Setter de la fecha fin de la planificacion
     * @param endDate
     */
    public void set_endDate(LocalDate endDate) {
        this._endDate = endDate;
    }

    /**
     * Getter de la hora de inicio de la planificacion
     * @return _startTime
     */
    public LocalTime get_startTime() {
        return _startTime;
    }

    /**
     * Setter de la hora de inicio de la planificacion
     * @param startTime
     */
    public void set_startTime(LocalTime startTime) {
        this._startTime = startTime;
    }

    /**
     * Getter de la duracion de la planificacion
     * @return _duration
     */
    public LocalTime get_duration() {
        return _duration;
    }

    /**
     * Setter de la duracion de la planificacion
     * @param duration
     */
    public void set_duration(LocalTime duration) {
        this._duration = duration;
    }

    /**
     * Getter de los dias de la planificacion
     * @return _days
     */
    public boolean[] get_days() {
        return _days;
    }

    /**
     * Setter de los dias de la planificacion
     * @param days
     */
    public void set_days(boolean[] days) {
        this._days = days;
    }

    /**
     * Getter del usuario de la planificacion
     * @return _user
     */
    public int get_user() {
        return _user;
    }

    /**
     * Setter del usuario de la planificacion
     * @param user
     */
    public void set_user(int user) {
        this._user = user;
    }

    /**
     * Getter del sport al que estara asociado la planificacion
     * @return _sport
     */
    public int get_sport() {
        return _sport;
    }

    /**
     * Setter del sport al que estara asociado la planificacion
     * @param sport
     */
    public void set_sport(int sport) {
        this._sport = sport;
    }
}
