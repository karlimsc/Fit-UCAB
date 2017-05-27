package Domain;

import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

/**
 * Created by noe on 26/5/2017.
 * Clase challenge modulo 8
 */

@XmlRootElement
public class Challenge {

    private String _challengeName;
    private String _challengeDescription;
    private int _challengeLevel;
    private int _challengeScore;
    private char _challengePredefined;


    /**
     * Constructor vacio
     */
    public Challenge() {
    }

    ;


    /**
     * constructor solo name
     */
    public Challenge(String _challengeName) {
        this._challengeName = _challengeName;
    }//cierre del contstructor challenge


    /**
     * constructor con todos los atributos
     *
     * @param _challengeName
     * @param _challengeDescription
     * @param _challengeLevel
     * @param _challengeScore
     * @param _challengePredefined
     */
    public Challenge(String _challengeName, String _challengeDescription, int _challengeLevel, int _challengeScore,char _challengePredefined) {
        this._challengeName = _challengeName;
        this._challengeDescription = _challengeDescription;
        this._challengeLevel = _challengeLevel;
        this._challengeScore = _challengeScore;
        this._challengePredefined = _challengePredefined;
    }//cierre del constructor con todos los atributos


    /**
     * constructor name y description
     *
     * @param _challengeName
     * @param _challengeDescription
     */
    public Challenge(String _challengeName, String _challengeDescription) {
        this._challengeName = _challengeName;
        this._challengeDescription = _challengeDescription;
    }//cierre del constructor name y description


    /**
     * constructor name y level
     *
     * @param _challengeName
     * @param _challengeLevel
     */
    public Challenge(String _challengeName, int _challengeLevel) {
        this._challengeName = _challengeName;
        this._challengeLevel = _challengeLevel;
    }//cierre del constructor name y level


    /**
     * constructor level y score
     */
    public Challenge(int _challengeLevel, int _challengeScore) {
        this._challengeLevel = _challengeLevel;
        this._challengeScore = _challengeScore;
    }//cierre del constructor level y score


    /**
     * constructor description level score
     */
    public Challenge(String _challengeDescription, int _challengeLevel, int _challengeScore) {
        this._challengeDescription = _challengeDescription;
        this._challengeLevel = _challengeLevel;
        this._challengeScore = _challengeScore;
    }//cierre del constructor description level score


    /**
     * constructor name, description, score
     */
    public Challenge(String _challengeName, String _challengeDescription, int _challengeScore) {
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
     * @return _challengeLevel
     */
    public int get_challengelevel() {
        return _challengeLevel;
    }//cierre del metodo para obtener level


    /**
     * metodo para montar level
     *
     * @param _challengeLevel
     */
    public void set_challengeLevel(int _challengeLevel) {
        this._challengeLevel = _challengeLevel;
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

    public void set_challengePredefined(char _challengePredefined) {
        this._challengePredefined = _challengePredefined;
    }


}//cierre de la clase challenge
