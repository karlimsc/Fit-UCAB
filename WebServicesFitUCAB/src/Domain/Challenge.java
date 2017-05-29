package Domain;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by noe on 26/5/2017.
 * Clase challenge modulo 8
 */

@XmlRootElement
public class Challenge {

    private int _challengeId;
    private String _challengeName;
    private String _challengeDescription;
    private int _challengeKilometers;
    private int _challengeScore;
    private char _challengePredefined;
    private String _challengeType;


    /**
     * Constructor vacio
     */
    public Challenge() {
    }

    ;


    /**
     * constructor solo name
     * @param _challengeId
     * @param _challengeName
     */
    public Challenge(int _challengeId,String _challengeName) {
        this._challengeId = _challengeId;
        this._challengeName = _challengeName;
    }//cierre del contstructor challenge



    /**
     * constructor con todos los atributos
     *
     * @param _challengeName
     * @param _challengeDescription
     * @param _challengeKilometers
     * @param _challengeScore
     * @param _challengePredefined
     * @param _challengeId
     */
    public Challenge(int _challengeId, String _challengeName, String _challengeDescription, int _challengeKilometers, int _challengeScore, char _challengePredefined) {
        this._challengeId = _challengeId;
        this._challengeName = _challengeName;
        this._challengeDescription = _challengeDescription;
        this._challengeKilometers = _challengeKilometers;
        this._challengeScore = _challengeScore;
        this._challengePredefined = _challengePredefined;
    }//cierre del constructor con todos los atributos


    /**
     * constructor name y description
     *
     * @param _challengeName
     * @param _challengeDescription
     * @param _challengeId
     */
    public Challenge(int _challengeId,String _challengeName, String _challengeDescription) {
        this._challengeId = _challengeId;
        this._challengeName = _challengeName;
        this._challengeDescription = _challengeDescription;
    }//cierre del constructor name y description


    /**
     * constructor id,name y level
     *
     * @param _challengeName
     * @param _challengeKilometers
     * @param _challengeId
     */
    public Challenge(int _challengeId,String _challengeName, int _challengeKilometers) {
        this._challengeId = _challengeId;
        this._challengeName = _challengeName;
        this._challengeKilometers = _challengeKilometers;
    }//cierre del constructor name y level


    /**
     * constructor id,level y score
     * @param _challengeId
     * @param _challengeKilometers
     * @param _challengeScore
     */
    public Challenge(int _challengeId, int _challengeKilometers, int _challengeScore) {
        this._challengeId = _challengeId;
        this._challengeKilometers = _challengeKilometers;
        this._challengeScore = _challengeScore;
    }//cierre del constructor level y score


    /**
     * constructor id, description, level, score
     * @param _challengeKilometers
     * @param _challengeId
     * @param _challengeScore
     * @param _challengeDescription
     */
    public Challenge(int _challengeId, String _challengeDescription, int _challengeKilometers, int _challengeScore) {
        this._challengeId = _challengeId;
        this._challengeDescription = _challengeDescription;
        this._challengeKilometers = _challengeKilometers;
        this._challengeScore = _challengeScore;
    }//cierre del constructor description level score


    /**
     * constructor id,name, description, score
     * @param _challengeScore
     * @param _challengeId
     * @param _challengeDescription
     * @param _challengeName
     */
    public Challenge(int _challengeId,String _challengeName, String _challengeDescription, int _challengeScore) {
        this._challengeId = _challengeId;
        this._challengeName = _challengeName;
        this._challengeDescription = _challengeDescription;
        this._challengeScore = _challengeScore;
    }//cierre del constructor name, description, score


    /**
     * metodo para obtener name
     *
     * @return _challengeName
     */
    public String get_challengeName() {
        return _challengeName;
    }//cierre del metodo para obtener name


    /**
     * metodo para montar name
     *
     * @param _challengeName
     */
    public void set_challengeName(String _challengeName) {
        this._challengeName = _challengeName;
    }//cierre del meetodo para montar name


    /**
     * metodo para obtener description
     *
     * @return _challengeDescription
     */
    public String get_challengeDescription() {
        return _challengeDescription;
    }//cierre del metodo para obtener description


    /**
     * metodo para montar description
     *
     * @param _challengeDescription
     */
    public void set_challengeDescription(String _challengeDescription) {
        this._challengeDescription = _challengeDescription;
    }//cierre del metodo para montar description


    /**
     * metodo para obtener level
     *
     * @return _challengeKilometers
     */
    public int get_challengelevel() {
        return _challengeKilometers;
    }//cierre del metodo para obtener level


    /**
     * metodo para montar level
     *
     * @param _challengeKilometers
     */
    public void set_challengeKilometers(int _challengeKilometers) {
        this._challengeKilometers = _challengeKilometers;
    }//cierre del metodo para montar level


    /**
     * metodo para obtener score
     *
     * @return _challengeScore
     */
    public int get_challengeScore() {
        return _challengeScore;
    }//cierre del metodo para obtener score


    /**
     * metodo para montar score
     * @param _challengeScore
     */
    public void set_challengeScore(int _challengeScore) {
        this._challengeScore = _challengeScore;
    }//cierre del metodo para montar score


    /**
     * metodo para obtener predefinido
     * @return _challengePredefined
     */
    public char get_challengePredefined() {
        return _challengePredefined;
    }

    /**
     * metodo para montar si el reto es predefinido s o n
     * @param _challengePredefined
     */
    public void set_challengePredefined(char _challengePredefined) {
        this._challengePredefined = _challengePredefined;
    }

    /**
     * metodo para obtener el id del reto
     * @return
     */
    public int get_challengeId() {
        return _challengeId;
    }

    /**
     * metodo para montar el id del reto
     * @param _challengeId
     */

    public void set_challengeId(int _challengeId) {
        this._challengeId = _challengeId;
    }


    /**
     * metoo para obtener la cantidad de kilometros del reto
     * @return
     */

    public int get_challengeKilometers() {
        return _challengeKilometers;
    }

    /**
     * metedo para obtener el tipo de reto
     * @return
     */
    public String get_challengeType() {
        return _challengeType;
    }

    /**
     * metodo para montar el tipo de reto
     * @param _challengeType
     */
    public void set_challengeType(String _challengeType) {
        this._challengeType = _challengeType;
    }
}//cierre de la clase challenge
