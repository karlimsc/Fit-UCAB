package Domain;

/**
 * Created by root on 26/05/17.
 */
public class Detail {

    private int _id;
    private int _challengeId;
    private String _start;
    private String _end;
    private boolean _ended;
    private int _position;

    /**
     * //Constructor
     */
    public Detail() {
        this._id = 0;
        this._challengeId = 0;
        this._start = "";
        this._end = "";
        this._ended = true;
        this._position = 0;
    }

    /**
     * //Get del id de la clase detalle
     * @return id que se recibe del detalle
     */
    public int get_id() {
        return _id;
    }

    /**
     *
     * @param _id que se envia del detalle
     */
    public void set_id(int _id) {
        this._id = _id;
    }
    /**
     * //Get del id del challenge que tiene el detalle
     * @return get del id del challenge con el cual esta relacionado el detalle
     */
    public int get_challengeId() {
        return _challengeId;
    }

    /**
     * //Set del id del challenge que tiene el detalle
     * @param _challengeId set del id del challenge con el cual esta relacionado el detalle
     */
    public void set_challengeId(int _challengeId) {
        this._challengeId = _challengeId;
    }

    /**
     * //Get del comienzo del detalle
     * @return Get de la fecha cuando comienza el challenge
     */
    public String get_start() {
        return _start;
    }

    /**
     * Set del comienzo del detalle
     * @param _start Set de la fecha de comienzo del challenge
     */
    public void set_start(String _start) {
        this._start = _start;
    }

    /**
     * //Get del final del detalle
     * @return Get de la fecha de final del detalle
     */
    public String get_end() {
        return _end;
    }

    /**
     * //Set del final del detalle
     * @param _end Set de la fecha de fin del detalle
     */
    public void set_end(String _end) {
        this._end = _end;
    }

    /**
     * //Set del boolean si esta terminado
     * @return Get de un boolean que indica si esta o no terminado el challenge
     */
    public boolean is_ended() {
        return _ended;
    }

    /**
     * //Set del detalle terminado
     * @param _ended Set que se introduce si esta terminado o no el challenge
     */
    public void set_ended(boolean _ended) {
        this._ended = _ended;
    }

    /**
     * //Get de la posicion del detalle
     * @return Get de la posicion del usuario con el challenge
     */
    public int get_position() {
        return _position;
    }

    /**
     * //Set de la posicion del detalle
     * @param _position Set de la posicion en la que el usuario esta con el challenge
     */
    public void set_position(int _position) {
        this._position = _position;
    }
}
