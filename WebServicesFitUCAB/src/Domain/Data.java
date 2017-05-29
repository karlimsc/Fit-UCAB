package Domain;

/**
 * Created by root on 10/05/17.
 */
public class Data {

    private int _id;
    private String _name;
    private String _description;
    private int _score;
    private int _level;



    /**
     * Metodo getter del atributo descricion
     * @return devuelve Descripcion del Reto cumplido
     */
    public String getDescription() {
        return _description;
    }

    /**
     * void setter del atributo _description
     * @param _description establece la _description del reto como contenido del atributo _description
     */
    public void setDescription(String _description) {
        this._description = _description;
    }


    /**
     * Motodo getter del atributo puntaje
     * @return devuelve Puntaje adquirido ṕor cumplir el reto
     */
    public int getScore() {
        return _score;
    }

    /**
     * void setter del atributo _score
     * @param _score establece el _score del reto como contenido del atributo _score
     */
    public void setScore(int _score) {
        this._score = _score;
    }


    /**
     * Metodo getter del atributo nivel
     * @return devuelve Nivel requerido para realizar el reto cumplido
     */
    public int getLevel() {
        return _level;
    }

    /**
     * void setter del atributo _level
     * @param _level establece el _level del reto como contenido del atributo _level
     */
    public void setLevel(int _level) {
        this._level = _level;
    }

    /**
     * Metodo contructor de la clase Data
     * @Param null
     */
    public Data(){
        _id = 0;
        _name = "";
    }

    /**
     * Metodo getter del atributo id
     * @return devuelve Id del reto cumplido
     */
    public int getId() {
        return _id;
    }

    /**
     * Metodo setter del atributo _id
     * @param _id establece el Id del reto cumplido  como contenido del atributo _id
     */
    public void setId(int _id) {
        this._id = _id;
    }


    /**
     * Metodo getter del atributo nombre
     * @return devuelve Nombre del reto cumplido
     */
    public String getName() {
        return _name;
    }

    /**
     * Metodo setter del atributo _name
     * @param _name establece el Nombre del reto cumplido  como contenido del atributo _name
     */
    public void setName(String _name) {
        this._name = _name;
    }
}
